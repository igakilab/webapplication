var Lmmifc = {};

Lmmifc.login = function(name, cbf){
	if( cbf === undefined ){
		LmmManager.login(name);
	}else{
		LmmManager.login(name, {callback: cbf});
	}
}


Lmmifc.logout = function(name, cbf){
	if( cbf === undefined ){
		LmmManager.logout(name);
	}else{
		LmmManager.logout(name, {callback: cbf});
	}
}


Lmmifc.getMemberInfo = function(name, cbf){
	LmmManager.getMemberInfo(name, {callback: cbf});
}


Lmmifc.getMemberInfoList = function(cbf){
	LmmManager.getMemberList({callback: cbf});
}


Lmmifc.getActionRecord = function(cbf){
	LmmManager.getActionRecordList({callback: cbf});
}


Lmmifc.getMemberActionRecord = function(name, cbf){
	LmmManager.getMemberActionRecordList(name, {callback: cbf});
}