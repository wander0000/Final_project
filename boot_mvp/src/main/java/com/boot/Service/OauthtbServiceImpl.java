package com.boot.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DAO.OauthtbDAO_3;
import com.boot.DAO.UsertbDAO_3;
import com.boot.DTO.OauthtbDTO;
import com.boot.DTO.UsertbDTO;

@Service("OauthtbService")
public class OauthtbServiceImpl implements OauthtbService {

    @Autowired
    private OauthtbDAO_3 oauthtbDAO;
    @Autowired
    private UsertbDAO_3 usertbDAO;

    @Override
    public void oauthInsertUser(OauthtbDTO oauthtbDTO) {
        // UUID 생성 및 중복 확인 로직 추가
        String uuid = generateUniqueUUID();
        oauthtbDTO.setUuid(uuid);

        // 회원 정보 삽입
        oauthtbDAO.oauthInsertUser(oauthtbDTO);
    }

    @Override
    public int oauthCheckUserid(String userid) {
        // ID 중복 체크
        return oauthtbDAO.oauthCheckUserid(userid);
    }

    // UUID 중복 확인 및 생성 로직
    private String generateUniqueUUID() {
        String uuid;
        boolean isUnique = false;

        do {
            uuid = UUID.randomUUID().toString();
            
            // oauthtb에서는 boolean을 반환하고 usertb에서는 int를 반환하므로 각각에 맞는 처리
            if (!oauthtbDAO.existsByUuid(uuid) && !usertbDAO.existsByUuid2(uuid)) {
                isUnique = true;
            }
        } while (!isUnique);
        
        return uuid;
    }

    // uuid 생성 시 usertb와 비교하여 중복여부확인
    @Override
    public boolean existsByUuid(String uuid) {
        // 실제로 DAO 메서드를 호출하여 중복 여부 확인
        return oauthtbDAO.existsByUuid(uuid);
    }
    
    // 기존회원여부 확인
    @Override
    public boolean oauthCheckNewUser(String oauthUserId, String registrationId) {
        return oauthtbDAO.oauthCheckNewUser(oauthUserId, registrationId);
    }

	@Override
	public OauthtbDTO oauthGetUserById(String id) {
        return oauthtbDAO.oauthGetUserById(id);
	}

	@Override
	public OauthtbDTO oauthGetUserByuniq(String oauthuniq) {
		 return oauthtbDAO.oauthGetUserByuniq(oauthuniq);
	}
	
	
}
