var SERVER_ADDRESS = "localhost";
var REQUESTURL_HEAD =
	"http://" + SERVER_ADDRESS + ":8080/multiple-dwr/dwr/jsonp/MultiplePrinter/";


function send_signin(user_name, dest_id){
	var httpObj = new XMLHttpRequest();
	var url = REQUESTURL_HEAD + "signin/";
	var dest_element;

	/*引数の処理*/
	if( dest_id != undefined ){
		dest_element = document.getElementById(dest_id);
	}else{
		console.log("send_signin: DEST_ID UNDEFINED ERROR");
		return;
	}
	if( user_name == undefined ){
		dest_element.innerHTML = "send_signin: USER_NAME UNDEFINED ERROR";
		return;
	}else{
		url = url + user_name;
	}

	httpObj.onreadystatechange = function () {
		var jsonObj;
        if( httpObj.readyState == 4 ){
        	jsonObj = JSON.parse(httpObj.responseText);
        	console.log("send_signin: json reply is " + JSON.stringify(jsonObj));
            dest_element.innerHTML = jsonObj.reply;
        }
	}
	httpObj.open("GET", url, true);

	httpObj.send(null);
}