var id = $('.hidden').val();
$('input[type="button"]').on('click',function(){
	alert(id);
	var content = $('textarea').val();
	if(content == "" || content == undefined){
		alert("回复内容不能为空");
		return;
	}
	var author = $('#author').val();
	$.ajax({
		url:'addReply',
		data:{
			content:content,
			author:author
		},
		type:'post',
		dataType:'text',
		success:function(result){
			if(result == "success"){
				alert("添加成功！");
				fresh();
			}else{
				alert("添加失败");
			}
		}
			
	});
});

function fresh(){
	alert(id);
	$.ajax({
		url:'queryReply',
		data:{
			pageIndex:1,
			displayCount:4,
			id:id
		},
		type:'post',
		dataType:'text',
		success:function(result){
			$('body').html(result);
		}
	});
}