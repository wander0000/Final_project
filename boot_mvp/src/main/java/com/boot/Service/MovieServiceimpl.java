package com.boot.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.boot.DTO.GenreDTO;
import com.boot.DTO.MovietbDTO;
import com.boot.DTO.SelecGenretbDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceimpl implements MovieService{	
	
	@Autowired
    private SqlSession sqlSession;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void insertMovie() {
        List<MovietbDTO> allMovies = new ArrayList<>();
        // Fetch movies from all pages (1-32)
        for (int page = 1; page <= 32; page++) 
//    	for (int page = 1; page <= 32; page++) 
        {
            List<MovietbDTO> movieDataList = insertMovie(page);
            allMovies.addAll(movieDataList);
        }

        // Save all movies to the database
        saveMoviesToDatabase2(allMovies);
    }

    private List<MovietbDTO> insertMovie(int page) 
    {
        List<MovietbDTO> movieList = new ArrayList<>();
        String apiKey = "4d8142b91b20a69346b46d5548e738e3";
        String imgBaseURL = "https://image.tmdb.org/t/p/w1280";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        
        // 현재 날짜를 얻습니다
        LocalDate today = LocalDate.now();

        // 두 달 전 날짜 계산
        LocalDate twoMonthsAgo = today.minusMonths(2);

        // 두 달 후 날짜 계산
        LocalDate twoMonthsLater = today.plusMonths(2);

        // 날짜 포맷을 지정합니다 (YYYYMMDD 형식으로)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 날짜를 포맷에 맞게 문자열로 변환합니다
        String gteDate = twoMonthsAgo.format(formatter);
        String lteDate = twoMonthsLater.format(formatter);
        
        // API URLs
        String movieURL = "https://api.themoviedb.org/3/discover/movie?primary_release_date.gte="+gteDate+"&primary_release_date.lte="+lteDate+"&api_key="
        +apiKey+ "&language=ko-kr&page="+page;

        try 
        {
			 //기본 영화정보 가져오기
	         String movieResponse = restTemplate.getForObject(movieURL, String.class);
	         JsonNode movieNode = objectMapper.readTree(movieResponse).get("results");
	         processMovieData2(movieNode, movieList, imgBaseURL, objectMapper, apiKey);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return movieList;
    }

    private void processMovieData2(JsonNode movies, List<MovietbDTO> movieList, String imgBaseURL, ObjectMapper objectMapper, String apiKey) 
    {
    	
        for (JsonNode movieData : movies) 
        {
        	RestTemplate restTemplate = new RestTemplate();
    	    objectMapper = new ObjectMapper();
    	    
            String movieno = movieData.get("id").asText();
            String movienm = movieData.get("title").asText();      
            String intro = getMovieOverview(movieno, movienm, apiKey);            
            String movieen = movieData.get("original_title").asText();            
            int times=0;     
            
            String moviebackimg ="";
            String moviepostimg ="";
            
            // backdrop_path가 있는 경우에만 설정
            if (movieData.has("backdrop_path") && !movieData.get("backdrop_path").isNull()) {
                moviebackimg = imgBaseURL + movieData.get("backdrop_path").asText();
            }

            // poster_path가 있는 경우에만 설정
            if (movieData.has("poster_path") && !movieData.get("poster_path").isNull()) {
                moviepostimg = imgBaseURL + movieData.get("poster_path").asText();
            }
            log.info("@#@#@# movieen===>"+movieen);

            // Initialize empty strings for YouTube and steelcut
            String movieyoutube = "";
            String steelcut = "";
            String certification = "15세이상관람가";

            // Fetch additional details for each movie
            String movieDetailsURL = "https://api.themoviedb.org/3/movie/" + movieno + "?api_key=" + apiKey + "&append_to_response=credits,release_dates&language=ko-kr";
            String youtubeSteelUrl = "https://api.themoviedb.org/3/movie/" + movieno + "?append_to_response=videos,images&api_key=4d8142b91b20a69346b46d5548e738e3";
            String genreStr ="";
            String castStr ="";
            String directorStr ="";
            
            try 
            {
	            String movieDetailsResponse = new RestTemplate().getForObject(movieDetailsURL, String.class);
		        JsonNode rootNode = objectMapper.readTree(movieDetailsResponse);
		        
		        String movieDetailResponse2 = restTemplate.getForObject(youtubeSteelUrl, String.class);
		        JsonNode movieDetailNode = objectMapper.readTree(movieDetailResponse2);
		        // 비디오 및 이미지 정보 추출
		        movieyoutube = extractYouTubeVideoKey(movieDetailNode);
		        steelcut = extractSteelCuts(movieDetailNode, imgBaseURL);
		        
		        times = rootNode.path("runtime").asInt();
		        log.info("@#@#@#runtime======>"+times);
		        
		        // 장르 추출
		        JsonNode genresNode = rootNode.path("genres");
		        List<String> genreNames = new ArrayList<>();  // 장르 이름을 저장할 리스트
		        if (genresNode.isArray()) 
		        {
		            for (JsonNode genre : genresNode) {
		                genreNames.add(genre.path("name").asText());
		                log.info("@#@#@#genreNames==>"+genreNames);
		            }
		        }		       
		        genreStr = String.join(",", genreNames);
		        
		        
		        // 배우 추출
		        List<String> castNames = new ArrayList<>();		        
		        // Extract cast names and director names
	            JsonNode creditsNode = rootNode.path("credits");
	            JsonNode castNode = creditsNode.path("cast");
	            if (castNode.isArray()) 
		        {
	            	for (JsonNode castMember : castNode) 
	            	{
		                String castName = castMember.path("name").asText();
		                castNames.add(castName);
		            }
		        }	            
	            castStr = String.join(",", castNames);
	            
	            
	            
	            List<String> directorNames = new ArrayList<>();
	            JsonNode crewNode = creditsNode.path("crew");
	            if (castNode.isArray()) 
		        {
	            	for (JsonNode crewMember : crewNode) 
	            	{
		                String job = crewMember.path("job").asText();
		                if ("Director".equalsIgnoreCase(job)) {
		                    String directorName = crewMember.path("name").asText();
		                    directorNames.add(directorName);
		                }
		            }
		        }
	            directorStr = String.join(",", directorNames);
	            
            
            

		         // Fetch production countries and their ISO codes
		         JsonNode productionCountriesNode = rootNode.path("production_countries");
		         List<String> productionIsoCodes = new ArrayList<>();
		
		         if (productionCountriesNode.isArray() && productionCountriesNode.size() > 0) {
		        	    for (JsonNode countryNode : productionCountriesNode) {
		        	        String isoCode = countryNode.path("iso_3166_1").asText();
		        	        System.out.println("Found ISO Code in production_countries: " + isoCode); // ISO 코드 출력
		        	        productionIsoCodes.add(isoCode); // Store all ISO codes from production countries
		        	    }
		        	} else {
		        	    System.out.println("No production countries found.");
		        	}
		
		        	// Fetch the release dates
		        	JsonNode releaseDatesNode = rootNode.path("release_dates").path("results");
		
		        	// Flag to determine if a certification has been found
		        	boolean certificationFound = false;
		        	
		        	Map<String, String> certificationMap = new HashMap<>();
		            certificationMap.put("TP", "전체관람가");
		            certificationMap.put("U/A 16+", "15세이상관람가");
		            certificationMap.put("G", "전체관람가");
		            certificationMap.put("PG", "전체관람가");
		            certificationMap.put("PG-13", "12세이상관람가");
		            certificationMap.put("R", "15세이상관람가");
		            certificationMap.put("NC-17", "청소년관람불가");
		            certificationMap.put("14A", "15세이상관람가");
		            certificationMap.put("15A", "15세이상관람가");
		            certificationMap.put("+13", "12세이상관람가");
		            certificationMap.put("17+", "15세이상관람가");
		            certificationMap.put("12A", "12세이상관람가");
		            certificationMap.put("6+", "전체관람가");
		            certificationMap.put("A", "전체관람가");
		            certificationMap.put("All", "전체관람가");
		            certificationMap.put("12", "12세이상관람가");
		            certificationMap.put("15", "15세이상관람가");
		            certificationMap.put("19", "청소년관람불가");
		            certificationMap.put("16", "15세이상관람가");
		            certificationMap.put("18", "청소년관람불가");
		            certificationMap.put("C", "청소년관람불가");
		            certificationMap.put("0", "15세이상관람가");
		            certificationMap.put(null, "15세이상관람가");
		            certificationMap.put("Restricted Screening", "청소년관람불가");
		
		        	// Iterate over release dates to find the certification for the given ISO codes
		        	for (JsonNode releaseDate : releaseDatesNode) 
		        	{
		        	    String releaseIsoCode = releaseDate.path("iso_3166_1").asText();
		        	    System.out.println("Found ISO Code in release_dates: " + releaseIsoCode); // ISO 코드 출력
		        	    
		        	    if (productionIsoCodes.contains(releaseIsoCode)) { // Check if the ISO code is in production countries
		        	        JsonNode releaseDatesArray = releaseDate.path("release_dates");
		        	        if (releaseDatesArray.isArray()) {
		        	            for (JsonNode dateNode : releaseDatesArray) {
		        	                String cert = dateNode.path("certification").asText();
		        	                if (!cert.isEmpty()) { // Empty string values are skipped
		        	                    certification = certificationMap.getOrDefault(cert, "15세이상관람가");
		//        	                    certification = cert;
		        	                    certificationFound = true;
		        	                    System.out.println("Certification found for ISO Code " + releaseIsoCode + ": " + certification); // 인증 값 출력
		        	                    break; // Exit loop once certification is found
		        	                }
		        	            }
		        	        }
		        	        if (certificationFound) {
		        	            break; // Exit outer loop if certification is found
		        	        }
		        	    }
		        	}
		
		        	// If certification is still "없음", check for KR
		        	if (!certificationFound) 
		        	{
		        	    System.out.println("Checking for KR certification...");
		
		        	    // Check for KR in release_dates
		        	    for (JsonNode releaseDate : releaseDatesNode) 
		        	    {
		        	        String releaseIsoCode = releaseDate.path("iso_3166_1").asText();
		        	        if ("KR".equals(releaseIsoCode)) {
		        	            JsonNode releaseDatesArray = releaseDate.path("release_dates");
		        	            if (releaseDatesArray.isArray()) {
		        	                for (JsonNode dateNode : releaseDatesArray) {
		        	                    String cert = dateNode.path("certification").asText();
		        	                    if (!cert.isEmpty()) { // Empty string values are skipped
		        	                    	certification = certificationMap.getOrDefault(cert, "15세이상관람가");
		//        	                    	certification = cert;
		        	                        System.out.println("Certification found for KR: " + certification); // 인증 값 출력
		        	                        certificationFound = true;
		        	                        break; // Exit loop once KR certification is found
		        	                    }
		        	                }
		        	            }
		        	            if (certificationFound) {
		        	                break; // Exit outer loop if KR certification is found
		        	            }
		        	        }
		        	    }
		        	}  
        	
            } // try 끝
            catch (Exception e) 
            {
                e.printStackTrace();
            }            
            
            // Create movieDTO object and add to list
            MovietbDTO movie = new MovietbDTO();            
            movie.setReleaseday(parseReleaseDate(movieData.path("release_date").asText("")));
            movie.setMovieno(movieno);
            movie.setMovienm(movienm);
            movie.setMovieen(movieen);
            movie.setIntro(intro);
            movie.setMoviebackimg(moviebackimg);
            movie.setMoviepostimg(moviepostimg);
            movie.setMovieyoutube(movieyoutube);
            movie.setSteelcut(steelcut);
            movie.setRatingno(certification);
            movie.setGenre(genreStr);
            movie.setActor(castStr);
            movie.setDirectornm(directorStr);
            movie.setMovieyoutube(movieyoutube);
	        movie.setSteelcut(steelcut);
	        movie.setTimes(times);

            movieList.add(movie); 
        }
    }

    // 데이터베이스에 저장하는 메소드
    private void saveMoviesToDatabase2(List<MovietbDTO> movieList) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            MovieDAO batchMovieDAO = sqlSession.getMapper(MovieDAO.class);
            
            // 제목이 한글인 영화만 필터링
            List<MovietbDTO> koreanMovies = movieList.stream()
                .filter(movie -> isKoreanTitle(movie.getMovienm()))
                .collect(Collectors.toList());

            // 중복 데이터가 있는지 확인하고, 중복되지 않는 데이터만 삽입
            for (MovietbDTO movie : koreanMovies) {
                if (!batchMovieDAO.existsById(movie.getMovieno())) {
                    batchMovieDAO.insertMovie(List.of(movie));
                }
            }
            
            sqlSession.commit();  // 커밋
        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
        }
    }

    // 제목에 한글 포함되어있는지 체크 
    private boolean isKoreanTitle(String title) {
        // 한글이 포함되어 있는지 체크
        return Pattern.compile("[가-힣]").matcher(title).find();
    }
    
    // 데이터포맷 바꿔서 가져오기 : 개봉일
    private Date parseReleaseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();  // 예외 처리
            return null;  // 파싱 실패 시 null 반환
        }
    }
    
    // 예고편 영상 가져오기
    private String extractYouTubeVideoKey(JsonNode movieDetailNode) 
    {
        JsonNode videosNode = movieDetailNode.path("videos").path("results");
        if (videosNode.isArray() && videosNode.size() > 0) {
            JsonNode firstVideo = videosNode.get(0);
            return "https://www.youtube.com/embed/" + firstVideo.path("key").asText();
        }
        return "";
    }
    
    // 스틸컷 가져오기
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
    
    // 제목으로 줄거리 가져오기
    public String fetchMovieOverviewById(String movieno, String apiKey) 
    {
        String movieDetailsURL = "https://api.themoviedb.org/3/movie/" + movieno + "?api_key=" + apiKey + "&append_to_response=credits,release_dates&language=ko-kr";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String response = restTemplate.getForObject(movieDetailsURL, String.class);
            JsonNode rootNode = objectMapper.readTree(response);
            String overview = rootNode.path("overview").asText();

            // Return the overview if it is available
            if (!overview.isEmpty()) {
                return overview;
            } else {
                // If overview is empty, return null or a default message
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    
    // movieno로 줄거리 못가져올때 타이틀로 가져오는 방법
	public String fetchMovieOverviewByTitle(String movienm, String apiKey) 
	{
	    String searchURL = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + movienm + "&language=ko-kr";
	    RestTemplate restTemplate = new RestTemplate();
	    ObjectMapper objectMapper = new ObjectMapper();

	    try 
	    {
	        String response = restTemplate.getForObject(searchURL, String.class);
	        JsonNode rootNode = objectMapper.readTree(response);
	        JsonNode resultsNode = rootNode.path("results");

	        // Iterate through search results and return the overview of the first match
	        if (resultsNode.isArray() && resultsNode.size() > 0) {
	            JsonNode firstResult = resultsNode.get(0);
	            return firstResult.path("overview").asText();
	        } else {
	            // If no results found, return null or a default message
	            return null;
	        }
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	// 줄거리 가져오기
	public String getMovieOverview(String movieno, String movienm, String apiKey) {
	    // Fetch overview by movie ID
	    String overview = fetchMovieOverviewById(movieno, apiKey);

	    // If overview is not available, search by title
	    if (overview == null || overview.isEmpty()) {
	        overview = fetchMovieOverviewByTitle(movienm, apiKey);
	    }

	    return overview;
	}
	
	// 영화 목록 불러오기
	@Override
	public ArrayList<MovietbDTO> MovieList() 
	{		
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		ArrayList<MovietbDTO> movieList = dao.MovieList();
		
		return movieList;
	}

	// 현재 상영작
	@Override
	public ArrayList<MovietbDTO> MoviePlayingList() 
	{
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		ArrayList<MovietbDTO> moviePlayingList = dao.MoviePlayingList();
		
		return moviePlayingList;
	}

	// 상영 예정작
	@Override
	public ArrayList<MovietbDTO> MovieUpcomingList() 
	{
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		ArrayList<MovietbDTO> movieUpcomingList = dao.MovieUpcomingList();
		
		return movieUpcomingList;
	}

	// 영화->장르별 검색 영화목록 가져오기
	@Override
	public ArrayList<MovietbDTO> GenreList(String genre) {
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		ArrayList<MovietbDTO> genreList = dao.GenreList(genre);
		
		return genreList;
	}

	// DB에 저장되어있는 장르 가져오기
	@Override
	public List<GenreDTO> Genre() {
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		List<GenreDTO> Genre = dao.Genre();
		return Genre;
	}

	// movieno 들고 들어와서 영화 상세정보 보여주기
	@Override
	public MovietbDTO movieInfo(String movieno) {
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		MovietbDTO dto = dao.movieInfo(movieno);
		
		return dto;
	}

	@Override
	public ArrayList<SelecGenretbDTO> selectUserGenre(HashMap<String, Object> param) {
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		ArrayList<SelecGenretbDTO> dto = dao.selectUserGenre(param);
		
		return dto;
	}

	@Override
	public ArrayList<BoxOfficeDTO> selectMoviesByGenres(HashMap<String, Object> param) {
		MovieDAO dao = sqlSession.getMapper(MovieDAO.class);
		ArrayList<BoxOfficeDTO> dto = dao.selectMoviesByGenres(param);
		
		return dto;
	}
	

	

	
	

	

	
}







