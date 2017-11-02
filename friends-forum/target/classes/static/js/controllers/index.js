/**
 * Created by 陈灏 on 2017/8/15
 * 权限判断
 */
define(['jquery'],function ($) {
    //初始化菜单栏
    function initMenu() {
    $.ajax({
        url:'./session/initMenu?t='+new Date().getTime(),
        method:'get',
        dataType:'json',
        error:function (XMLHttpRequest,textStatus,errorThrown) {
            if(XMLHttpRequest.status =='403'){
                window.location.href = pageHost+'html/login.html';
            }
            alert("服务器异常");
        },
        success:function (result) {
            if (result.errCode != "000000") {
                alert(result.errMessage);
                window.location.href = pageHost+'html/login.html';
            }
        },
        dataType:'json'
    });
    }

    return {
        initMenu : initMenu
    };
})