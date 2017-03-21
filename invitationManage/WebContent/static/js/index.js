debugger
	var pageIndex = 1;//帖子列表的当前页码
	var r_pageIndex = 1;//回复列表的当前页码
	var displayCount = 4;//展示的条数
	var totalPage = 0;//展示所有帖子的总页数
	var title="";//搜索框内容
	var id = 0;//传递到回复列表中的帖子ID
	
	window.onload = query;//第一次加载
	
	$("#search").on('click',function(){
		title = $('#title').val();
		query();
	});
	
	//链接利用ajax,避免把参数暴露在地址栏上
	$("a[data-info='query']").on('click',function(){
		var tr = $(this).parent().parent();//表示一个tr
		id = tr.find("td:eq(0)").html();//得到ID
		var data = {
				pageIndex:r_pageIndex,
				displayCount:displayCount,
				id:id
		}
		$.ajax({
			url:'queryReply',
			data:data,
			type:'post',
			dataType:'text',
			success:function(result){
				$('body').html(result);
			}
		});
		
	});
	
	//删除
	$('a[data-info="delete"]').on('click',function(){
		id = $(this).parent().parent().find("td:eq(0)").html();
		var value = confirm("确定删除该条帖子及相关回复？");
		if(value== true){
			$.ajax({
				url:'deleteMessage',
				data:{
					id:id
				},
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.success == "success"){
						//$('#tip').html("删除成功");
						alert("删除成功");
						var totalCount = result.totalCount;
						alert("totalCount:"+totalCount);
						if(totalCount % displayCount == 0){
							pageIndex = parseInt($('.currentPage').html()) - 1;
						}else{
							pageIndex = parseInt($('.currentPage').html());
						}
						query();
					}else{
						$('#tip').html("删除失败");
					}
					
				}
			});
		}
		
	});
	
		
	//查询帖子信息
	function query(){
		$.ajax({
			url:'queryMessage',
			type:'post',
			dataType:'text',
			data:{
				pageIndex:pageIndex,
				displayCount:displayCount,
				title:title
			},
			success:function(result){
				$('body').html(result);
				pageIndex = parseInt($('.currentPage').html());//记录当前页
				totalPage = parseInt($('.hidden').html());//记录总页数
				
			}
		});
	}

	//首页
	function firstPage(page){
		pageIndex = parseInt($('.currentPage').html());//记录当前页
		if(pageIndex == 1) return;
		pageIndex = 1;
		query();
	}
	//上一页
	function prePage(page){
		pageIndex = parseInt($('.currentPage').html());//记录当前页
		if(pageIndex == 1) return;
		pageIndex = pageIndex - 1;
		query();
	}
	//下一页
	function nextPage(){
		pageIndex = parseInt($('.currentPage').html());//记录当前页
		totalPage = parseInt($('.hidden').html());//记录总页数
		if(pageIndex >= totalPage) return;
		pageIndex = pageIndex + 1;
		query();
	}
	//尾页
	function lastPage(){
		pageIndex = parseInt($('.currentPage').html());//记录当前页
		totalPage = parseInt($('.hidden').html());//记录总页数
		if(pageIndex == totalPage) return;
		pageIndex = totalPage;
		query();
		
	}