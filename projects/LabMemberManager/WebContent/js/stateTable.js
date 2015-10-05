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
		if( Array.isArray(this.states[i]) ){
			for(var j=0; j<this.states[i].length; j++){
				if( this.states[i][j] == code ){
					return i;
				}
			}
		}else{
			if( this.states[i] == code ){
				return i;
			}
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
		this.states[i] = arguments[i];
	}
};

StateTable.appendRowElem = function(jq_elem, name, inf){
	var position = this.getPosition(inf.stateCode);
	jq_elem.empty();
	jq_elem.append($("<td></td>").text(name));
	this.appendTdElements(jq_elem, position, this.states.length, "●");
};

StateTable.setStateRowElem = function(name, jq_elem, callback){
	LmmManager.getMemberState(name, {
		callback: function(st){
			StateTable.appendRowElem(jq_elem, name, st);
			if( callback !== undefined ){
				callback();
			}
		}
	});
}
