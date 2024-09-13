package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.DTO.MovietbDTO;

public interface MovieService_2 {
	public ArrayList<MovietbDTO> selectAll();
	public MovietbDTO getTitleRating(HashMap<String, String> param);
}
