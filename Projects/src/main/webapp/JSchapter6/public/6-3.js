
function goukei(){
	var a=document.getElementById('text-a').value;
	var b=document.getElementById('text-b').value;
	var ret = parseInt(a)+parseInt(b);
	document.getElementById('goukei').innerHTML=ret;
}
function calc(){
	var form=document.form1;
	var x= parseInt(form.text_x.value);		//"-"は使用不可
	var y=parseInt(form.text_y.value);
	var v=form.keisan.value;
	var ret2;
		switch(v){
			case '+':
			ret2=x+y;
				break;
			case '-':
			ret2=x-y;
				break;
			case '*':
			ret2=x*y;
				break;
			case '/':
			ret2=x/y;
				break;
			
		}
		var elem=document.getElementById('result');
		elem.style.color=form.color.value;
		elem.style.fontSize='20px';
		
		if(form.big.checked){
			elem.style.fontSize='40px';
		}
		elem.innerHTML=ret2;
}

