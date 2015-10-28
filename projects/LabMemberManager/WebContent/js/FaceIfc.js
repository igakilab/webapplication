FaceIfc = {};

FaceIfc.ImageList = [];

FaceIfc.updateImageList = function(cbf){
	FaceCollector.getImg(function(list){
		var cnt = 0;
		for(var i=list.length - 1; i>=0; i--){
			FaceIfc.ImageList[cnt] = {
				url: list[i].url, timeStamp: list[i].timeStamp
			};
			cnt++;
		}
		cbf();
	});
}

FaceIfc.updateImageListLatest = function(cnt, cbf){
	FaceCollector.getImgLatest(cnt, function(list){
		var cnt = 0;
		for(var i=list.length - 1; i>=0; i--){
			FaceIfc.ImageList[cnt] = {
				url: list[i].url, timeStamp: list[i].timeStamp
			};
			cnt++;
		}
		cbf();
	});
}

FaceIfc.getImageList = function(idx){
	return this.ImageList[idx];
}

FaceIfc.getImageListLength = function(){
	return this.ImageList.length;
}