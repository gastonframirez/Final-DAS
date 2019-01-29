function setLocale(filename, lang, root) {
	root = root == undefined ? '' : root;
    $.i18n.properties({
        name: filename,
        path:  root + '/js/properties/',
        mode: 'map',
        language: lang
    });
}

function changeLanguage(lang){
	var curr = document.location.href;
	if(curr.indexOf('?lang')!=-1 || curr.indexOf('?')==-1){
		var url = document.location.href.split('?')[0]+"?lang="+lang;
	}else{
		 var url = document.location.href+"&lang="+lang;
		 
	}
	document.location = url;
}