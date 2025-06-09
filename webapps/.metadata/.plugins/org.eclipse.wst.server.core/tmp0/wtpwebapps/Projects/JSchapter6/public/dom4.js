document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('lunch').addEventListener('change', lunchCheck);
});

function lunchCheck() {
  var confirm = '';
  if (document.getElementById('ramen').checked==true) {	//checked==true:チェックされた場合
    confirm += "ラーメンをご希望ですね。";
  }
  if (document.getElementById('friedRice').checked==true) {
    confirm += "チャーハンをご希望ですね。";
  }
  if (document.getElementById('dumplings').checked==true) {
    confirm += "餃子をご希望ですね。";
  }

  document.getElementById('answer').textContent = confirm;
}
