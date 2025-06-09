
var a = 7;			//グローバル変数
var b;
sample1();
sample2(200);
sample3(a,10);

b=sample4(a,b);

document.write(b);

function sample1(){
	a = 100;		//a=7→a=100
}

function sample2(x){ //sample2(200)→x=200
	a = x;	//a=200を意味する
}

function sample3(x,y){  //sample3(a,10)→(200,10)
	b = x + y;		//b=210
}

function sample4(arg1,arg2){
	var c= arg1 + arg2;	//arg1+arg2=a+b→200+210
	return c;				//c=410
}