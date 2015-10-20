var TestPageLink = {};

TestPageLink.links = [
    {text:"lmmtest.html", href:"lmmtest.html"},
    {text:"personalData.html", href:"personalData.html"},
    {text:"records.html", href:"records.html"},
    {text:"statistics.html", href:"statistics.html"}
]

TestPageLink.appendToList = function(jq_elem){
	var new_elem;
	for(var i=0; i<this.links.length; i++){
		new_elem = $("<a></a>");
		new_elem.attr("href", this.links[i].href);
		new_elem.text(this.links[i].text);
		jq_elem.append($("<li></li>").append(new_elem));
	}
}
