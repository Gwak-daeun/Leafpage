let curPage = 0;
let isLoading = false;

$(document).ready(function () {

    $("#spinner").hide();

    $(window).scroll(function () {

        console.log("page : ", page);

        let scrollTop = $(window).scrollTop();
        let windowsHeight = $(window).height();
        let documentHeight = $(document).height();
        let isBottom = scrollTop + windowsHeight + 10 >= documentHeight;

        if(isBottom) {
            console.log("바닥이야");
            if (curPage >= page) {
                console.log("안해");
                return false;
            } else {
                console.log("작동해");
                curPage += 12;
                loadMoreData();
            }
        }

    });

});

function loadMoreData() {
    if (isLoading) return;

    $("#spinner").show();

    isLoading = true;

    // 서버에서 데이터 가져오는 AJAX 요청
    $.ajax({
        url: '/bookScroll.do', // 데이터를 제공하는 서버 스크립트 경로
        method: 'GET',
        data: {
            page: page,
            searchKeyword : searchKeyword,
            searchSelect : searchSelect,
            genre : genre,
            sortWord : sortWord
        },
        success: function (data) {
            console.log("성공 : " , data.books);

            if (data.books.length > 0) {
                console.log("작동 확인");
                // 데이터를 HTML에 추가
                const $dataList = $('#searchResultUl');
                data.books.forEach(item => {
                    $dataList.append(` <li>
                            <a href="/detailPageView.do?isbn=${item.ISBN}">
                                <div class="book-list">
                                    <img class="book-cover" src="${item.bookImg}" />
                                    <div class="book-title">${item.bookName}</div>
                                    <div class="book-author">${item.bookAuthorName}</div>
                                </div>
                            </a>
                        </li>`
                    );
                });

                isLoading = false;

                page += 12;
            }

            $("#spinner").hide();
        },
        error: function (error) {
            console.log("에러 : ", error);
        }
    });
}