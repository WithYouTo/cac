/**
 * 初始化详情对话框
 */
var UserInfoDlg = {
    userInfoData : {}
};

/**
 * 清除数据
 */
UserInfoDlg.clearData = function() {
    this.userInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function(key, val) {
    this.userInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function() {
    parent.layer.close(window.parent.User.layerIndex);
}

/**
 * 收集数据
 */
UserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('password')
    .set('account')
    .set('salt')
    .set('companyCode')
    .set('departmentId')
    .set('communityId')
    .set('sex')
    .set('name')
    .set('status')
    .set('phone')
    .set('mail')
    .set('userType')
    .set('birthday')
    .set('img')
    .set('idCard')
    .set('address')
    .set('startDate')
    .set('endDate')
    .set('newAddress')
    .set('race')
    .set('management')
    .set('remark')
    .set('createTime')
    .set('createEmp')
    .set('updateTime')
    .set('updateEmp')
    .set('version')
    .set('positionId')
    .set('roleId');
}

/**
 * 提交添加
 */
UserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    var flag=UserInfoDlg.check();
    if(!flag){
    	return ;
    }
    
    var params=this.userInfoData;
    
    $.ajax({
		type : 'POST',
		url :Feng.ctxPath + "/webUser/add",
		async : false,
		data : params,
		dataType : 'json',
		beforeSend : function(){
			
		},
		success : function(data){   
			if(data.flag =="1"){
				Feng.success('新增成功');
				window.parent.User.table.refresh();
				UserInfoDlg.close();	
				return;
			}else if(data.flag =="2"){
				Feng.error('新增失败，账号已存在');
				return;
			}else if(data.flag =="3"){
				Feng.error('新增失败，手机号已存在！');
				return;
			}else if(data.flag =="4"){
				Feng.error('新增失败，身份证号已存在');
				return;
			}else{
				Feng.error('处理失败');
				return;
			}
		}
	}); 
    
    
    
    //提交信息
//    var ajax = new $ax(Feng.ctxPath + "/webUser/add", function(data){
//        Feng.success("添加成功!");
//        window.parent.User.table.refresh();
//        UserInfoDlg.close();
//    },function(data){
//        Feng.error("添加失败!" + data.responseJSON.message + "!");
//    });
//    ajax.set(this.userInfoData);
//    ajax.start();
}

UserInfoDlg.check=function(){
	
	//校验
    var name =$("#name").val();	  
    var mobile= $("#phone").val();	  
    var email=$("#mail").val();
    var idCard=$("#idCard").val();
    var companyCode=$("#companyCode").val();
    var departmentId=$("#departmentId").val();
    var positionId=$("#positionId").val();
    //var roleId=$("#roleId").val();
    if(!mobile){
    	Feng.info('请输入手机号！');
    	return false;
    }
    if(!name){
    	Feng.info("请输入姓名！");
    	return false;
    }
  
   
    if(!companyCode){
    	Feng.info('请选择公司名称！');
    	return false;
    }
    
    if(companyCode){
    	if(!departmentId){
        	Feng.info('请选择部门！');
        	return false;
        }
    	if(!positionId){
        	Feng.info('请选择职位！');
        	return false;
        }
//    	if(!roleId){
//        	Feng.info('请选择角色！');
//        	return false;
//        }
    }

    var  re = /^1[34578]\d{9}$/;    //正则表达式
    if (!re.test(mobile)) {      //判断字符是否是11位数字
    	Feng.info('手机号码格式错误！');
  		return false;
    }

    if(email !=''){
    	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   	    if (!filter.test(email)){      
   	    	Feng.info('电子邮箱格式错误！');
   	  		return false;
       }
    }
	 
    
    if(idCard != ''){
    	//身份证正则表达式(15位)
    	isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    	//身份证正则表达式(18位)
    	isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
    	if (!isIDCard1.test(idCard)&&!isIDCard2.test(idCard)){      
    		Feng.info('身份证格式错误！');
      		return false;
        }
	}
    
    return true;
}

UserInfoDlg.phoneChange=function(){
	
	var phone=$("#phone").val();
	$("#account").val(phone);
}



/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    var flag=UserInfoDlg.check();
    if(!flag){
    	return ;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/webUser/update", function(data){
    	if(data.flag =="1"){
			Feng.success('修改成功');
			window.parent.User.table.refresh();
			UserInfoDlg.close();	
			return;
		}else if(data.flag =="2"){
			Feng.error('修改失败，账号已存在');
			return;
		}else if(data.flag =="3"){
			Feng.error('修改失败，手机号已存在！');
			return;
		}else if(data.flag =="4"){
			Feng.error('修改失败，身份证号已存在');
			return;
		}else{
			Feng.error('修改失败');
			return;
		}
    },function(data){
        Feng.error("修改失败!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
}

UserInfoDlg.loadDepts=function(){
	$('#departmentId').empty();
	$('#positionId').empty();
	//$('#roleId').empty();
	
	var companyCode = $('#companyCode').val();
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
				$("#departmentId").append(option);
			})
		}
	});
	
	
	//职位
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/webUser/selectPositionInfo",
		async : false,
		data:params,
		dataType : 'json',
		success : function(data) {
			var option  = "<option value='' >请选择</option>";
			$("#positionId").append(option);
			$.each(data, function(index, value){
				option = "<option value='"+value.id+"'>"+value.text+"</option>";
				$("#positionId").append(option);
			})
		}
	});
	
	
	//角色
//	$.ajax({
//		type : 'POST',
//		url : Feng.ctxPath + "/webUser/selectRoleInfo",
//		async : false,
//		data:params,
//		dataType : 'json',
//		success : function(data) {
//			var option  = "<option value='' >请选择</option>";
//			$("#roleId").append(option);
//			$.each(data, function(index, value){
//				option = "<option value='"+value.id+"'>"+value.text+"</option>";
//				$("#roleId").append(option);
//			})
//		}
//	});
}
	



$(function() {
	
	//初始化公司  部门  职位  角色  性别
	
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/property/selectSubCompanies",
		async : false,
		dataType : 'json',
		success : function(data) {
			var option  = "<option value='' >请选择</option>";
			$("#companyCode").append(option);
			$.each(data, function(index, value){
				option = "<option value='"+value.code+"'>"+value.name+"</option>";
				$("#companyCode").append(option);
			})
		}
	});
	
	if($("#companyCodeInfo").val()){
		$("#companyCode").val($("#companyCodeInfo").val());
		UserInfoDlg.loadDepts();
		if($("#departmentIdInfo").val()){
			$("#departmentId").val($("#departmentIdInfo").val());
		}
//		if($("#roleIdInfo").val()){
//			$("#roleId").val($("#roleIdInfo").val());
//		}
		if($("#positionIdInfo").val()){
			$("#positionId").val($("#positionIdInfo").val());
		}
		
		
	}
	//初始化 性别
	var  sexData=[{id:'0',text:'女'},{id:'1',text:'男'}];
	var option ='';
	$.each(sexData, function(index, value){
		option = "<option value='"+value.id+"'>"+value.text+"</option>";
		$("#sex").append(option);
	})
	
	if($("#sexInfo").val()){
		$("#sex").val($("#sexInfo").val());
	}
	
	
	
	

});
