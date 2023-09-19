$(function(){

//직접입력 인풋박스 기존에는 숨어있다가

    $('#phone').hide();



    $("#selectType").change(function() {



        //직접입력을 누를 때 나타남

        if($("#selectType").val() == "1") {

            $("#phone").show();
            $("#email").hide();

        }  else {

            $("#phone").hide();
            $("#email").show();

        }



    })



});