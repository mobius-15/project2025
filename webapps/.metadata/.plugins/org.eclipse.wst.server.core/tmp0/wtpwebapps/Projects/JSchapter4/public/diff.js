function diff() {
	var text = 'ようこそとぴあITスクールへ'
	var a = text.substr(4,9);		/* 1:文字列を取り出す開始位置（どこから取り出しはじめる）
	2:取り出す開始位置を１番目としたときの取り出す文字数（何文字取り出す）*/
	var b = text.substring(4,13);	/*1:文字列を取り出す開始位置（どこから取り出しはじめる）
	2:文字列を取り出す終了位置（どこまで取り出す）*/
	var c = text.slice(4,13);		/*substringと同じ */
	document.write("a:"+a+"<br>");
	document.write("b:"+b+"<br>");
	document.write("c:"+c+"<br>");
	
	var d = text.substr(4);	/*第二引数が省略されるときは、開始位置から最後まで抜き出す。(いずれも)*/
	var e = text.substring(4);
	var f = text.slice(4);
	document.write("d:"+d+"<br>");
	document.write("e:"+e+"<br>");
	document.write("f:"+f+"<br>");
	
	var g = text.substr(-5);	//文字列の後ろから数えた位置が開始位置になる
	var h = text.substring(-5);		//開始位置を「0」と認識する
	var i = text.slice(-5);	    //文字列の後ろから数えた位置が開始位置になる
	document.write("g:"+g+"<br>");
	document.write("h:"+h+"<br>");
	document.write("i:"+i+"<br>");
	
	var j = text.substr(-5,4);	/*1:文字列の後ろから数えた位置が開始位置になる,
	2:取り出す開始位置を１番目としたときの取り出す文字数（何文字取り出す）*/
	var k = text.substring(-5,4);/*1:開始位置を「0」と認識する,
	 2:頭から数えた時の文字列を取り出す終了位置（どこまで取り出す）*/
	var l = text.slice(-5,4);	/*エラー */
	document.write("j:"+j+"<br>");
	document.write("k:"+k+"<br>");
	document.write("l:"+l+"<br>");
	
	var m = text.substr(-5,-1);		/*エラー*/
	var n = text.substring(-5,-1);	/*エラー*/
	var o = text.slice(-5,-1);		/*1:文字列の後ろから数えた位置が開始位置になる,
	2:文字列後ろから数えた位置が終了位置になる*/
	document.write("m:"+m+"<br>");
	document.write("n:"+n+"<br>");
	document.write("o:"+o+"<br>");
	
}
