//5-5
var ary=[10,20.5,'ABC'];
for(var i=0; i<ary.length;i++){
	document.write('ary['+i+']='+ary[i]+'<br>');
}
//5-6
	document.write('<br>'+'while文'+'<br>');
	var a=0;
	while(a<ary.length){
		document.write('ary['+a+']='+ary[a]+'<br>');
	a++;
}
//5-7
	document.write('<br>'+'continue,break'+'<br>');
	var ary2=[5,-10,3,4,-7,9,0,6,8];
	for(var b=0;b<ary2.length;b++){
	if(ary2[b]<0){
		continue;
	}
	if(ary2[b]==0){
		break;
	}
	document.write(ary2[b]+',');
}

