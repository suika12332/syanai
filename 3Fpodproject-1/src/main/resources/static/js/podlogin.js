$("#login").on('click',function(){
	var id = $("#id").val();
	var pw = $("#pw").val();
	if(id===null || id===""){
		alert('君の社員番号は？');
		return;
	}
	if(pw===null || pw===""){
		alert('パスワード入力なしで行くつもり？');
		return;
	}else{
	 	document.podlog.submit();		
	}
	
})