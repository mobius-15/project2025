//5-1
var ary=new Array();
ary[0]=10;
ary[1]=20.5;
ary[2]='ABC';
document.write(ary[0]+', '+ary[1]+', '+ary[2]);

document.write('<br>');
//5-2
var ary=[10,20.5,'ABC'];
document.write(ary[0]+','+ary[1]+','+ary[2]);

document.write('<br>'+'<br>');
//5-3

var str='Fukuoka,Saga,Nagasaki';
var ary=str.split(',');		//文字列を,で区切り配列化する
document.write(ary[0]+' '+ary[1]+' '+ary[2]);

document.write('<br>');

//5-4
var ary2=['Fukuoka','Saga','Nagasaki'];
var str2=ary2.join(':');	//配列を:で連結する文字列に変える
document.write('str2='+str2);