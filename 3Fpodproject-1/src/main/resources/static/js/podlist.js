var hid = 1;
$('#zentailist').on('click', function(){
	
	
	/*if (ajaxRequest !== null) {
		 $("#zentailist").unbind("click");
	 }*/
	
	/*$.ajax({
		type:"post",
		url:"/podzentai",
		datatype:'json', 
		success: function(zentailist){
			if(zentailist !== null){
				alert("データーあり"+zentailist.length());
			}
			var kensu = 0;
			var result= '';
			result +='<table border="3" id="podlist"><tr><th>指示番号</th><th>会社名</th><th>品名</th><th>加工情報</th><th>種類</th>';
			result += '<th>枚数</th><th>出荷日</th><th>作業者</th><th>作業終了時間</th></tr>';
			
			
			
			for(var i =0; i< zentailist.length();i++){
				alert(idx+""+zentailist.length());
				
				result += '<tr id="listmokuroku"><td>'+zentailist[i].iraibango+'</td>';
				result += '<td>'+zentailist[i].kaisyamei+'</td>';
				result += '<td>'+'<a href="/podkuwashiku?title='+zentailist[i].title+'" name="title">'
				+val.title+'</a></td>';
				result += '<td>'+zentailist[i].kakoujouhou+'</td>';
				result += '<td>'+zentailist[i].shurui+'種'+'</td>';
				result += '<td>'+zentailist[i].maisu+'枚'+'</td>';
				result += '<td>'+zentailist[i].shukkabi+'</td>';
				result += '<td>'+zentailist[i].sagyousya+'さん'+'</td>';
				result += '<td>'+'<h>'+new Intl.DateTimeFormat('jp').format(new Date(zentailist[i].touroku_date))+'"</h></td></tr>';
				
				kensu += 1;
			
				//setTimeout(function(){isAjsx = false;},10000);
			}
			result += '</table>';
			
			if(hid <= 1){
				result += '<h id="joukenlistsize">'+"全部"+kensu+"件です！！"+'</h>';
			}
			$('#listsubete').empty();
			$('#listsubete').append(result);*/
		
			
	/*	},
		error: function(){
			alert('通信エラー');
			$("#zentailist").unbind("click");
			//setTimeout(function(){isAjsx = false;},10000);
		},
		
		complete:function(){
			close.modal();
			$('#listsubete').load(location.href+'#listsubete');
		}
	})
		*/
})

