package com.boot.DAO;

import org.apache.ibatis.annotations.Mapper;
import com.boot.DTO.SelecGenretbDTO;

@Mapper
public interface SelecGenretbDAO_3 {

    void oauthtbinsertUserGenre(SelecGenretbDTO selecgenretbDTO);
    void usertbinsertUserGenre(SelecGenretbDTO selecgenretbDTO);

    // You can add more methods here if needed
}
