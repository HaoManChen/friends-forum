var saveMetadata=(function(){
    var id = 0;
    var imgPath;
    function init() {
        $.extend.wuHint();
        modelHide();
        navigationAction();
        bindEvent();
    }
    //成功
    function success(msg) {
        $.extend.wuHintS(msg);
    }
    function modelHide() { $('#myModal').on('hide.bs.modal', function () {
        success('接下来请进行《对图片添加元数据》');
    });
    }
    //导航栏触发事件
    function navigationAction() {
        $('#save_img_click').on('click',function () {
            $('#save_img_click').addClass("active");
            $('#save_metadata_click').removeClass();
            $('#find_img_by_metadata_click').removeClass();
            $('#first_show').show();
            $('#second_show').hide();
            $('#third_show').hide();
        });
        $('#save_metadata_click').on('click',function () {
            getImgPath();
            $('#save_img_click').removeClass();
            $('#save_metadata_click').addClass("active");
            $('#find_img_by_metadata_click').removeClass();
            $('#first_show').hide();
            $('#second_show').show();
            $('#third_show').hide();
        });
        $('#find_img_by_metadata_click').on('click',function () {
            getMetadataType();
            $('#save_img_click').removeClass();
            $('#save_metadata_click').removeClass();
            $('#find_img_by_metadata_click').addClass("active");
            $('#first_show').hide();
            $('#second_show').hide();
            $('#third_show').show();
        });
    }
    function getImgPath() {
        var parme = {
        };
        $.ajax({
            url : '../getImgPath',
            dataType : 'json',
            data : parme,
            error : function () {
                alert("服务器连接失败");
            },
            success : function (result) {
                if (result.errCode=="000000"){
                    var data = result.data;
                    $('#select_img').empty();
                    var text = "<option value=\"\"> </option>";
                    $('#select_img').append(text);
                    jQuery.each(data, function(i,item){
                        var imgOld = item.imgPath;
                        var img = imgOld.substring(41);
                        text = " <option value=\""+imgOld+"\">"+img+"</option>";
                        $('#select_img').append(text);
                    });
                    }
            }
        });
    }
    function getMetadataType() {
        $.ajax({
            url : '../getMetadataType',
            dataType : 'json',
            data : "",
            error : function () {
            },
            success : function (result) {
                if (result.errCode == "000000"){
                    var data = result.data;
                    $('#select_type').empty();
                    jQuery.each(data, function(i,item){
                        var metaData = item.metadataType;
                        var text = " <option value=\""+metaData+"\">"+metaData+"</option>";
                        $('#select_type').append(text);
                    });

                }else{
                    alert(result.errMessage);
                }
            }
        });
    }
    function bindEvent() {
        $('#submit_img').on('click',function () {
            $('#submit_img_ok').show();
        });
        $('#close_submit').on('click',function(){
            $('#submit_img_ok').hide();
        })
        $('#select').on('click',function () {
            var parme = {
                type : $('#select_type').val(),
                name : $('#select_name').val()
            };
            $.ajax({
                url : '../selectByMetadata',
                data : parme,
                dataType :'json',
                error : function () {
                    alert("未连接成功服务器");
                },
                success : function (result) {
                    if (result.errCode == "000000"){
                        var data = result.data;
                        $('#select_form').empty();
                        jQuery.each(data,function(i,item) {
                            var imgUrl = item.imgUrl;
                            var time = new Date().getTime();
                            var path = "http://localhost:8080/friends-forum/getImg?path="+imgUrl+"&time="+time;
                            var name = imgUrl.substring(41);
                            var metadataType = item.metadataType;
                            var metadataName = item.metadataName;
                            var text = "            <tr>\n" +
                                "                <td><img src=\""+path+"\" class=\"img-thumbnail\" width=\"50\" height=\"50\"></td>\n" +
                                "                <td>"+name+"</td>\n" +
                                "                <td>"+metadataType+"</td>\n" +
                                "                <td>"+metadataName+"</td>\n" +
                                "            </tr>";
                            $('#select_form').append(text);
                        })

                    }else{
                        alert(result.errMessage);
                    }
                }
            });
        });
        $('#select_img').change(function () {
            var time = new Date().getTime();
            imgPath = $('#select_img').find("option:selected").val();
            var path = "http://112.74.175.123:8080/friends-forum/getImg?path="+imgPath+"&time="+time;
            $('#find_pic').attr('src',path);
        });
        $('#add').on('click',function () {
            id = id+1;
            var text = "    <div>\n" +
                "    <div class=\"input-group\">\n" +
                "        <span class=\"input-group-addon\">数据类型</span>\n" +
                "        <input type=\"text\" class=\"form-control\" placeholder=\"请输入元数据的类型\" id=\"type"+id+"\">\n" +
                "    </div>\n" +
                "    <div class=\"input-group\">\n" +
                "        <span class=\"input-group-addon\">数据内容</span>\n" +
                "        <input type=\"text\" class=\"form-control\" placeholder=\"请输入元数据的值\" id=\"num"+id+"\">\n" +
                "    </div>\n" +
                "    </div>"
            // var text = "数据类型<input type=\"text\" id=\"type"+id+"\">数据值<input type=\"text\" id=\"num"+id+"\"><br/>";
            $('#list div.metadata-list').append(text);
        });
        $('#submit_file').on('click',function () {
            // var file = $('#my_file').
        });
        $('#submit').on('click',function () {
            var arr = new Array();
           for (var m = 0;m<=id;m++){
               var type = $('#type'+m).val();
               var num = $('#num'+m).val();
               if (type==""||num==""){}
               else {
                   arr.push(type, num);
               }
           }
           var parme = {
               data : arr.toString(),
               path : imgPath
           };
           $.ajax({
               url : '../saveMetadata',
               data : parme,
               dataType : 'json',
               error : function () {
                   alert("服务器连接失败");
               },
               success : function (result) {
                   if (result.errCode=="000000"){
                       success("数据存储成功");
                   }else{
                       alert(result.errMessage);
                   }
               }
           })
        });

    }
    return {init:init};
}());
saveMetadata.init();