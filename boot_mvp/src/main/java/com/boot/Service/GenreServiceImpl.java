package com.boot.Service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.DTO.GenreDTO;
import com.boot.DAO.GenreDAO_3;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    private GenreDAO_3 genreDAO;

    // 모든 장르를 가져오는 메서드
    public ArrayList<GenreDTO> getAllGenres() {
        return genreDAO.getAllGenres();
    }
}
