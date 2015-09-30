var StateTable = {};

StateTable.states = [101, 103, 104, 102];
StateTable.defaultColumn = 3;

StateTable.appendTdElements = function(dest, pos, len, val){
	for(var i=0; i<len; i++){
		var newtd = $("<td></td>");
		newtd.attr("style", "text-align:center");
		if( i == pos ){
			dest.append(newtd.append(val));
		}else{
			dest.append(newtd);
		}
	}
};

StateTable.appendRow = function(id, name, inf){
	var row = $("#" + id);
	var position = this.getPosition(inf.stateCode);
	row.empty();
	row.append($("<td></td>").text(name));
	this.appendTdElements(row, position, this.states.length, "●");
};

StateTable.getPosition = function(code){
	for(var i=0; i<this.states.length; i++){
		if( code == this.states[i] ){
			return i;
		}
	}
	return this.defaultColumn;
};

StateTable.setStateRow = function(name, id){
	LmmManager.getMemberState(name, { callback: function(st){
		StateTable.appendRow(id, name, st);
	}});
};

StateTable.setColumn = function(args){
	this.states = new Array();
	for(var i=0; i<arguments.length; i++){
		states[i] = arguments[i];
	}
};
