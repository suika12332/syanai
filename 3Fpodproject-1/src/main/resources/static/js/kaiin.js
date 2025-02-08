	if($('#id').val() == null || $('#id').val() == ""){
		alert('idを入力してください。');
		return false;
	} 
	if($('#pw').val() == null || $('#id').val() == ""){
		alert('パスワードを入力してください。');
		return false;
	} 
	if($('#name').val() == null || $('#name').val() == ""){
		alert('名前を入力してください。');
		return false;
	} 
	else{
		return true;
	}
