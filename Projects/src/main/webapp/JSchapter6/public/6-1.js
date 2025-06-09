//6-1,6-2
function click1(){
	var f =document.getElementById("color");	//"color"を基準に要素を取得
	if(f.innerHTML=='ブルー'){
		f.innerHTML='ホワイト';
		f.style.backgroundColor='white';
//		f.style.left='200px';
		//文字列、要素の書き換え
	}else{f.innerHTML='ブルー';
		f.style.backgroundColor='blue';
//		f.style.left='0px';
	}
}
function click2(){
	var e=document.getElementById("color");
	if(e.textContent=='ブルー'){
		e.textContent='ホワイト';

		//文字列のみ書き換え
	}else{e.textContent='ブルー';

	}
}