$(document).ready(function () {
  $("#image-upload").on("change", handleImgFileSelect);
  $(".remove-btn").on("click", handleImgFileReset);
  $("#select-order").on("change", changeTabMenu);
});

function handleImgFileSelect(e) {
  let files = e.target.files;
  let reader = new FileReader();
  reader.onload = function (e) {
    $("#image").attr("src", e.target.result);
    $("#image").css("display", "block");
  };

  reader.readAsDataURL(files[0]);
}

function handleImgFileReset() {
  $("#image").attr("src", "/css/icons/iconmonstr-book-26-240.png");
  $("#image-upload").val("");
}

function changeTabMenu() {
  let selectLink = $("#select-order").val();
  $(".tab-contents").hide();
  $(selectLink).show();
  console.log(selectLink);
}


/*
function showModal(ISBN) {
    $.ajax({
    type : 'get', // 타입 (get, post, put 등등)
    url : 'getBook.do', // 요청할 서버url
    async : true, // 비동기화 여부 (default : true)
    dataType : 'text', // 데이터 타입 (html, xml, json, text 등등)
    data : { // 보낼 데이터 (Object , String, Array)
    "ISBN" : ISBN
  },
    success : function(result) { // 결과 성공 콜백함수
      console.log(result)
      $("#editModal").html(result);
  },
    error : function(request, status, error) { // 결과 에러 콜백함수
    console.log(error)
  }
  })
}*/



function modalOn(ISBN) {

  $('.modal-backdrop').remove();
  $("#background_modal").css("display","block");
  // 모달 열기
  /*$('#editModal').modal('show');*/

  // 컨트롤러로부터 데이터 가져오기 (Ajax 요청)
  $.ajax({
    url: 'getBook.do', // 컨트롤러의 URL
    type: 'get', // GET 또는 POST 요청
    dataType: 'html', // 받아올 데이터 타입 (HTML 또는 원하는 형식)
    async: false,
    data: {ISBN: ISBN},

    success: function (data) {
      // 받아온 데이터를 모달 내용에 추가
      $('.edit-container').html(data);
    },
    error: function (xhr, textStatus, errorThrown) {
      // 에러 처리 코드
      console.log('Error: ' + textStatus);
    }
  });
}
function closeModal() {
  $("#background_modal").css("display","none");
}

function modalOn(ISBN) {

  $('.modal-backdrop').remove();
  $("#background_modal").css("display","block");
  // 모달 열기
  /*$('#editModal').modal('show');*/

  // 컨트롤러로부터 데이터 가져오기 (Ajax 요청)
  $.ajax({
    url: 'getBook.do', // 컨트롤러의 URL
    type: 'get', // GET 또는 POST 요청
    dataType: 'html', // 받아올 데이터 타입 (HTML 또는 원하는 형식)
    async: false,
    data: {ISBN: ISBN},

    success: function (data) {
      // 받아온 데이터를 모달 내용에 추가
      $('.edit-container').html(data);
    },
    error: function (xhr, textStatus, errorThrown) {
      // 에러 처리 코드
      console.log('Error: ' + textStatus);
    }
  });
}



