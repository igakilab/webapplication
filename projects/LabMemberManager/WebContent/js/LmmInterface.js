function LmmLogin(name, cbf){
	if( cbf === undefined ){
		LmmManager.login(name);
	}else{
		LmmManager.login(name, {
			callback: cbf
		});
	}
}


function LmmGetMemberList(cbf){
	LmmManager.getMemberList({
		callback: cbf
	});
}


function LmmAddMemberListToTable(id){
	LmmGetMemberList(
		function(list){
			var nrow;

			for(var i=0; i<list.length; i++){
				$("#" + id).append(
					$("<tr></tr>").append(
						$("<td></td>").text(list[i].name),
						$("<td></td>").text(list[i].stateStr)
					)
				);
			}
		}
	);
}