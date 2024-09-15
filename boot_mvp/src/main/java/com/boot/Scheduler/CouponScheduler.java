package com.boot.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boot.Service.CouponService;

@Component
public class CouponScheduler {

    @Autowired
    private CouponService couponService;

    // 매일 오전10시에 생일 쿠폰 발급을 위한 스케줄러
    @Scheduled(cron = "0 0 10 * * ?")//초 분 시 일 월 요일
    public void issueCouponsDaily() {
        couponService.issueBirthdayCoupons();
    }
    
//    @Scheduled(fixedRate = 10000)  // 10초마다 실행
//    public void fixedRateTask() {
//        System.out.println("10초마다 실행되는 작업입니다.");
//    }
}

