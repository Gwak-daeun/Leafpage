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
