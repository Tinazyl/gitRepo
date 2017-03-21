	var pageIndex = 1;
	var displayCount = 4;
	var totalPage = 0;
	var id = $('input[class="hidden"]').html();
	function queryReply(){
		var data = {
				pageIndex:pageIndex,
				displayCount:displayCount,
				id:id
		}
		debugger
		$.ajax({
			url:'queryReply',
			data:data,
			type:'post',
			dataType:'text',
			success:function(result){
				$('body').html(result);
				totalPage = parsInt($('span[class="hidden"]').html());
				pageIndex = parseInt($('.currentPage').html());
				alert(pageIndex);
			}
		});
	}
	//添加回复
	$('#addReply').on('click',function(){
		
	});
	
	
	//返回上一页
	$('#back').on('click',function(){
		window.history.back(-1);
	});

	//首页
	function firstPage1(page){
		if(pageIndex == 1) return;
		pageIndex = 1;
		queryReply();
	}
	//上一页
	function prePage(page){
		if(pageIndex == 1) return;
		pageIndex = pageIndex - 1;
		queryReply1();
	}
	//下一页
	function nextPage(){
		if(pageIndex >= totalPage) return;
		pageIndex = pageIndex + 1;
		queryReply1();
	}
	//尾页
	function lastPage(){
		alert(pageIndex);
		if(pageIndex == totalPage) return;
		pageIndex = totalPage;
		queryReply1();
		
	}