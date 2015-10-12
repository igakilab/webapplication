var Lmmifc = {};

Lmmifc.systemInit = function(){
	LmmManager.init();
}

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


Lmmifc.getMemberState = function(name, cbf){
	LmmManager.getMemberState(name, {callback: cbf});
}


Lmmifc.getRegistedId = function(cbf){
	LmmManager.getRegistedId({callback: cbf});
}


Lmmifc.getMemberNameList= function(cbf){
	LmmManager.getMemberNameList({callback: cbf});
}


Lmmifc.getRecordList = function(cbf){
	LmmManager.getRecordList({callback: cbf});
}


Lmmifc.getMemberRecordList = function(cbf){
	LmmManager.getMemberRecordList(name, {callback:cbf});
}