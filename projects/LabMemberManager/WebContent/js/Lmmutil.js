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