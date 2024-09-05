package com.boot.DAO;

import org.apache.ibatis.annotations.Mapper;
import com.boot.DTO.SelecGenretbDTO;

@Mapper
public interface SelecGenretbDAO_3 {

    void insertUserGenre(SelecGenretbDTO selecgenretbDTO);

    // You can add more methods here if needed
}
