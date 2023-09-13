$(document).ready(function () {
  $(".tab-button > li").click(function () {
    console.log("dhfsldkjfs");
    var idx = $(this).index();

    $(this).addClass("on").siblings().removeClass("on");

    $(".tabmenu .tab-content")
      .eq(idx)
      .addClass("on")
      .siblings(".tab-content")
      .removeClass("on");
  })
});

$(document).ready(function(){
  /*웹페이지 열었을 때*/
  $("#emptyH").show();
  $("#fullH").hide();

  /*emptyH을 클릭했을 때 fullH를 보여줌*/
  $("#emptyH").click(function(){
      $("#emptyH").hide();
      $("#fullH").show();
  });

  /*fullH를 클릭했을 때 emptyH을 보여줌*/
  $("#fullH").click(function(){
      $("#emptyH").show();
      $("#fullH").hide();
  });
});