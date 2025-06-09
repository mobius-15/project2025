
function search(){
	var txt=document.form2.txt.value;
	var cnt =txt.split('x').length-1;
	document.getElementById('results').innerHTML=cnt;
}
function search2(){
	var txt2=document.form3.txt2.value;
	var cnt2=txt2.split('y').length -1;
	document.getElementById('results2').innerHTML=cnt2;
}