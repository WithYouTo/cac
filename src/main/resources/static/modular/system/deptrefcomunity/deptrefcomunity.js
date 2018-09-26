/**
 * 公司管理管理初始化
 */
var DeptRefCom = {
    id: "DeptRefComtable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DeptRefCom.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'tbaa01Id', visible: false, align: 'center', valign: 'middle'},
            {title: '部门ID', field: 'tbbb04Id', visible: true, align: 'center', valign: 'middle'},
            {title: '部门层级', field: 'layer', visible: true, align: 'center', valign: 'middle'},
            {title: '部门名称', field: 'deptName', visible: true, align: 'center', valign: 'middle'},
            {title: '部门所辖小区', field: 'areaName', visible: true, align: 'center', valign: 'middle'},
            {title: '上级部门ID', field: 'parentDeptId', visible: true, align: 'center', valign: 'middle'},
            {title: '上级部门名称', field: 'parentDeptName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DeptRefCom.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	DeptRefCom.seItem = selected[0];
        return true;
    }
};




DeptRefCom.loadDepts=function(){
	$('#main_DeptName').empty();
	$('#selectArea').empty();
	
	var companyCode = $('#main_CompName').val();
	var params ={companyCode : companyCode} 
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/property/listDeptInfo",
		async : false,
		data: params,
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, value){
				var option = "<option value='"+value.id+"'>"+value.text+"</option>";
				$("#main_DeptName").append(option);
			});
			
		}
	});
	
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/deptref/getAreaListByParam",
		async : false,
		data : params,
		dataType : 'json',
		success : function(data) {
			var arr=new Array();
			var obj={};
			$.each(data, function(index, value){
				obj={ label: value.text, value:value.id }
				arr.push(obj);
			})
			$('#selectArea').multiselect({
				nonSelectedText: ' 请选择小区  ',
		 		buttonWidth: 164,
		        maxHeight: 200,
		        nSelectedText:'项被选中',
		        includeSelectAllOption:true,
		        selectAllText:'全选/取消',
		        allSelectedText:'已选中所有小区',
   			});
			$('#selectArea').multiselect('dataprovider', arr); //更新select
		}
	});
};



DeptRefCom.add=function (){
	var deptId=$('#main_DeptName').val();
	var values=$('#selectArea').val();
	if(!deptId){
		Feng.info("请选择部门")
		return;
	} 
	if(values==null||values.length==0){
		Feng.info("请选择小区")
		return;
	}
	
	var str="";
	for(var i=0;i<values.length;i++){
		if(values[i]){
			str+=values[i];
			if(i<values.length-1){
				str+=",";
			}
		}
	}
	  $.ajax({
			type : 'POST',
			url : Feng.ctxPath+'/deptref/addRelation',
			async : false,
			data : {"deptId":deptId,"values":str },
			dataType : 'json',
			beforeSend : function(){
				
			},
			success : function(data){     				
				if(data.result=="1"){
					Feng.success('关联成功');
					DeptRefCom.search();
					return;
				}else if(data.result=="2"){
					Feng.info('已经关联');
					return;
				}else{
					Feng.info("关联失败");
					return;
				}
			}
		}); 
	
}


/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
DeptRefCom.formParams = function() {

    var queryData = {
		 compName:  $('#main_CompName').val(),
		 companyCode:  $('#main_CompanyCode').val(),
	}
    return queryData;
}


/**
 * 查询公司管理列表
 */
DeptRefCom.search = function () {
    
    var companyCode = $('#main_CompName').val();
	var deptId = $('#main_DeptName').val();
	if(companyCode == ''||companyCode==null){
		Feng.info("请选择公司！");
		return;
	}
	var queryData = {
		companyCode : companyCode,
		deptId : deptId
	}
    
	DeptRefCom.table.refresh({query: queryData});
};



DeptRefCom.del=	function (){
	   
	  if (this.check()) {
		  var tbaa01Id=  this.seItem.tbaa01Id;
		  var tbbb04Id=  this.seItem.tbbb04Id;
		  
		  Feng.confirm("确定删除关联？",function() { 
			 $.ajax({
					type : 'POST',
					url : Feng.ctxPath+'/deptref/deleteRelation',
					async : false,
					data : {"tbaa01Id": tbaa01Id,"tbbb04Id": tbbb04Id,},
					dataType : 'json',
					beforeSend : function(){
						
					},
					success : function(data){  
						if(data.result=="1"){
							Feng.success('删除关联成功');
							DeptRefCom.search();
							return;
						}else{
							Feng.info(data.msg);
							return;
						}
					}
				}); 
		 });
	  }
	  
};

DeptRefCom.clear=function(){
	$('#main_DeptName').empty();
	$('#selectArea').empty();
	$('#main_CompName').val('');
	$('#main_DeptName').val('');
	$('#selectArea').val('');
}
	
	
$(function() {
	

	var defaultColunms = DeptRefCom.initColumn();
    var table = new BSTable(DeptRefCom.id, "/deptref/findDeptAndArea", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(DeptRefCom.formParams());
    DeptRefCom.table = table.init();
	
	
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/property/selectSubCompanies",
		async : false,
		dataType : 'json',
		success : function(data) {
			var option  = "<option value='' >请选择</option>";
			$("#main_CompName").append(option);
			$.each(data, function(index, value){
				option = "<option value='"+value.code+"'>"+value.name+"</option>";
				$("#main_CompName").append(option);
			});
			
			$("#main_CompName").select2();
			$("#main_DeptName").select2();
		}
	});

	// $('#selectArea').multiselect();
	$('#selectArea').multiselect({
		nonSelectedText: ' 请选择小区  ',
 		buttonWidth: 184,
        maxHeight: 200,
        nSelectedText:'项被选中',
        includeSelectAllOption:true,
        selectAllText:'全选/取消',
        allSelectedText:'已选中所有小区',
    });
});

$(window).resize(function () {
    $('#DeptRefComtable').bootstrapTable('resetView');
});
