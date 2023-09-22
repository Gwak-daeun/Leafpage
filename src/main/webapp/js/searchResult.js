$(document).ready(function () {

    $(window).scroll(function () {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            loadMoreData();
        }
    });

});

function loadMoreData() {
    // if (isLoading) return;
    //
    // isLoading = true;

    // 서버에서 데이터 가져오는 AJAX 요청
    $.ajax({
        url: 'bookScroll.do', // 데이터를 제공하는 서버 스크립트 경로
        method: 'GET',
        data: {
            page: page,
            searchKeyword : searchKeyword,
            searchSelect : searchSelect,
            genre : genre,
            sortWord : sortWord
        },
        success: function (data) {
            console.log("성공 : " , data);
            if (data.length > 0) {
                // 데이터를 HTML에 추가
                const $dataList = $('#searchResultUl');
                data.forEach(item => {
                    $dataList.append(`<li>${item}</li>`);
                });

                // isLoading = false;
                page += 12;
            }
        },
        error: function (error) {
            console.log("에러 : ", error);
        }
    });
}