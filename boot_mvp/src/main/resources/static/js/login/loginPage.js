
    // 휠 스크롤 및 터치 스크롤 방지
    document.addEventListener('wheel', function(event) {
        event.preventDefault();
    }, { passive: false });

    // 키보드 화살표 키로 인한 스크롤 방지
    document.addEventListener('keydown', function(event) {
        if (event.key === 'ArrowUp' || event.key === 'ArrowDown') {
            event.preventDefault();
        }
    });

    // 터치 스크롤 방지 (모바일 디바이스)
    document.addEventListener('touchmove', function(event) {
        event.preventDefault();
    }, { passive: false });
