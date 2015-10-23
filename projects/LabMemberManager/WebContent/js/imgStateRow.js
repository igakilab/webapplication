var ImgStateRow = {};

//列と状態コードの対応
ImgStateRow.codes = [101, 102, 103, 104];

//該当するコードの対応がない時の列番号
ImgStateRow.defaultColumn = 0;

//各列で使用するimgファイル
ImgStateRow.imgPaths = [];
ImgStateRow.imgPaths_act = [];

//クリックすると状態が変わるようにするかどうか
ImgStateRow.registButton = true;

//登録完了時のコールバック関数
ImgStateRow.registCallback = null;

ImgStateRow.createImgElement = function(imgpath, callback){
	var e_img = $("<img></img>");
	e_img.attr("alt", "img");
	e_img.attr("src", imgpath);
	if( callback !== undefined && callback !== null ){
		e_img.click(callback);
	}
	return e_img;
}

ImgStateRow.getStateColumn = function(code){
	for(var i=0; i<this.states.length; i++){
		if( Array.isArray(this.states[i]) ){
			for(var j=0; j<this.states[i].length; j++){
				if( this.states[i][j] == code ) return i;
			}
		}else{
			if( this.states[i] == code ) return i;
		}
	}
	return this.defaultColumn;
};

ImgStateRow.appendTdElements = function(dest, pos, name){
	var len = this.imgPaths.length;

	for(var i=0; i<len; i++){
		var newtd = $("<td></td>");
		var cfunc = null;

		if( this.registButton == true && name !== undefined ){
			cfunc = function(){
				ImgStateRow.registState(name,
					ImgStateRow.getStateCodeForRegist(i),
					ImgStateRow.registCallback
				);
			}
		}

		if( i == pos ){
			newtd.attr("class", "isr_data_act");
			newtd.append(this.createImgElement(this.imgPaths_act[i], cfunc));
			dest.append(newtd);
		}else{
			newtd.attr("class", "isr_data");
			newtd.append(this.createImgElement(this.imgPaths[i], cfunc));
			dest.append(newtd);
		}
	}

};

ImgStateRow.appendTableData = function(dest, name, inf){
	var td_name = $("<td></td>");
	td_name.attr("class", "isr_name");
	td_name.text(name);
	dest.append(td_name);

	this.appendTdElements(dest, this.getStateColumn(inf.stateCode), name)

}

ImgStateRow.appendToRow = function(elem_id, name, cbf){
	$("#" + elem_id).empty();
	LmmManager.getMemberState(name, function(inf){
		ImgStateRow.appendTableData($("#" + elem_id), name, inf);
		if( cbf !== undefined ) cbf();
	});
}

ImgStateRow.appendToTable = function(elem_id, name, cbf){
	LmmManager.getMemberState(name, function(inf){
		var new_row = $("<tr></tr>");
		ImgStateRow.appendTableData(new_row, name, inf);
		$("#" + elem_id).append(new_row);
		if( cbf !== undefined ) cbf();
	});
}

ImgStateRow.registState = function(id, code, cbf){
	LmmManager.registState(id, code, function(reply){
		if( cbf !== undefined && cbf == null ){
			cbf(reply);
		}
	});
}

ImgStateRow.getStateCodeForRegist = function(colm){
	if( jQuery.isArray(this.codes[colm]) ){
		return this.codes[colm][0];
	}else{
		return this.codes[colm];
	}
}


//コードの設定
ImgStateRow.setColumn = function(args){
	this.states = new Array();
	for(var i=0; i<arguments.length; i++){
		this.states[i] = arguments[i];
	}
};

ImgStateRow.setImgPaths = function(paths){
	this.imgPaths = paths;
}

ImgStateRow.setActiveImgPaths = function(paths){
	this.imgPaths_act = paths;
}

//
