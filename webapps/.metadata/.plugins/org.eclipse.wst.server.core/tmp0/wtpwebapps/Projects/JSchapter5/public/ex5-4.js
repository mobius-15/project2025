//ex5-4
var abc=[10,50,70];
for(var v of abc){document.write(v+'<br>');}

//ex5-5
var data={'A01':{jap:75,soc:58,math:92,sci:83,eng:76},
			'A02':{jap:60,soc:65,math:80,sci:75,eng:70},
				'A03':{jap:76,soc:75,math:77,sci:76,eng:75}};
	document.write('A03(sci):'+data['A03'].sci+'pt<br>');
	document.write('A02(math):'+data.A02.math+'pt<br>');
	
	var sum3=0;
	var sum2=0;
	for(var a in data['A03']){sum3+=data['A03'][a];}
document.write('A03(total):'+sum3+'pt<br>');
	for(var b in data.A02){sum2+=data['A02'][b];}
	document.write('A02(total):'+sum2+'pt<br>');
	