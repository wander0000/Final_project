package com.boot.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestParam;

import com.boot.DTO.ScreentbDTO;

public interface ScreenService_2 {
	public ArrayList<ScreentbDTO> selecthdr(HashMap<String, String> param);
	public ArrayList<ScreentbDTO> selecthdrbase();
	public ArrayList<ScreentbDTO> selectdtl(HashMap<String, String> param);
	public ScreentbDTO selectmovieinfo(HashMap<String, String> param);
	public void Seat_deduction(HashMap<String, String> param);
}
