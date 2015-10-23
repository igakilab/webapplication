var graf = {};

graf.rei = function() {
  	var g = new html5jp.graph.vbar("sample");
  	if( ! g ) { return; }
  	var items = [
    	["商品A", 20, 58, 40, 14, 38, 20, 40],
    	["商品B", 10, 14, 58, 80, 70, 90, 20],
    	["商品C", 10, 14, 58, 80, 70, 90, 20],
    	["商品D", 10, 14, 58, 80, 70, 90, 20],
    	["商品E", 10, 14, 58, 80, 70, 90, 20]
  	];
  	var params = {
    	x: ["曜日", "日", "月", "火", "水", "木", "金", "土"],
    	y: ["注文数(個)"]
  	};
  	g.draw(items, params);
}
