var day1= new Date(2021,6,23,20,0,0);

var year=day1.getFullYear();
var month=day1.getMonth();
var date=day1.getDate();
var day=day1.getDay();
var hour=day1.getHours();
var minute=day1.getMinutes();
var second=day1.getSeconds();

var week=['日','月','火','水','木','金','土'];

document.write('五輪開会時刻<br>');
document.write(year + '/');
document.write((month +1)+'/');
document.write(date +'');
document.write('('+ week[day]+')');
document.write(hour +':');
document.write(minute +':');
document.write(second );