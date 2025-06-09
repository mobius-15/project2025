var date = new Date(2022, 5, 1);
var year = date.getFullYear();
var month = date.getMonth();

var schedule = {
			'6/13':'Report1',
			'6/15':'HomeWork1',
			'6/17':'HomeWork2',
			'6/22':'Report2'};

function drawCalendar() {
	var html = '';
	var firstDay = new Date(year, month, 1);
	for(var i=1; i<=35; i++) {
		var day = new Date(year, month, i-firstDay.getDay());
		var m = day.getMonth();
		var d = day.getDate();
		var className = 'day';
		if (i%7 == 1) className += ' sunday';
		if (i%7 == 0) className += ' saturday';
		if (m != month) className += ' other-month';
		for(var sch in schedule) {
			if (sch==(m+1)+'/'+d) {
				d += '<div class="schedule-text">' + schedule[sch] +  '</div>';
			}		
		}
		html +='<div class="'+className+'">'+d+'</div>';
	}
	document.write(html);
}
