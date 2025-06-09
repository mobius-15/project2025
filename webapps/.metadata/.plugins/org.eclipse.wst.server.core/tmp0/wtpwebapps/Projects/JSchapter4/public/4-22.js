
for(var n=0;n<5;n++){
	document.write(n);
	if(n%2==0){
		document.write(':偶数<br>');
		}else{document.write(':奇数<br>')
	}
}
document.write('<br>');
for(var j=0;j<5;j++){
	for(var i=0;i<4;i++){
		if(i==1||j==2){
		document.write('●');
		}else{
			document.write('〇');
		}
	}
	document.write('<br>');
}