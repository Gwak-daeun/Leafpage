$(document).ready(function () {
  $("#image-upload").on("change", handleImgFileSelect);
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

$(".remove-btn").on("click", function () {
  $("#image").attr("src", "/css/icons/iconmonstr-book-26-240.png");
  $("#image-upload").val("");
});
