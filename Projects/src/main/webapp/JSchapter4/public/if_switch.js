
/*if(条件式１){
	条件式１が真（true）の場合の処理
}else if(){
	条件式１が偽（false）で条件式２が真（true）の場合の処理
}else{
	上記にある条件をいずれも満たさなかった時の処理
} */

function if_uranai() {
	var random = Math.floor(Math.random()*4);
	if(random==0){
		document.write('占い結果は凶'+'<br>');
	}else if(random==1){
		document.write('占い結果は吉'+'<br>');
	}else if(random==2){
		document.write('占い結果は中吉'+'<br>');
	}else{
		document.write('占い結果は大吉'+'<br>');
	}
}
/*switch(条件の値){
  case 値１：
    条件値と値１が等しい場合に実行する処理
    break;
  case 値２：
    条件値と値２が等しい場合に実行する処理
    break;
  default:
    上記の条件に当てはまらなかった場合に実行する処理
} */

function switch_uranai(){
	var random = Math.floor(Math.random()*4);
	switch(random){
	  case 0:
		document.write('占い結果は凶'+'<br>');
		break;
	  case 1:
		document.write('占い結果は吉'+'<br>');
		break;
	case 2:
		document.write('占い結果は中吉'+'<br>');
		break;
	default:
		document.write('占い結果は大吉'+'<br>');
	}
}
