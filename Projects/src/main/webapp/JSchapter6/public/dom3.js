function inputCheck() {
  let anser = prompt('答えを漢字で入力してください。', 'ここに答えを入力'); //('引数1','引数2')
  if (anser == '富士山') {
    alert('正解！');
  } else {
    alert('間違い！');
  }
}
