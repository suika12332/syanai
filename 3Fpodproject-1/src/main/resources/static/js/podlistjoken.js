var hid = 1;
$('#joken').on('click', function joken(){
	//$("#joken").unbind("click",handler);
	/*if(isAjsx){
		return;
	}*/
	
	
	if($("#iraibango1").val()==null&&$("#iraibango1").val()==""&&$("#iraibango2").val()==null&&$("#iraibango2").val()==""&&$("#iraibango3").val()==null&&$("#iraibango3").val()==""){
		$("#iraibango1").focus();
		alert("指示書の番号を入力してください！！");
		return;
	}
	if($("#kakoujouhou").val()==null||$("#kakoujouhou").val()=="none"){
		
		alert("加工情報を選択してください！！");
		return;
	}
	if($("#shurui").val()==null||$("#shurui").val()==''){
		$("#shurui").focus();
		alert("種類を入力してください！！");
		return;
	}
	
	if($("#shukkabi").val()==null||$("#shukkabi").val()==0||$("#shukkabi2").val()==null||$("#shukkabi2").val()==0){
		
		alert("出荷日を選択してください！！");
		return;
	}
	if($("#sagyousya").val()==null||$("#sagyousya").val()=="none"){
		
		alert("作業者の名前は何ですか？");
		return;
	}
	
	 /*if (ajaxRequest !== null) {
		 $("#joken").unbind("click");
	 }*/
	
	var queryString = $('form[name=joken]').serialize();
	//isAjsx = true;
	
	$.ajax({
		type:"post",
		url:"/podjoken",
		data:queryString,
		datatype:'json', 
		success: function(zentailist){			
			
			var kensu = 0;
			var result= '';
			result +='<table border="3" id="podlist"><tr><th>指示番号</th><th>会社名</th><th>品名</th><th>加工情報</th><th>種類</th>';
			result += '<th>枚数</th><th>出荷日</th><th>作業者</th><th>作業終了時間</th></tr>';
					
			$.each(zentailist,function(idx,val){
				//alert(idx+""+val.sagyousya);
				result += '<tr id="listmokuroku"><td>'+val.iraibango+'</td>';
				result += '<td>'+val.kaisyamei+'</td>';
				result += '<td>'+'<a href="/podkuwashiku?title='+val.title+'" name="title">'
				+val.title+'</a></td>';
				result += '<td>'+val.kakoujouhou+'</td>';
				result += '<td>'+val.shurui+'種'+'</td>';
				result += '<td>'+val.maisu+'枚'+'</td>';
				result += '<td>'+val.shukkabi+'</td>';
				result += '<td>'+val.sagyousya+'さん'+'</td>';
				result += '<td>'+'<h>'+new Intl.DateTimeFormat('jp').format(new Date(val.touroku_date))+'"</h></td></tr>';
				
				kensu += 1;
				

	
				//setTimeout(function(){isAjsx = false;},10000);
			})	
			//$("#joken").bind("click",handler);
			//$('#podlist').remove();
			result += '</table>';
			if(hid <= 1){
				result += '<h id="joukenlistsize">'+"全部"+kensu+"件です！！"+'</h>';
			}
			$('#listsubete').empty();
			$('#listsubete').append(result);
			
		},
		error: function(){
			alert('通信エラー');
			//$("#joken").unbind("click");
			//setTimeout(function(){isAjsx = false;},10000);
		},
		/*complete:function(){
			close.modal();
			$('#kensakujoken').load(location.href+'#kensakujoken');
		}*/
	})
	
	

})



