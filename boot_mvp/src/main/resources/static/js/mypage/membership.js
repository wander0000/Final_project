$(document).ready(function()
{
    /*
        2024-09-01 서연주 
        서브메뉴 눌렀을 때 메뉴 활성화 : active
    */
    $('.subHeader ul li').click(function(){
        $(this).addClass('active');
        $('.navMenu ul li').not(this).removeClass('active');
    });


    /*
        2024-09-01 서연주 
         탭 메뉴 활성화 : active
    */
    $('.tabCon').click(function(){
        // 탭 활성화
        $(this).addClass('active');
        $('.tabCon').not(this).removeClass('active');
        
        // 탭 유형 가져오기
        const tabType = $(this).data('tab');
        
        // 콘텐츠 활성화
        $(`.contentCon.${tabType}`).addClass('active');
        $('.contentCon').not(`.contentCon.${tabType}`).removeClass('active');
    });



    /*
        2024-09-01 서연주 
        필터버튼 활성화 : active
    */
    $('button.filter').click(function(){
        $(this).addClass('active');
        $('button.filter').not(this).removeClass('active');
    });


    /*
        2024-09-01 서연주 
        연도/월 선택 
    */
    var $yearSelect = $('#yearSelect');
    var currentYear = new Date().getFullYear();
    
    // 현재 연도 기준으로 2년 전부터 올해까지 옵션 생성
    for (var i = currentYear - 2; i <= currentYear ; i++) {
        $yearSelect.append($('<option>', {
            value: i,
            text: i + '년'
        }));
    }
    
    // 현재 연도 선택
    $yearSelect.val(currentYear);

    // 연도와 월 선택 이벤트 핸들러
    $('#yearSelect, #monthSelect').change(updateFilter);
    
    function updateFilter() {
        var year = $('#yearSelect').val();
        var month = $('#monthSelect').val();
        
        if (year && month) {
            // 선택된 연도와 월로 필터링 로직 구현
            console.log('Selected: ' + year + '년 ' + month + '월');
            // 여기에 필터링 또는 데이터 로드 로직 추가
        }
    }

});// document ready 끝