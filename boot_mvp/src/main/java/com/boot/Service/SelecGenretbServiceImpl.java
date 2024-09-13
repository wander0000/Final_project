package com.boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.DAO.SelecGenretbDAO_3;
import com.boot.DTO.SelecGenretbDTO;

@Service
public class SelecGenretbServiceImpl implements SelecGenretbService{

    @Autowired
    private SelecGenretbDAO_3 selecgenretbDAO;
    


	@Override
	public void oauthtbinsertUserGenre(SelecGenretbDTO selecgenretbDTO) {
        selecgenretbDAO.oauthtbinsertUserGenre(selecgenretbDTO);
	}

	@Override
	public void usertbinsertUserGenre(SelecGenretbDTO selecgenretbDTO) {
        selecgenretbDAO.usertbinsertUserGenre(selecgenretbDTO);
	}

    // 추가적인 서비스 메서드를 여기서 정의할 수 있습니다.
}
