// DOMContentLoaded 이벤트가 발생한 후 스크립트를 실행합니다.
document.addEventListener('DOMContentLoaded', function() {
    // 모든 체크박스와 레이블을 선택합니다.
    const checkboxes = document.querySelectorAll('.genrebut');
    const labels = document.querySelectorAll('.genrebutlabel');

    // 각 체크박스에 대해 이벤트 리스너를 추가합니다.
    checkboxes.forEach((checkbox, index) => {
        checkbox.addEventListener('change', function() {
            if (checkbox.checked) {
                // 체크된 경우 스타일을 적용합니다.
                labels[index].style.backgroundColor = '#F3B931';
                labels[index].style.color = '#000000';
                labels[index].style.fontWeight = 500;
            } else {
                // 체크 해제된 경우 기본 스타일로 되돌립니다.
                labels[index].style.backgroundColor = 'rgba(153, 153, 153, 0.2)';
                labels[index].style.color = '#99A2B9';
                labels[index].style.fontWeight = 500;
            }
        });
    });
});