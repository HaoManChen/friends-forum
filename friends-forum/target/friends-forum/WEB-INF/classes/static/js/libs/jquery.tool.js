/*
 * 依赖于jquery，weui
 * 
 */

;(function($, window, document, undefined){
	
	var pluginName = "wuLoading",
		defaults = {
			propertyName: "value",
		},
		html = "<div id=\"loadingToast\" class=\"weui_loading_toast\" style=\"display: none; font-size: 16px;\">"+
				    "<div class=\"weui-mask_transparent\"></div>"+
				    "<div class=\"weui-toast\">"+
				        "<div class=\"weui_loading\">"+
				            "<i class=\"weui-loading weui-icon_toast\"></i>"+
				        "</div>"+
				        "<p class=\"weui-toast__content\">数据加载中</p>"+
				    "</div>"+
				"</div>";
	
	function Plugin(element, options){
		this.element = element;
		this.options = $.extend({}, defaults, options);
		this._defaults = defaults;
		this.init();
	}
	
	Plugin.prototype = {
			
		init: function(){
			//初始化逻辑
			$("body").append(html);
		},
		
		show: function(text){
			text = text || "数据加载中";
			$("#loadingToast")
				.show()
				.find("p.weui_toast_content")
					.text(text);
		},
		
		hide: function(){
			$("#loadingToast").hide();
		}
	}
	
	$.extend[pluginName] = function (options, args) {
		var data = $(document.querySelector("body")).data("_" + pluginName);
		if(!$.data(document.querySelector("body"), "_" + pluginName)){
			data = $.data(document.querySelector("body"), "_" + pluginName, new Plugin(this, options));
		}
		if(typeof options == 'string') {
			data[options].apply(data, [].concat(args));
		}
	}
})(jQuery, window, document);
;(function($, window, document, undefined){
	
	var pluginName = "wuHint",
		defaults = {
			propertyName: "value",
		},
		html = "<div id=\"toast\" style=\"display: none; font-size: 16px;\">"+
				    "<div class=\"weui-mask_transparent\"></div>"+
				    "<div class=\"weui-toast\">"+
				        "<i class=\"weui-icon-success-no-circle weui-icon_toast\"></i>"+
				        "<p class=\"weui-toast__content\">已完成</p>"+
				    "</div>"+
				"</div>";
	
	function Plugin(element, options){
		this.element = element;
		this.options = $.extend({}, defaults, options);
		this._defaults = defaults;
		this.init();
	}
	
	Plugin.prototype = {
			
		time: null,
		
		init: function(){
			//初始化逻辑
			$("body").append(html);
			
			this.setIcon(this.options.icon);
			
			this.setText(this.options.text);
			
			//this.show();
		},
		
		setIcon: function(icon){
			//success error warn
			val = icon || "success";
			switch(val){
			case "success":
				$("#toast").find("i").removeClass().addClass("weui-icon-success-no-circle weui-icon_toast");
				break;
			case "error":
				$("#toast").find("i").removeClass().addClass("weui-icon_toast weui-icon-warn");
				break;
			case "warn":
				$("#toast").find("i").removeClass().addClass("weui-icon_toast weui-icon-warn weui-icon_msg-primary");
				break;
			}
		},
		
		setText: function(text){
			text = text || "已完成";
			$("#toast").find("p.weui-toast__content").text(text);
		},
		
		show: function(){
			$("#toast").fadeIn();
			if(Plugin.prototype.time) clearTimeout(Plugin.prototype.time);
			Plugin.prototype.time = setTimeout(function(){
				$("#toast").fadeOut();
			}, 1800);
		}
	};
	
	function wuHintS(text){
		text = text || "已完成";
		$("#toast").find("i").removeClass().addClass("weui-icon-success-no-circle weui-icon_toast");
		$("#toast").find("p.weui-toast__content").text(text);
		Plugin.prototype.show();
	}
	
	function wuHintE(text){
		text = text || "已完成";
		$("#toast").find("i").removeClass().addClass("weui-icon_toast weui-icon-warn");
		$("#toast").find("p.weui-toast__content").text(text);
		Plugin.prototype.show();
	}
	
	function wuHintW(text){
		text = text || "已完成";
		$("#toast").find("i").removeClass().addClass("weui-icon_toast weui-icon-warn weui-icon_msg-primary");
		$("#toast").find("p.weui-toast__content").text(text);
		Plugin.prototype.show();
	}
	
	$.extend[pluginName] = function (options, args) {
		var data = $(document.querySelector("body")).data("_" + pluginName);
		if(!$.data(document.querySelector("body"), "_" + pluginName)){
			data = $.data(document.querySelector("body"), "_" + pluginName, new Plugin(this, options));
		}
		if(typeof options == 'string') {
			data[options].apply(data, [].concat(args));
		}
	};
	
	$.extend.wuHintS = wuHintS;
	$.extend.wuHintE = wuHintE;
	$.extend.wuHintW = wuHintW;
	
})(jQuery, window, document);
