package com.boot.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SelecGenretbDAO_4 {

    public List<String> getUserGenre(String uuid);//좋아하는 선호장르 이름 리스트로 반환
    public int getGenreNum(String genreName);//좋아하는 선호장르 이름 리스트로 반환
    public void updateSelectGenre(@Param("genrenm") String genrenm, @Param("uuid") String uuid);
    public void updateSelectGenreOauth(@Param("genrenm") String genrenm, @Param("uuid") String uuid);
    public void deleteSelectGenre(@Param("uuid") String uuid);
    
}
