//LabMemberManagerにログインの情報を登録する関数
//name: 名前(文字列), cbf: コールバック関数(関数)
function LmmLogin(name, cbf){
	if( cbf === undefined ){
		LmmManager.login(name);
	}else{
		LmmManager.login(name, {
			callback: cbf
		});
	}
}

//LabMemberManagerにログアウトの情報を登録する関数
//name: 名前(文字列), cbf: コールバック関数(関数)
function LmmLogout(name, cbf){
	if( cbf === undefined ){
		LmmManager.logout(name);
	}else{
		LmmManager.logout(name, {
			callback: cbf
		});
	}
}


//LabMemberManagerにからMemberInfoBean型オブジェクトの配列を取得する関数
//cbf: コールバック関数(関数)
function LmmGetMemberList(cbf){
	LmmManager.getMemberList({
		callback: cbf
	});
}


//LabMemberManagerにからMemberInfoのリストを取得し、
//idで指定されたtable要素に行を追加する関数
//id: table要素のid名(文字列, '#'はいらない)
function LmmAddMemberListToTable(id){
	LmmGetMemberList(function(list){
		for(var i=0; i<list.length; i++){
			$("#" + id).append(
				$("<tr></tr>").append(
					$("<td></td>").text(list[i].name),
					$("<td></td>").text(list[i].stateStr),
					$("<td></td>").text(list[i].loginDate),
					$("<td></td>").text(list[i].updateDate)
				)
			);
		}
	});
}


//labMemberManagerからレコードリストをActionRecordBean型配列で取得する関数
//cbf: コールバック関数(関数)
function LmmGetActionRecordList(cbf){
	LmmManager.getActionRecordList({
		callback: cbf
	});
}


//LabMemberManagerからの特定メンバーのレコードリストを
//ActionRecordBean型配列で取得する関数
//name: 名前(文字列), cbf: コールバック関数(関数)
function LmmGetActionRecordListByName(name, cbf){
	LmmManager.getMemberActionRecordList(name, {
		callback: cbf
	});
}


//ActionRecordBean型のレコードのオブジェクトを
//d_elemに指定されたtable要素のオブジェクトに行を追加する関数
//d_elem: table要素のid名(dom要素), obj: レコードの配列(ActionRecordBean配列)
function LmmAddRecordObjectToTable(d_elem, obj){
	for(var i=0; i<obj.length; i++){
		d_elem.append(
			$("<tr></tr>").append(
				$("<td></td>").text(obj[i].timeStamp),
				$("<td></td>").text(obj[i].name),
				$("<td></td>").text(obj[i].stateStr)
			)
		);
	}
}