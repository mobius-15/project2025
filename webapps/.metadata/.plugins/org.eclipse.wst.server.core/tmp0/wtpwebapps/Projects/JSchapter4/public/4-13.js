

var c = 100;
var b = sample1(3,5);
document.write(b +','+ c);
function sample1 (arg1,arg2){
	 c = arg1 * arg2;		// arg1*arg2=3*5
	return c;				//グローバル変数100が返される
}