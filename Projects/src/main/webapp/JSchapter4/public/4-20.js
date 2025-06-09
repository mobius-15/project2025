var a,b;
a=3;
b=f1(a,30);
b +=5;
document.write('b='+b+'<br>');

function f1(x,y){
	var p= 10+x+y;
	return p;
}

var name= 'Suzuki Ichiro';
var s1=name.toUpperCase();
document.write('s1='+s1+'<br>');

var s2=name.replace('Ichi','Sabu');
document.write('s2='+s2+'<br>');