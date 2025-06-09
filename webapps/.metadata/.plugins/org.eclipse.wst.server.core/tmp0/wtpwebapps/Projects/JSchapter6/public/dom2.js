document.addEventListener('DOMContentLoaded', function () {
	document.getElementById('btn').addEventListener('click',inputCheck);
});

function inputCheck() {
  var answerBox = document.getElementById('answerText').value;	
  if(answerBox=='富士山'){	//htmlの'answerText'から取得,valueで情報を保存
  	alert('正解です');	//window.alert("");
  }else{
  	alert('間違いです');
  }
}
