var data={
	A001:{name:'Sato',age:21},
	A002:{name:'Yamada',age:20},
	A003:{name:'Suzuki',age:19},
	A004:{name:'Tanaka',age:22},
};

function dispList(){
	var html='';
	for(var key in data){
		html += key + ':';
		html +=data[key].name+',';
		html +=data[key].age +' old<br>';
	}
	document.getElementById('list').innerHTML=html;
}