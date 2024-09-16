package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.DTO.ReserdtltbDTO;

public interface ReserdtltbService_2 {
	public void insertReserdtl(HashMap<String, String> param);
	public int selected_count(HashMap<String, String> param);
	public ArrayList<ReserdtltbDTO> selected_seat(HashMap<String, String> param);
}
