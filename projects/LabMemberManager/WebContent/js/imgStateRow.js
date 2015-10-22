var ImgStateRow = {};

//列と状態コードの対応
ImgStateRow.codes = [101, 102, 103, 104];

//該当するコードの対応がない時の列番号
ImgStateRow.defaultColumn = 0;

//各列で使用するimgファイル
ImgStateRow.imgPaths = [];

//クリックされたときの動作の定義
ImgStateRow.onclickFunction = [];

ImgStateRow.createImgElement(imgpath, callback){
	var e_img = $("<img></img>");
	e_img.attr("alt", "img");
	e_img.attr("src", imgpath);
	if( callback !== undefined ){
		e_img.click(callback);
	}
	return e_img;
}

ImgStateRow.getStateColumn(code){
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

ImgStateRow.setColumn = function(args){
	this.states = new Array();
	for(var i=0; i<arguments.length; i++){
		this.states[i] = arguments[i];
	}
};

