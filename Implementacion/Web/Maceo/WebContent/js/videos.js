var jVideos = {
	
	buscar: function() {
        jUtils.executing("result");
        jUtils.hiding("message");
        $.ajax({
            url: "/videos/Buscar.do",
            type: "post",
            dataType: "html",
            data: $.param($("input[type=text],input[type=radio]:checked", $("#form"))),
            error: function(hr){
                jUtils.hiding("result");
                jUtils.showing("message", hr.responseText);
            },
            success: function(html) {
                jUtils.showing("result", html);
            }
        });		
	},
	
	ver: function(obj) {
        jUtils.hiding("main", false);
        jUtils.executing("response");
        jUtils.hiding("message");
        
        $("#span-" + $(obj).closest("p").find("input[name=nroVideo]").val()).html($.i18n.prop('visto'));
        
        $.ajax({
            url: "/videos/Ver.do",
            type: "post",
            dataType: "html",
            data: $.param($(obj).closest("p").find("input[type=hidden]")),
            error: function(hr){
                jUtils.hiding("response");
                jUtils.showing("main");
                jUtils.showing("message", hr.responseText);
            },
            success: function(html) {
                jUtils.showing("response", html);
            }
        });		
	},
	
	volver: function() {
		jUtils.hiding("response");
        jUtils.showing("main");
	}
		
};