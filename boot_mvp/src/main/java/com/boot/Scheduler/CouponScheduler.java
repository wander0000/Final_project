package com.boot.Scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boot.DTO.CouponDTO;
import com.boot.Service.AttendenceService;
import com.boot.Service.CouponService;
import com.boot.Service.UserService_4;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

@Slf4j
@Component
public class CouponScheduler {

    @Autowired
    private CouponService couponService;

    @Autowired
    private AttendenceService attService;

    @Autowired
    private UserService_4 userService;

    /* SMS 전송을 위한 CoolSMS 설정 */
    private DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(
            "NCS5XV1C37RNDO0E", "IRLCQO4R6VDFOA0PTSOVFK2GACU3MQCP", "https://api.coolsms.co.kr");

    // 매일 오전 10시에 생일 쿠폰 발급을 위한 스케줄러
    @Scheduled(cron = "0 0 10 * * ?")
    public void issueCouponsDaily() {
        couponService.issueBirthdayCoupons();
    }

    
    // 매달 1일 10:00에 만근유저 찾아서 쿠폰발행(출석이벤트)
    @Transactional
    @Scheduled(cron = "0 46 15 * * ?") // 매달 1일 10:00에 실행 (cron 표현식 사용)
    public void issueMonthlyCoupons() {
        // 만근한 유저들의 UUID 리스트 가져오기
        List<String> uuidList = attService.checkMonthlyAttendance();

        try {
            for (String uuid : uuidList) {
                // 쿠폰 발행
                CouponDTO coupon = new CouponDTO();
                coupon.setType("D"); // 타입: C(쿠폰), D(할인권)
                coupon.setPeriod(30); // 유효기간 30일
                coupon.setReason("출석이벤트 만근 축하");
                coupon.setRefno(4); // 쿠폰 레퍼런스 타입 (10% 할인권)
                coupon.setAcrec("N"); // 쿠폰 상태:미등록
                coupon.setUuid(uuid); // 사용자 UUID

                // 쿠폰 발급 후 쿠폰번호 조회
                String couponno = couponService.issueCoupon(coupon);

                // 해당 유저의 전화번호 가져오기
                String phoneNumber = userService.getUserPhoneNumber(uuid);

                // SMS 발송
                sendSms(phoneNumber, couponno);
            }
        } catch (Exception e) {
            log.error("SMS 전송 실패: " + e.getMessage());
        }
    }

    // CoolSMS API를 통한 SMS 발송 메서드
    private void sendSms(String phoneNumber, String couponno) throws Exception {
        Message message = new Message();
        String formattedPhone = phoneNumber.replaceAll("-", ""); // 전화번호에서 "-" 제거
        log.info("전화번호: " + formattedPhone + " // 쿠폰번호: " + couponno);

        // 발신번호 및 수신번호 설정
        message.setFrom("01098131204");
        message.setTo(formattedPhone);
        message.setText("[MVP] 출석 이벤트 만근_할인권 제공 안내\n" + 
                        "\n할인권 번호: " + couponno + 
                        "\n\nMVP 웹페이지에서 할인권을 등록 후 예매 시 사용 가능합니다.");

        try {
            // 메시지 발송
            SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            log.info("SMS 발송 성공: " + response.getMessageId());
        } catch (Exception e) {
            log.error("SMS 발송 실패: " + e.getMessage(), e);
            throw new Exception("SMS 발송 중 오류가 발생했습니다.", e);
        }
    }
//    @Scheduled(fixedRate = 10000)  // 10초마다 실행
//    public void fixedRateTask() {
//        System.out.println("10초마다 실행되는 작업입니다.");
//    }


}

