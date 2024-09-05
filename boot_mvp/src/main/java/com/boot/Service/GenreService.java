package com.boot.Service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.DTO.GenreDTO;
import com.boot.DAO.GenreDAO_3;

@Service
public interface GenreService {

	public ArrayList<GenreDTO> getAllGenres();  // 장르 리스트 가져오기
	
}
