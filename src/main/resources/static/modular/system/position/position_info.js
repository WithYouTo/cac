
/**
 * 初始化公司管理详情对话框
 */
var PositionInfoDlg = {
    postionInfoData : {}
};

//界面初始化
$(function() {		
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/property/selectTemplateTypes?configCode=POSITION_LEVEL_",
		async : false,
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, value){
				var option = "<option value='"+value.id+"'>"+value.text+"</option>";
				$("#positionLevel").append(option);
			})
		}
	});
	
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/property/selectSubCompanies",
		async : false,
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, value){
				var option = "<option value='"+value.code+"'>"+value.name+"</option>";
				$("#company").append(option);
			})
		}
	});
	if($("#companyCodeInfo").val()){
		$("#company").val($("#companyCodeInfo").val());
		$("#positionLevel").val($("#positionLevelInfo").val());
	}
	
});


/**
 * 关闭此对话框
 */
PositionInfoDlg.close = function() {
    parent.layer.close(window.parent.Position.layerIndex);
}


PositionInfoDlg.add=  function(){
	var positionName = $('#positionName').val();
	var positionLevel = $('#positionLevel').val();
	var companyCode = $('#company').val(); 
	
	if(companyCode == ''){
		Feng.info('请选择公司！');
		return;
	}
	if(positionName == ''){
		Feng.info('请输入职位名称！');
		return;
	}
	if(positionLevel == ''){
		Feng.info('请选择职位级别！');
		return;
	}
	
	var params = {
			companyCode: companyCode,
			positionName :positionName,
			positionLevel :positionLevel
	}
	
	 $.ajax({
			type : 'POST',
			url : Feng.ctxPath +'/position/insertNewPosition',
			async : false,
			data : params,
			dataType : 'json',
			beforeSend : function(){
				
			},
			success : function(data){  
				if(data.flag =="1"){
					Feng.success('新增成功');		
					window.parent.Position.table.refresh();
				    PositionInfoDlg.close();	
					return;
				}else if(data.flag =="-1"){
					Feng.error('参数为空');
					return;
				}else if(data.flag =="2"){
					Feng.error('职位名称已经存在');
					return;
				}else{
					Feng.error('新增失败');
					return;
				}
			}
	});
   
}


PositionInfoDlg.update =function(){
	var positionName = $('#positionName').val();
	var positionLevel = $('#positionLevel').val();
	var companyCode = $('#company').val();
	var tbbb05Id = $('#tbbb05Id').val();
	var oldName = $('#oldName').val();
	
	if(companyCode == ''){
		Feng.info('请选择公司！');
		return;
	}
	if(positionName == ''){
		Feng.info('请输入职位名称！');
		return;
	}
	if(positionLevel == ''){
		Feng.info('请选择职位级别！');
		return;
	}
	
	var params = {
			tbbb05Id:tbbb05Id,
			positionName :positionName,
			positionLevel :positionLevel,
			companyCode:companyCode,
			oldName:oldName
	}
    $.ajax({
			type : 'POST',
			url : Feng.ctxPath +'/position/updatePositionInfo',
			async : false,
			data : params,
			dataType : 'json',
			beforeSend : function(){
				
			},
			success : function(data){     				
				if(data.flag =="1"){
					Feng.success('修改成功！');
					window.parent.Position.table.refresh();
				    PositionInfoDlg.close();
					return;				
				}else if(data.flag =="-1"){
					Feng.error('参数为空');
					return;
				}else if(data.flag =="2"){
					Feng.error('职位名称已经存在');
					return;
				}else{
					Feng.error('修改失败');
					return;
				}
			}
	});
}


