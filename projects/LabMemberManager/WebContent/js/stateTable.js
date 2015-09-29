var StateTable = {

		states : [101, 103, 104, 102],

		appendTdElements: function(dest, pos, len, val){
			for(var i=0; i<len; i++){
				if( i == pos ){
					dest.append($("<td></td>").append(val));
				}else{
					dest.append($("<td></td>"));
				}
			}
		},

		appendRow: function(id, name, inf){
			var row = $("#" + id);
			var position = StateTable.getPosition(inf.stateCode);
			row.empty();
			row.append($("<td></td>").text(name));
			StateTable.appendTdElements(row, position, StateTable.states.length, "●");
		},

		getPosition: function(code){
			for(var i=0; i<StateTable.states.length; i++){
				if( code == StateTable.states[i] ){
					return i;
				}
			}
			return 3;
		},

		setStateRow: function(name, id){
			LmmManager.getMemberState(name, { callback: function(st){
				StateTable.appendRow(id, name, st);
			}});
		}
};