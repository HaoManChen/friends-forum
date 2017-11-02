$(function () {
    //发送手机验证码
    $(document).on('click','#send_auth_code_message',function () {
        var phone = $('#phone').val();
        if (!isPhone(phone)){
            alert("手机格式不正确");
        }else{
        var date = new Date();
        var parme  = {
            phone : $("#phone").val(),
            date : date
        } ;
        $.ajax({
            url:'../sendPhoneAuthCode',
            data:parme,
            error: function () {
                alert("服务器未连接成功");
            },
            success: function (result) {
                result = $.parseJSON(result);
                var errCode = result.errCode;
                if (errCode == "000000"){
                    alert("短信发送成功");
                }else {

                    alert("错误码："+errCode+"错误信息："+result.errMessage);
                }
            }
        });
        }
    });
    //发送虚拟号获取请求
    $(document).on('click','#random_virtual_number',function () {
        var date = new Date();
        var parme = {
            date : date
        };
        $.ajax({
            url : '../sendRandomVirtualNumber',
            data: parme,
            error: function () {
                alert("服务器未连接成功");
            },
            success:function (result) {
                result = $.parseJSON(result);
                var errCode = result.errCode;
                if (errCode =="000000"){
                    $("#virtual_number").val(result.randomVirtualNumber);
                    $("#virtual_number").attr("disabled",true);
                }else{
                    alert("错误码："+errCode+"错误信息："+result.errMessage);
                }
            }
        });
    });
    //发送提交注册请求
    $(document).on('click','#submit',function () {
        if (!check()){
            return "";
        }
        var date = new Date();
        var parme ={
            date :date,
            loginName : $('#login_name').val(),
            loginPassword : $('#login_password').val(),
            name : $('#name').val(),
            email : $('#email').val(),
            phone : $('#phone').val(),
            telephone : $('#telephone').val(),
            apartment : $('#apartment').val(),
            identityCardNumber : $('#identity_card_number').val(),
            userName : $('#user_name').val(),
            virtualNumber : $('#virtual_number').val(),
            gmLevel : $('#gm_level option:selected').val(),
            gmLevelAuthCode : $('#gm_level_auth_code').val(),
            messageAuthCode : $('#message_auth_code').val()
        }
        $.ajax({
            url : '../submitRegister',
            data : parme,
            error : function () {
                alert('服务器未连接成功');
            },
            success:function (result) {
                result = $.parseJSON(result);
                var errCode = result.errCode;
                if (errCode == "000000"){
                    alert("注册成功");
                }else{
                    alert("错误码："+errCode+"错误信息："+result.errMessage);
                }
            }
        });
    });
    $('#login_password2').on('input',function () {
        var password1 = $('#login_password').val();
        var password2 = $('#login_password2').val();
        if (password1 != password2){
            $('#password_same').text("两次密码不同");
        }else{
            $('#password_same').text("");
        }
    });
    $('#email').on('input',function () {
       var email = $('#email').val();
       if (!isEmail(email)){
           $('#email_false').text("邮箱格式不正确");
       }else{
           $('#email_false').text("");
       }
    });
    $('#login_name').on('input',function () {
        var loginName = $('#login_name').val();
        if (!isLoginName(loginName)){
            $('#login_name_false').text("用户名长度在6到18位字符之间");
        }else{
            $('#login_name_false').text("");
        }
    });
    $('#login_password').on('input',function () {
        var loginPassword = $('#login_password').val();
        if (!isLoginPassword(loginPassword)){
            $('#login_password_false').text("密码长度在6到18位字符之间");
        }else{
            $('#login_password_false').text("");
        }
    });
    $('#identity_card_number').on('input',function () {
       var identity = $('#identity_card_number').val();
       if (!isIdentityCardNumber(identity)){
           $('#identity_card_number_false').text("请输入正确格式的身份证");
       }else {
           $('#identity_card_number_false').text("");
       }
    });
    //验证邮箱是否是标准写法
    function isEmail(str) {
        var reg = /^[A-Za-z0-9._%+-]{5,}@(?:[A-Za-z0-9-]+\.)+[A-Za-z\d]{2,6}$/;
        return reg.test(str);
    }
    //验证登陆账号长短
    function isLoginName(str) {
        var reg = /^[A-Za-z0-9._%+-]{6,19}$/;
        return reg.test(str);
    }
    //验证手机号是否标准
    function isPhone(str) {
        var reg = /^[0-9]{11}$/;
        return reg.test(str);
    }
    //验证身份证是否标准
    function  isIdentityCardNumber(str) {
        var reg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
        return reg.test(str);
    }
    //验证密码长度是否符合
    function isLoginPassword(str) {
        var reg = /^[A-Za-z0-9._%+-]{6,19}$/;
        return reg.test(str);
    }
    function check() {

        if ($('#password_same').text()!=""||$('#email_false').text()!=""||$('#login_name_false').text()!=""||$('#login_password_false').text()!=""||$('#identity_card_number_false').text()!=""){
            alert("您所填写的有格式错误");
            return false;
        }

        var loginName = $('#login_name').val();
        var loginPassword = $('#login_password').val();
        var name = $('#name').val();
        var email = $('#email').val();
        var phone = $('#phone').val();
        var telephone = $('#telephone').val();
        var apartment = $('#apartment').val();
        var identityCardNumber = $('#identity_card_number').val();
        var userName = $('#user_name').val();
        var virtualNumber = $('#virtual_number').val();
        var gmLevel = $('#gm_level option:selected').val();
        var gmLevelAuthCode = $('#gm_level_auth_code').val();
        var messageAuthCode = $('#message_auth_code').val();
        if (loginName ==""||loginPassword==""||name==""||email==""||phone==""||telephone==""||apartment==""||identityCardNumber==""||userName==""||virtualNumber==""||gmLevel==""||gmLevelAuthCode==""||messageAuthCode==""){
            alert("表格中有遗漏项未填写，请完整填写");
            return false;
        }
        return true;
    }
});