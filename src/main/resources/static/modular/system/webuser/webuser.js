/**
 * 管理初始化
 */
var User = {
    id: "UserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
User.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: 'comcode', field: 'comcode', visible: false, align: 'center', valign: 'middle'},
            {title: '账号', field: 'account', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号码', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '所属公司', field: 'companyCode', visible: true, align: 'center', valign: 'middle'},
            {title: '所属部门', field: 'departmentId', visible: true, align: 'center', valign: 'middle'},
            {title: '职位', field: 'positionId', visible: true, align: 'center', valign: 'middle'},
            {title: '角色', field: 'roleId', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'mail', visible: true, align: 'center', valign: 'middle'} ,
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'} 
    ];
};

/**
 * 检查是否选中
 */
User.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        User.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
User.openAddUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/webUser/webuser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
User.openUserDetail = function () {
	
    if (this.check()) {
    	if("admin"==User.seItem.account){
    		Feng.info("系统管理员用户不能修改");
    		return false;
    	}
    	
        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/webUser/toUpdate/' + User.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
User.delete = function () {
    if (this.check()) {
    	var operation=function(){
	        var ajax = new $ax(Feng.ctxPath + "/webUser/delete", function (data) {
	            Feng.success("删除成功!");
	            User.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("userId",User.seItem.id);
	        ajax.start();
    	}
        
        Feng.confirm("是否刪除该用户?", operation);
    }
};

/**
 * 冻结用户
 */
User.freeze=function(){
	
	 if (this.check()) {
		 var status=this.seItem.status;
		 if("冻结"==status){
			 Feng.info("该用户已经冻结！");
			 return;
		 }
		 
    	var operation=function(){
	        var ajax = new $ax(Feng.ctxPath + "/webUser/changeStatus", function (data) {
	        	if("1"==data.flag){
	        		Feng.success("冻结成功!");
	        		User.table.refresh();
	        	}else{
	        		Feng.error("冻结失败!");
	        	}
	        }, function (data) {
	            Feng.error("冻结失败!");
	        });
	        var id=User.seItem.id;
	        var status="2";
	        var param={id:id,status:status};
	        ajax.set(param);
	        ajax.start();
    	}
        Feng.confirm("是否冻结该用户?", operation);
	 }

}

/**
 * 解除冻结
 */
User.unFreeze=function(){
	 if (this.check()) {
		 var status=this.seItem.status;
		 if("启用"==status){
			 Feng.info("该用户已是启用状态！");
			 return;
		 }
		 
    	var operation=function(){
	        var ajax = new $ax(Feng.ctxPath + "/webUser/changeStatus", function (data) {
	        	if("1"==data.flag){
	        		Feng.success("解除冻结成功!");
	        		User.table.refresh();
	        	}else{
	        		Feng.error("解除冻结失败!");
	        	}
	        }, function (data) {
	            Feng.error("解除冻结失败!");
	        });
	        var id=User.seItem.id;
	        var status="1";
	        var param={id:id,status:status};
	        ajax.set(param);
	        ajax.start();
    	}
        Feng.confirm("是否解除冻结?", operation);
	 }

}

/**
 * 角色分配
 */
User.roleAssgin=function(){
	
	if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '角色分配',
            area: ['300px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mgr/role_assignNew/' + this.seItem.id+"/"+User.seItem.comcode
        });
        this.layerIndex = index;
    }
	
}

/**
 * 重置密码
 */
User.resetPwd = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        parent.layer.confirm('是否重置密码为111111？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            var ajax = new $ax(Feng.ctxPath + "/mgr/reset", function (data){

            if(data.code == 403){
                Feng.error("重置密码失败! " + data.message + "!");
            }else{
                Feng.success("重置密码成功!");
            }
            }, function (data) {
                Feng.error("重置密码失败!");
            });
            ajax.set("userId", userId);
            ajax.start();
        });
    }
};

User.clear=function(){
//	$('#companyName').empty();
	$('#deptName').empty();
	
	$('#companyName').val('');
	$('#deptName').val('');
	$('#name').val('');
	$('#phone').val('');

}

/**
 * 查询列表
 */
User.search = function () {
    var queryData = {};
    var fields=["companyName","deptName","name","phone"];
    $.each(fields,function(index,value){
    	queryData[value] = $("#"+value).val();
    });
   
    User.table.refresh({query: queryData});
};

User.loadDepts=function(){
	$('#deptName').empty();
	
	var companyCode = $('#companyName').val();
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
				$("#deptName").append(option);
			});
			
		}
	});
}
	

$(function () {
	//初始化  公司  部门
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/property/selectSubCompanies",
		async : false,
		dataType : 'json',
		success : function(data) {
			var option  = "<option value='' >请选择</option>";
			$("#companyName").append(option);
			$.each(data, function(index, value){
				option = "<option value='"+value.code+"'>"+value.name+"</option>";
				$("#companyName").append(option);
			});
			$("#companyName").select2();
			$("#deptName").select2();
		}
	});

	
    var defaultColunms = User.initColumn();
    var table = new BSTable(User.id, "/webUser/list", defaultColunms);
    table.setPaginationType("server");
    User.table = table.init();
});

$(window).resize(function () {
    $('#UserTable').bootstrapTable('resetView');
});


