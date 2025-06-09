

var c = 100;
var b = sample1 (3,5);
document.write(b+','+c);
function sample1 (arg1,arg2){
	var c = arg1 * arg2;	//ローカル変数
	return c;			//3*5=15が返される
}