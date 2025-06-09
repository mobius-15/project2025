
function drawCalendar(){
	var firstDay=new Date(2025,0,1);//月初めをインスタンス化
	for(var i=1;i<=35;i++){
				//週数*7
		var d=i-firstDay.getDay();
		if(d<1||d>31){
			d='';
		}
		document.write('<div class="day">'+d+'</div>');
	}
}