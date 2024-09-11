package com.boot.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.DAO.MovieDAO;
import com.boot.DTO.BoxOfficeDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoxOfficeServiceimpl implements BoxOfficeService{	
	


    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertBoxOffice()  
    {
    	
        List<BoxOfficeDTO> boxOfficeList = new ArrayList<>();
        String kobisKey ="72119d4336ab6e93b05f618318676111";
        String apiKey = "4d8142b91b20a69346b46d5548e738e3";
        String imgBaseURL = "https://image.tmdb.org/t/p/w1280";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        
        // 현재 날짜를 얻습니다
        LocalDate today = LocalDate.now();
        
        // 박스오피스 날짜
        LocalDate boxOfficeday = today.minusDays(1);

        // 두 달 전 날짜 계산
        LocalDate twoMonthsAgo = today.minusMonths(2);

        // 두 달 후 날짜 계산
        LocalDate twoMonthsLater = today.plusMonths(2);

        // 날짜 포맷을 지정합니다 (YYYYMMDD 형식으로)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 날짜를 포맷에 맞게 문자열로 변환합니다
        String gteDate = twoMonthsAgo.format(formatter);
        String lteDate = twoMonthsLater.format(formatter);
        String boxDate = boxOfficeday.format(formatter);
        
        // API URLs
        String movieURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
        + "searchDailyBoxOfficeList.json?key="+kobisKey+"&targetDt=" +boxDate;
        

        try 
        {
			 //기본 영화정보 가져오기
	         String movieResponse = restTemplate.getForObject(movieURL, String.class);
	         JsonNode movieNode = objectMapper.readTree(movieResponse).get("boxOfficeResult").path("dailyBoxOfficeList");
	         processMovieData(movieNode, boxOfficeList, imgBaseURL, objectMapper, apiKey, kobisKey);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        saveMoviesToDatabase2(boxOfficeList);
    }

    private void processMovieData(JsonNode movies, List<BoxOfficeDTO> boxOfficeList, String imgBaseURL, 
    		ObjectMapper objectMapper, String apiKey, String kobisKey) 
    {
    	
        for (JsonNode movieData : movies) 
        {
        	RestTemplate restTemplate = new RestTemplate();
    	    objectMapper = new ObjectMapper();    	    
    	    
            String movieno = movieData.get("movieCd").asText();
            log.info("@#@#@# movieno===>"+movieno);
            
            String movienm = movieData.get("movieNm").asText();   
            log.info("@#@#@# movienm===>"+movienm);  

            
            String salesAcc = movieData.get("salesAcc").asText();
            
            
            int audiAcc = movieData.get("audiAcc").asInt();
            int ranking = movieData.get("rank").asInt();            
            String releaseday = movieData.get("openDt").asText();            
            int times=0;
            String directorStr ="";
            String genreStr ="";
            String castStr ="";
//            String releaseday = "";
            String status="";
            String typeNm="";
            String showTypeStr="";
            String certification = "";
            String movieyoutube = "";
            String steelcut = "";
            String moviebackimg = "";
            String moviepostimg = "";
            String intro = "";
            
            // Fetch additional details for each movie            
            String movieDetailsURL = "https://api.themoviedb.org/3/search/movie?api_key="+ apiKey + "&query=" +movienm+"&language=ko-kr";
//            String movieDetailsURL = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + movienm.replaceAll(" ", "") + "&language=ko-kr";
            String youtubeSteelUrl = "";
            String boxInfoURL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="+kobisKey+"&movieCd="+movieno;
            
            
            try 
            {
            	String boxDetailsResponse = new RestTemplate().getForObject(boxInfoURL, String.class);
		        JsonNode boxNode = objectMapper.readTree(boxDetailsResponse);
		        
		        JsonNode box1 = boxNode.path("movieInfoResult");
	            JsonNode boxInfo = box1.path("movieInfo");
	            
	            times = boxInfo.path("showTm").asInt();
	            status = boxInfo.path("prdtStatNm").asText();
	            typeNm = boxInfo.path("typeNm").asText();
//	            releaseday = boxInfo.path("openDt").asText();
	            
	            // 장르 추출
		        JsonNode genresNode = boxInfo.path("genres");
		        List<String> genreNames = new ArrayList<>();  // 장르 이름을 저장할 리스트
		        if (genresNode.isArray()) 
		        {
		            for (JsonNode genre : genresNode) {
		            	 genreNames.add(genre.path("genreNm").asText());
		                log.info("@#@#@#genreNames==>"+genreNames);
		            }
		        }		       
		        genreStr = String.join(",", genreNames);
	            
		        
		        // 배우 추출
		        List<String> castNames = new ArrayList<>();		        
		        // Extract cast names and director names
	            JsonNode creditsNode = boxInfo.path("actors");
	            if (creditsNode.isArray()) 
		        {
	            	for (JsonNode castMember : creditsNode) 
	            	{
		                String castName = castMember.path("peopleNm").asText();
		                castNames.add(castName);
		            }
		        }	            
	            castStr = String.join(",", castNames);
	            
	            // 상영타입 추출
	            List<String> showTypeNames = new ArrayList<>();
	            JsonNode showTypeNode = boxInfo.path("showTypes");
	            if (showTypeNode.isArray()) 
		        {
	            	for (JsonNode showType : showTypeNode) 
	            	{
		                String showTypeName = showType.path("showTypeGroupNm").asText();
		                showTypeNames.add(showTypeName);		                
		            }
		        }
	            showTypeStr = String.join(",", showTypeNames);
	            
	            // 관람등급
	            List<String> GradeNames = new ArrayList<>();
	            JsonNode GradeNode = boxInfo.path("audits");
	            if (GradeNode.isArray()) 
		        {
	            	for (JsonNode Grade : GradeNode) 
	            	{
		                String GradeName = Grade.path("watchGradeNm").asText();
		                GradeNames.add(GradeName);		                
		            }
		        }
	            certification = String.join(",", GradeNames);
	            
	            // 감독 추출
	            List<String> directorNames = new ArrayList<>();
	            JsonNode directorNode = boxInfo.path("directors");
	            if (directorNode.isArray()) 
		        {
	            	for (JsonNode director : directorNode) 
	            	{
		                String directorName = director.path("peopleNm").asText();
		                directorNames.add(directorName);		                
		            }
		        }
	            directorStr = String.join(",", directorNames);
	            
	            //TMDB 조회
	            String movieDetailsResponse = new RestTemplate().getForObject(movieDetailsURL, String.class);
		        JsonNode rootNode = objectMapper.readTree(movieDetailsResponse);
		        JsonNode resultsNode  = rootNode.path("results");
		        
	            String correctedMovieNm = addSpaceBeforeNumber(movienm);
	            List<String> searchTitles = Arrays.asList(movienm, correctedMovieNm);
	            JsonNode matchedMovieNode = null;
	            
	            for (String searchTitle : searchTitles) {
	                String movieDetailsURL2 = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + searchTitle + "&language=ko-kr";
	                try {
	                    String movieDetailsResponse2 = new RestTemplate().getForObject(movieDetailsURL2, String.class);
	                    JsonNode rootNode2 = objectMapper.readTree(movieDetailsResponse2);
	                    JsonNode resultsNode2 = rootNode2.path("results");

	                    // 검색 결과 중에서 가장 유사한 영화를 선택
	                    for (JsonNode movieNode : resultsNode2) {
	                        String title = movieNode.path("title").asText();

	                        // 검색한 제목과 일치하는지 확인
	                        if (title.equalsIgnoreCase(searchTitle)) {
	                            matchedMovieNode = movieNode;
	                            break;  // 일치하는 영화가 있으면 반복 종료
	                        }
	                    }

	                    // 이미 매칭된 영화가 있으면 검색을 중지
	                    if (matchedMovieNode != null) {
	                        break;
	                    }

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }

	            
		        
	            if (matchedMovieNode != null) 
	            {
	            	intro = matchedMovieNode.path("overview").asText();
			        log.info("@#@# intro====>"+intro);
			        
			        int movieNum= matchedMovieNode.path("id").asInt();;		        
			        log.info("@#@# movieNum====>"+movieNum);
			        
		            moviebackimg = imgBaseURL + (matchedMovieNode.has("backdrop_path") ? matchedMovieNode.get("backdrop_path").asText() : "");
		            moviepostimg = imgBaseURL + (matchedMovieNode.has("poster_path") ? matchedMovieNode.get("poster_path").asText() : "");
		            
			        
		            youtubeSteelUrl = "https://api.themoviedb.org/3/movie/" + movieNum + "?append_to_response=videos,images&api_key=4d8142b91b20a69346b46d5548e738e3";
			        
		            String movieDetailResponse2 = restTemplate.getForObject(youtubeSteelUrl, String.class);
			        JsonNode movieDetailNode = objectMapper.readTree(movieDetailResponse2);
			        
			        // 비디오 및 이미지 정보 추출
			        movieyoutube = extractYouTubeVideoKey(movieDetailNode);
			        steelcut = extractSteelCuts(movieDetailNode, imgBaseURL);
			        BoxOfficeDTO movie = new BoxOfficeDTO();            
		            
			        String bmovieno = "b-"+movieno;
		            movie.setMovieno(bmovieno);
		            movie.setMovienm(movienm);
		            movie.setDirectornm(directorStr);
		            movie.setReleaseday(parseReleaseDate(releaseday));
		            movie.setRatingno(certification);
		            movie.setTimes(times);
		            movie.setIntro(intro);
		            movie.setMoviebackimg(moviebackimg);
		            movie.setMoviepostimg(moviepostimg);
		            movie.setMovieyoutube(movieyoutube);
		            movie.setSteelcut(steelcut);            
		            movie.setGenre(genreStr);
		            movie.setActor(castStr);
		            movie.setSalesAcc(salesAcc);
		            movie.setAudiAcc(audiAcc);
		            movie.setRanking(ranking);
		            movie.setStatus(status);
		            movie.setTypeNm(typeNm);
		            movie.setShowType(showTypeStr);
			        

			        boxOfficeList.add(movie);
	            }
		        

            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            // Create movieDTO object and add to list
            
            

        }
    }

    private void saveMoviesToDatabase2(List<BoxOfficeDTO> boxOfficeList) 
    {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) 
        {
            MovieDAO batchMovieDAO = sqlSession.getMapper(MovieDAO.class);
            
            // 제목이 한글인 영화만 필터링
            List<BoxOfficeDTO> koreanMovies = boxOfficeList.stream()
                .filter(movie -> isKoreanTitle(movie.getMovienm()))
                .collect(Collectors.toList());

            // 중복 데이터가 있는지 확인하고, 중복되지 않는 데이터만 삽입
            for (BoxOfficeDTO movie : koreanMovies) 
            {
                if (!batchMovieDAO.existsById(movie.getMovieno())) 
                {
                    batchMovieDAO.insertBoxOffice(List.of(movie));
                }
            }
            
            sqlSession.commit();  // 커밋
        } 
        catch (Exception e) 
        {
            e.printStackTrace();  // 예외 처리
        }
    }

    private boolean isKoreanTitle(String title) 
    {
        // 한글이 포함되어 있는지 체크
        return Pattern.compile("[가-힣]").matcher(title).find();
    }
    
    private Date parseReleaseDate(String dateStr) 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try 
        {
            return dateFormat.parse(dateStr);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();  // 예외 처리
            return null;  // 파싱 실패 시 null 반환
        }
    }
    
    private String extractYouTubeVideoKey(JsonNode movieDetailNode) 
    {
        JsonNode videosNode = movieDetailNode.path("videos").path("results");
        if (videosNode.isArray() && videosNode.size() > 0) {
            JsonNode firstVideo = videosNode.get(0);
            return "https://www.youtube.com/embed/" + firstVideo.path("key").asText();
        }
        return "";
    }

    private String extractSteelCuts(JsonNode movieDetailNode, String imgBaseURL) 
    {
        JsonNode imagesNode = movieDetailNode.path("images").path("backdrops");
        StringBuilder steelcuts = new StringBuilder();
        int count = 0; // 스틸컷 개수 카운트

        if (imagesNode.isArray()) 
        {
            for (JsonNode imageNode : imagesNode) 
            {
                if (count >= 12) break; // 최대 10개의 스틸컷만 처리
                if (steelcuts.length() > 0)
                {
                    steelcuts.append(",");
                }
                steelcuts.append(imgBaseURL).append(imageNode.path("file_path").asText());
                count++;
            }
        }
        return steelcuts.toString();
    }

	@Override
	public ArrayList<BoxOfficeDTO> BoxOfficeList() 
	{		
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		ArrayList<BoxOfficeDTO> Boxlist= dao.BoxOfficeList();
		
		return Boxlist;
	}
	
	// 숫자 앞에 자동으로 띄어쓰기를 추가하는 메서드
	private String addSpaceBeforeNumber(String movienm) 
	{
	    return movienm.replaceAll("(\\D)(\\d)", "$1 $2");  // 숫자 앞에 공백 추가
	}
	
	
	

	
	

	

	
}







