//ex5-1
var abc=[10,20,30,40];
document.write(abc[2]+' ');
document.write(abc[3]+'<br>');

//ex5-2
var test1={apple:100,orange:20};
document.write(test1['orange']+'<br>');
				//(test1.orange);
//ex5-3
var abcd=[10,30,50,40,20,80,60];
var max=-999;
for(var i=0;i<abcd.length;i++){
	if(abcd[i]>max){max=abcd[i];	//前値より大きければ更新
	}
}document.write('max='+max+'<br>');

