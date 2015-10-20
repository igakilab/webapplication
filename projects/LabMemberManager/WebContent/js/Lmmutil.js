var Lmmutil = {};

Lmmutil.addActionRecordListToTable = function(jq_elem, ary_obj){
	for(var i=0; i<ary_obj.length; i++){
		jq_elem.append(
			$("<tr></tr>").append(
				$("<td></td>").text(ary_obj[i].timeStamp),
				$("<td></td>").text(ary_obj[i].name),
				$("<td></td>").text(ary_obj[i].stateCode),
				$("<td></td>").text(ary_obj[i].stateStr)
			)
		);
	}
}


Lmmutil.addMemberInfoToTable = function(jq_elem, info){
	if( jQuery.isArray(info) ){
		for(var i=0; i<info.length; i++){
			jq_elem.append(
				$("<tr></tr>").append(
					$("<td></td>").text(info[i].name),
					$("<td></td>").text(info[i].stateCode),
					$("<td></td>").text(info[i].stateStr),
					$("<td></td>").text(info[i].updateDate)
				)
			);
		}
	}else{
		jq_elem.append(
			$("<tr></tr>").append(
				$("<td></td>").text(info.name),
				$("<td></td>").text(info.stateCode),
				$("<td></td>").text(info.stateStr),
				$("<td></td>").text(info.updateDate)
			)
		);
	}
}


Lmmutil.createLabelRow = function(label){
	var nrow = $("<tr></tr>");
	for(var i=0; i<label.length; i++){
		nrow.append($("<th></th>").text(label[i]));
	}
	return nrow;
}

Lmmutil.stateTable = [];

Lmmutil.updateStateTable = function(){
	LmmManager.getStateList(function(list){
		stateTable = list;
	});
}

Lmmutil.getStateName = function(code){
	var name = "unknown";
	for(var i=0; i<stateTable.length; i++){
		if( stateTable[i].code == code ){
			name = stateTable[i].name;
		}
	}
	return name;
}
