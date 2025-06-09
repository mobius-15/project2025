//5-11
document.write('for-of'+'<br>');

var ary=[1,3,5,7];
for(var val of ary){document.write(val+',');}
//5-12
document.write('<br>'+'for-in(連想配列)'+'<br>');

var obj={name:'taro',age:21,tel:'090-1111-2222'};
for(var k in obj){document.write(k+':'+obj[k]+'<br>');}