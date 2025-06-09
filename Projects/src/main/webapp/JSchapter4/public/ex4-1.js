//ex4-1
var data ='living-room';

document.write(data.length+'<br>');
document.write(data.replace('living','bed')+'<br>');
document.write('1'+(1+parseInt('1'))+'<br>');

document.write('<br>');
//ex4-2
var a=100,b=10,c=20;
a++;
a+=b;
if(a>110){
	c+=10;		
}
document.write('c='+c);

document.write('<br>'+'<br>');

//ex4-3
var abc=10;
for(var n=0; n<5;n++){
	document.write('n='+n+'<br>');
	abc++;
}
document.write('abc='+abc);

document.write('<br>'+'<br>');

//ex4-4
var p=100,q=200;
var r=test1(100,p,q);
document.write(r);

function test1(x,y,z){
	var a=10;
	a=a+x+y+z;
	return a;
}
