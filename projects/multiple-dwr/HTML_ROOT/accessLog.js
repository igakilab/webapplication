var LOG_TXT_URL = "logs.txt";
var LOG_DEST_ID = "show_area";

function getLog(dest_id, log_url){
	var httpObj = new XMLHttpRequest();
	var url;
	var dest_element;

	/*引数の処理*/
	if( dest_id == undefined ){
		dest_element = document.getElementById(LOG_DEST_ID);
	}else{
		dest_element = document.getElementById(dest_id);
	}
	if( log_url == undefined ){
		url = LOG_TXT_URL;
	}else{
		url = log_url;
	}

	/*リクエスト*/
	httpObj.onreadystatechange = function () {
		var response;
        if( httpObj.readyState == 4 ){
            response = httpObj.responseText;
            dest_element.innerHTML = response;
        }
	}
	httpObj.open("GET", url, true);

	httpObj.send(null);

}