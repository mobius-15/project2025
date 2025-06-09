

var str ='Hello World';
var a=str.indexOf('Wo');
var b=str.substr(3,2);		//文字列[3]より2文字切り出す→'lo'
var c=str.replace('World','Japan');

var d=str.replace(/o/g,'@');		// 文字列内に'o'が出現する度に'@'に置き換える

document.write('a='+a+',b='+b+',c='+c+',d='+d);