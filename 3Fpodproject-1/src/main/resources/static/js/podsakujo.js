$(".sakujo").on('click',function(){
	var atai = $("input[name=kakushibango]").val();
	var id = {"id":atai};
	alert("指示番号は"+atai+"です");
	$.ajax({
		type:"get",
		url:"/podsakujo",
		data:id,
		success:function(data){
			alert(data+"です！！");
		},error:function(){
			alert('ajax失敗');
		}
	})
})