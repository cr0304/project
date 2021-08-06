$(function (){
    $('#pw2').blur(function (){
      if ($('#pw').val()!=$('#pw2').val()){
          if ($('#pw2').val()!=''){
              alert("비밀번호가 일치하지 않습니다.");
              $('#pw2').val('');
              $('#pw2').focus();
          }
      }
    })
})
