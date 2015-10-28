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
ImgStateRow.registCallback = function(){};

//アイコンの大きさ
ImgStateRow.imgWidth = 84;
ImgStateRow.imgHeight = 46;

ImgStateRow.createImgElement = function(imgpath, onc){
	var e_img = $("<img></img>");
	e_img.attr("alt", "img");
	e_img.attr("src", imgpath);
	e_img.attr("width", this.imgWidth);
	e_img.attr("height", this.imgHeight);
	if( onc !== undefined && onc !== null ){
		e_img.attr("onclick", onc);
	}
	return e_img;
}

ImgStateRow.getStateColumn = function(code){
	for(var i=0; i<this.codes.length; i++){
		if( Array.isArray(this.codes[i]) ){
			for(var j=0; j<this.codes[i].length; j++){
				if( this.codes[i][j] == code ) return i;
			}
		}else{
			if( this.codes[i] == code ) return i;
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
			var c_name = name;
			var c_code = this.getStateCodeForRegist(i);
			cfunc = function(){

			}
			cfunc = "ImgStateRow.registState(\"" + name + "\", " + this.getStateCodeForRegist(i) + ")";
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

ImgStateRow.appendToRow = function(elem_id, name){
	$("#" + elem_id).empty();
	LmmManager.getMemberState(name, function(inf){
		ImgStateRow.appendTableData($("#" + elem_id), name, inf);
	});
}

ImgStateRow.appendToTable = function(elem_id, name){
	LmmManager.getMemberState(name, function(inf){
		var new_row = $("<tr></tr>");
		ImgStateRow.appendTableData(new_row, name, inf);
		$("#" + elem_id).append(new_row);
	});
}

ImgStateRow.registState = function(id, code){
	LmmManager.registState(id, code, function(reply){
		ImgStateRow.registCallback(reply);
	});
}

ImgStateRow.getStateCodeForRegist = function(colm){
	if( Array.isArray(this.codes[colm]) ){
		return this.codes[colm][0];
	}else{
		return this.codes[colm];
	}
}


//コードの設定
ImgStateRow.setColumn = function(args){
	this.codes = new Array();
	for(var i=0; i<arguments.length; i++){
		this.codes[i] = arguments[i];
	}
};

ImgStateRow.setImgPaths = function(paths){
	this.imgPaths = paths;
}

ImgStateRow.setActiveImgPaths = function(paths){
	this.imgPaths_act = paths;
}

//
