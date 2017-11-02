$(function () {
    changeImg();
   $(document).on('click','#login',function () {
       var parme = {
           loginName : $('#login_name').val(),
           loginPassword : $('#login_password').val(),
           authCode : $('#auth_code').val()
       };
       $.ajax({
           url : '../login',
           data : parme,
           error : function () {
               alert('服务器异常');
           },
           success : function (result) {
               result = $.parseJSON(result);
               var errCode = result.errCode;
               if (errCode =='000000'){
                   window.location.href = "/friends-forum/html/saveMetadata.html"
               }else{
                   alert("错误码："+errCode+"错误信息："+result.errMessage);
               }
           }
       });
   });
   $(document).on('click','#new_auth_code_img',function () {
       changeImg();
   })
   function changeImg() {
       var time = new Date().getTime();
       var path = "http://localhost:8080/friends-forum/authCodeImg?"+time;
       $('#auth_code_img').attr('src',path);
   }
});