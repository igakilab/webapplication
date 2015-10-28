GanttManager = {};

GanttManager.defaultCustomClass = "ganttOrange";

GanttManager.defaultProfile = {
	source: [],
	navigate: "scroll",
	maxScale: "month",
	minScale: "hours",
	itemsPerPage: 10,
	months : ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
	dow : ["日", "月", "火", "水", "木", "金", "土"],
	onItemClick: function(data) {
		alert("進捗バーがクリックされました。");
	},
	onAddClick: function(dt, rowId) {
		alert("空白部分がクリックされました。");
	},
	onRender: function() {
		if (window.console && typeof console.log === "function") {
			console.log("chart rendered");
		}
	}
}

GanttManager.getGanttRecord = function(name, callback){
	LmmManager.getGanttRecord(name, function(ret){
		var grec = {};
		grec.name = name;
		grec.recs = [];
		for(var i=0; i<ret.length; i++){
			grec.recs[i] = ret[i];
		}
		callback(grec);
	});
}

GanttManager.convertSource = function(grec){
	var new_source = {};
	new_source.name = grec.name;
	new_source.desc = "";
	new_source.values = this.convertValues(grec);
	return new_source;
}

GanttManager.convertValues = function(grec){
	var new_vals = [];
	for(var i=0; i<grec.recs.length; i++){
		new_vals[i] = {
			from: "/Date(" + (grec.recs[i].dateFromSec * 1000) + ")/",
			to: "/Date(" + (grec.recs[i].dateToSec * 1000) + ")/",
			label: grec.recs[i].label,
			customClass: this.defaultCustomClass
		}
	}
	return new_vals;
}