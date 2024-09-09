$(document).ready(function()
{
 
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
            // 버튼 활성화
            $(this).addClass('active');
            $('button.filter').not(this).removeClass('active');
        });




    /*
        2024-090--02 서연주 
        글자수 제한 + 넘는건 ...처리: 영화제목/관람평
    */
    $('.commentTitle').each(function() {
        var length = 13; //표시할 글자 수 정하기
    
        $(this).each(function()
        {
            if($(this).text().length >= length)
            {
                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
            }
        });
    });

    $('.commentBody').each(function()  {
        var length = 80; //표시할 글자 수 정하기
    
        $(this).each(function()
        {
            if($(this).text().length >= length)
            {
                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
            }
        });
    });
    
    //본영화에서 영화제목
    // $('.movieTitle').each(function()  {
    //     var length = 20; //표시할 글자 수 정하기
    
    //     $(this).each(function()
    //     {
    //         if($(this).text().length >= length)
    //         {
    //             $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
    //         }
    //     });
    // });

    //파트너스에서 영화제목/게시글제목
    $('.movieName > .title').each(function()  {
        var length = 12; //표시할 글자 수 정하기
    
        $(this).each(function()
        {
            if($(this).text().length >= length)
            {
                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
            }
        });
    });
    $('.partnerTitle').each(function()  {
        var length = 24; //표시할 글자 수 정하기
    
        $(this).each(function()
        {
            if($(this).text().length >= length)
            {
                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
            }
        });
    });
});// document ready 끝