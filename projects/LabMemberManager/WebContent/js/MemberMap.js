var MemberMap = {};

MemberMap.members = [];

MemberMap.backgroundImg = null;

MemberMap.TRIGHT = 100;
MemberMap.TLEFT = 101;

MemberMap.defaultFillColor = "rgba(0, 0, 0, 1)";

//ユーザが変更してよい変数
//ログイン状態と判別するコード
MemberMap.loginCode = 101;
//ログイン状態時の文字列と色
MemberMap.loginLabel = "在室";
MemberMap.loginColor = null;
//その他の状態時の文字列と色
MemberMap.otherLabel = "退室";
MemberMap.otherColor = null;
//アイコンの大きさ
MemberMap.memberRectWidth = 30;
MemberMap.memberRectHeight = 30;
//フォントの種類
MemberMap.fontType = null;


MemberMap.addMember = function(n0, dx0, dy0, ts0){
	this.members[this.members.length] = {
		name: n0, dx: dx0, dy: dy0, text_side: ts0
	}
}

MemberMap.clearMember = function(){
	this.members = new Array();
}


MemberMap.checkLoginCode = function(code){
	if( Array.isArray(this.loginCode) ){
		for(var i=0; i<this.loginCode.length; i++){
			if( this.loginCode[i] == code ) return true;
		}
	}else{
		if( this.loginCode == code ) return true;
	}
	return false;
}

MemberMap.drawMemberIcon = function(ctx, name, cx, cy, name_str_side, login){
	var dw = this.memberRectWidth;
	var dh = this.memberRectHeight;
	var dx = cx - (dw / 2);
	var dy = cy - (dh / 2);
	var rect_color;
	if( login == true ){
		rect_color = this.loginColor;
	}else{
		rect_color = this.otherColor;
	}
	var nt_dx, nt_dy, nt_align;
	if( name_str_side == MemberMap.TLEFT ){
		nt_dx = dx - 5;
		nt_dy = dy + dh - 5;
		nt_align = "right";
	}else{
		nt_dx = dx + dw + 5;
		nt_dy = dy + dh - 5;
	}
	var s_str;
	if( login == true ){
		s_str = this.loginLabel;
	}else{
		s_str = this.otherLabel;
	}
	if( this.fontType != null ){
		ctx.font = this.fontType;
	}

	if( rect_color != null ){
		ctx.fillStyle = rect_color;
		ctx.fillRect(dx, dy, dw, dh);
		ctx.fillStyle = this.defaultFillColor;
	}
	ctx.strokeRect(dx, dy, dw, dh);
	ctx.textAlign = "left";
	ctx.fillText(s_str, dx +3 , dy + dh -3, dw-6);
	ctx.textAlign = nt_align;
	ctx.fillText(name, nt_dx, nt_dy);
}

MemberMap.drawMember = function(ctx, idx){
	var mdx = this.members[idx].dx;
	var mdy = this.members[idx].dy;
	var mna = this.members[idx].name;
	var tsd = this.members[idx].text_side;

	LmmManager.getMemberState(mna, function(inf){
		var login = MemberMap.checkLoginCode(inf.stateCode);
		MemberMap.drawMemberIcon(ctx, mna, mdx, mdy, tsd, login);
	});
}

MemberMap.drawBackgroundImg = function(ctx, img_path, callback, dw, dh){
	var img = new Image();
	if( arguments.length >= 5 ){
		img.onload = function(){
			ctx.drawImage(img, 0, 0, dw, dh);
			callback();
		};
	}else{
		img.onload = function(){
			ctx.drawImage(img, 0, 0);
			callback();
		};
	}
	img.src = img_path;
}

MemberMap.draw = function(id, canvas_width, canvas_height){
    var cnvs = document.getElementById(id);
    var ctx = cnvs.getContext("2d");
    ctx.beginPath();
    ctx.fillStyle = this.defaultFillColor;

    if( arguments.length >= 3 ){
    	ctx.clearRect(0, 0, canvas_width, canvas_height);
    }

    if( this.backgroundImg != null ){
    	if( arguments.length >= 3 ){
	    	this.drawBackgroundImg(ctx, this.backgroundImg, function(){
	    		MemberMap.drawMemberList(ctx);
	    	}, canvas_width, canvas_height);
    	}else{
	    	this.drawBackgroundImg(ctx, this.backgroundImg, function(){
	    		MemberMap.drawMemberList(ctx);
	    	});
    	}
    }else{
    	this.drawMemberList(ctx);
    }
}

MemberMap.drawMemberList = function(ctx){
	for(var i=0; i<this.members.length; i++){
		this.drawMember(ctx, i);
	}
}
