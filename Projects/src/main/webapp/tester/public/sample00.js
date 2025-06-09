

function menseki(teihen,takasa){
	var ret=document.write(teihen*takasa);
	return ret;
}
function answer(){
	let i=1;
	let ans=0;
	while(i<=10){
		ans+=i;
		i++;
	}
	document.write(ans);
}
function getMonth(){
	let theDay=new Date(2025,2,2);
	var o=theDay.getMonth();
	document.write(o);
}
function slice(){
	let text='ようこそWebページへ';
	var a=text.slice(-7,-1);	//sliceでは逆側に７文字
	document.write(a);
}