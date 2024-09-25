package com.boot.Service;

import java.util.HashMap;

public interface ReserdtltmptbService_2 {
	public int getCnt(HashMap<String, String> param);
	public void inserttmp(HashMap<String, String> param);
	public void deletetmp(HashMap<String, String> param);
	public boolean checkDelStatus(); // deletetmp() 메소드 관련 - 쿼리 동작 안함
    public void resetDelStatus(); // deletetmp() 메소드 관련 - 쿼리 동작 안함
}
