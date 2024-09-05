package com.boot.Service;

import org.springframework.stereotype.Service;

import com.boot.DTO.SelecGenretbDTO;

@Service
public interface SelecGenretbService {

    void insertUserGenre(SelecGenretbDTO selecgenretbDTO);

    // You can add more methods here if needed
}
