//5-13
var ary=[{carmodel:'suv',price:200,color:'white'},{carmodel:'truck',price:400,color:'black'},{carmodel:'public',price:300,color:'red'}];

document.write('Model:'+ary[1].carmodel);
document.write('  Color:'+ary[1].color+'<br>');


var sum=0;
for(var obj of ary){
	sum+= obj.price;
}
document.write('Summary:$'+sum);

document.write('<br>'+'<br>');

//5-14
var score={'A001':{japanese:80,math:70,english:60},'A002':{japanese:75,math:60,english:80},'A003':{japanese:90,math:70,english:85},};
var sum=0;
document.write('Summary:<br>')
	for(var id in score){ sum=score[id].japanese+score[id].math+score[id].english;
	document.write(id+':'+sum+'pt<br>');
	}