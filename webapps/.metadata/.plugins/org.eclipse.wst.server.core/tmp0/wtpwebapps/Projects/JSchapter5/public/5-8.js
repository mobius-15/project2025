//5-8
var obj={name:'taro',age:21,tel:'090-1111-2222'};
obj[0]=100;
obj['x']=200;
obj.y=300;
document.write(obj.name+' ');
document.write(obj['tel']+'<br>');
document.write(obj[0]+' ');
document.write(obj.x+' ');
document.write(obj['y']+'<br>');
document.write(obj.age);

document.write('<br>');
//5-9

var ary=[{name:'taro',age:21,tel:'090-1111-2222'},
		{name:'hanako',age:19,tel:'090-3333-4444'},
		{name:'jiro',age:18,tel:'090-5555-6666'}];
document.write(ary[0].name+','+ary[0].age+','+ary[0].tel+'<br>');
document.write(ary[1].name+', '+ary[1].age+','+ary[1].tel+'<br>');
document.write(ary[2].name+', '+ary[2].age+','+ary[2].tel+'<br>');

document.write('<br>');

var obj2={name:'taro',age:21,score:{math:80,english:75,japanese:87}};
document.write(obj.name+','+obj.age+'歳'+'<br>');
document.write('math:'+obj.score+'点'+'<br>');
document.write('english:'+obj.score+'点'+'<br>');
document.write('japanese:'+obj.score+'点'+'<br>');