package com.boot.Service;

import org.springframework.stereotype.Service;

import com.boot.DTO.SelecGenretbDTO;

@Service
public interface SelecGenretbService {

    void oauthtbinsertUserGenre(SelecGenretbDTO selecgenretbDTO);
    void usertbinsertUserGenre(SelecGenretbDTO selecgenretbDTO);

    // You can add more methods here if needed
}
