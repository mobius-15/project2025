function isWeaponized(){
	
	var weaponizedCheck=document.getElementById('weaponized');
	var a2aCheck=document.getElementById('A2A');
	var a2aSelect=document.getElementById('A2AWeapons');
	var equip=document.getElementById('equipment');
	var confirm='';
	
	if(weaponizedCheck.checked){
		if(a2aCheck.checked==true){confirm="自衛可能";
			a2aSelect.style.display="block";
		}else{confirm="対地装備のみ";a2aSelect.style.display="none";}
	}else{confirm+"非武装";a2aSelect.style.display="none";
	}
	equip.innerHTML=confirm;
}
function jet_type(){
	var type=document.getElementById('aircraft_type').value;
	var element=document.getElementById('result');
	var ret='武装は？';
	
		
	if(type.indexOf('F')!=-1){	ret='対空装備が可能。';		
	}else if(type.indexOf('A')!=-1){	ret='装備を確認。';
		
	}
	element.innerHTML=ret;
}