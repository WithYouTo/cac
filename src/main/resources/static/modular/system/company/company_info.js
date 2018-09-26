/**
 * 初始化公司管理详情对话框
 */
var CompanyInfoDlg = {
    companyInfoData : {}
};

/**
 * 清除数据
 */
CompanyInfoDlg.clearData = function() {
    this.companyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyInfoDlg.set = function(key, val) {
    this.companyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CompanyInfoDlg.close = function() {
    parent.layer.close(window.parent.Company.layerIndex);
}

/**
 * 收集数据
 */
CompanyInfoDlg.collectData = function() {
    this
    .set('companyId')
    .set('companyCode')
    .set('compName')
    .set('fullName')
    .set('compBrand')
    .set('address')
    .set('mail')
    .set('compTel')
    .set('fax')
    .set('compWeb')
    .set('email')
    .set('nationalTax')
    .set('landTax')
    .set('bankAccount')
    .set('bank')  
}

/**
 * 提交添加
 */
CompanyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/company/add", function(data){
        Feng.success("添加成功!");
        window.parent.Company.table.refresh();
        CompanyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.Feng.error + "!");
    });
    ajax.set(this.companyInfoData);
    ajax.start();
}

CompanyInfoDlg.add= function(){
	var compName = $('#compName').val();
	var fullName = $('#fullName').val();
	var compBrand = $('#compBrand').val();
	var address = $('#address').val();
	var mail = $('#mail').val();
	var compTel = $('#compTel').val();
	var compWeb = $('#compWeb').val();
	var fax = $('#fax').val();
	var email = $('#email').val();
	var nationalTax = $('#nationalTax').val();
	var landTax = $('#landTax').val();
	var bankAccount = $('#bankAccount').val();
	var bank = $('#bank').val();

	if(compName == ''){
		Feng.error('请输入公司简称！'); 
		return;
	}
	if(fullName == ''){
		Feng.error('请输入公司全称！');
		return;
	}
	if(compBrand == ''){
		Feng.error('请选择公司品牌！');
		return;
	}
	if(email !=''){
    	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   	    if (!filter.test(email)){      
   	    	Feng.error('电子邮件格式错误！');
   	  		return;
       }
    }
	if(compTel !=''){
    	var filter  =  /(^(\d{3,4}-)?\d{7,8})$|^((1[0-9][0-9]\d{8}$))/;
   	    if (!filter.test(compTel)){      
   	    	Feng.error('公司电话格式错误！');
   	  		return;
       }
    }
	if(fax !=''){
    	var filter  =  /^(\d{3,4}-)?\d{7,8}$/;
   	    if (!filter.test(fax)){      
   	    	Feng.error('传真号码格式错误！');
   	  		return;
       }
    }
	if(compWeb !=''){
    	var filter  =  /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
   	    if (!filter.test(compWeb)){      
   	    	Feng.error('公司网址格式错误！');
   	  		return;
       }
    }
	if(nationalTax !=''){
    	var filter  =  /\d{15}/;
   	    if (!filter.test(nationalTax)){      
   	    	Feng.error('国税号格式错误！');
   	  		return;
       }
    }
	if(landTax !=''){
    	var filter  =  /\d{15}/;
   	    if (!filter.test(landTax)){      
   	    	Feng.error('地税号格式错误！');
   	  		return;
       }
    }
	if(bankAccount !=''){
    	var filter  =  /^([1-9]{1})(\d{14}|\d{18})$/;
   	    if (!filter.test(bankAccount)){      
   	    	Feng.error('银行账号格式错误！');
   	  		return;
       }
    }
	var params = {
			compName :compName,
			fullName :fullName,
			compBrand :compBrand,
			address :address,
			mail :mail,
			compTel :compTel,
			compWeb :compWeb,
			fax :fax,
			email :email,
			nationalTax :nationalTax,
			landTax :landTax,
			bankAccount :bankAccount,
			bank:bank
	}
	
		$.ajax({
			type : 'POST',
			url : Feng.ctxPath +'/company/add',
			async : false,
			data : params,
			dataType : 'json',
			beforeSend : function(){
				
			},
			success : function(data){   
				if(data.flag =="1"){
					Feng.success('新增成功');
					window.parent.Company.table.refresh();
				    CompanyInfoDlg.close();	
					return;
				}else if(data.flag =="-1"){
					Feng.error('新增失败，参数为空');
					return;
				}else if(data.flag =="-2"){
					Feng.error('您选择的公司编码已经被使用！');
					return;
				}else if(data.flag =="-3"){
					Feng.error('新增失败，公司简称重复');
					return;
				}else if(data.flag =="-4"){
					Feng.error('新增失败，公司全称重复');
					return;
				}else if(data.flag =="-5"){
					Feng.error('新增失败，公司品牌重复');
					return;
				}else{
					Feng.error('处理失败');
					return;
				}
			}
		}); 

    
}


CompanyInfoDlg.updateInfo=   function(){
	var companyId = $('#companyId').val();
	var companyCode = $('#companyCode').val();
	var oldCompName = $('#oldCompName').val();
	var oldFullName = $('#oldFullName').val();
	var oldCompBrand = $('#oldCompBrand').val();
	var newCompName = $('#newCompName').val();
	var newFullName = $('#newFullName').val();
	var newCompBrand = $('#newCompBrand').val();
	var address = $('#address').val();
	var mail = $('#mail').val();
	var compTel = $('#compTel').val();
	var compWeb = $('#compWeb').val();
	var fax = $('#fax').val();
	var email = $('#email').val();
	var nationalTax = $('#nationalTax').val();
	var landTax = $('#landTax').val();
	var bankAccount = $('#bankAccount').val();
	var bank = $('#bank').val();
	
	if(newCompName == ''){
		Feng.error('请选择公司简称！');
		return;
	}
	if(newFullName == ''){
		Feng.error('请输入公司全称！');
		return;
	}
	if(newCompBrand == ''){
		Feng.error('请输入公司品牌！');
		return;
	}
	if(email !=''){
    	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   	    if (!filter.test(email)){      
   	    	Feng.error('电子邮件格式错误！');
   	  		return;
       }
    }
	if(compTel !=''){
    	var filter  =  /(^(\d{3,4}-)?\d{7,8})$|^((1[0-9][0-9]\d{8}$))/;
   	    if (!filter.test(compTel)){      
   	    	Feng.error('公司电话格式错误！');
   	  		return;
       }
    }
	if(fax !=''){
    	var filter  =  /^(\d{3,4}-)?\d{7,8}$/;
   	    if (!filter.test(fax)){      
   	    	Feng.error('传真号码格式错误！');
   	  		return;
       }
    }
	if(compWeb !=''){
    	var filter  =  /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
   	    if (!filter.test(compWeb)){      
   	    	Feng.error('公司网址格式错误！');
   	  		return;
       }
    }
	if(nationalTax !=''){
    	var filter  =  /\d{15}/;
   	    if (!filter.test(nationalTax)){      
   	    	Feng.error('国税号格式错误！');
   	  		return;
       }
    }
	if(landTax !=''){
    	var filter  =  /\d{15}/;
   	    if (!filter.test(landTax)){      
   	    	Feng.error('地税号格式错误！');
   	  		return;
       }
    }
	if(bankAccount !=''){
    	var filter  =  /^([1-9]{1})(\d{14}|\d{18})$/;
   	    if (!filter.test(bankAccount)){      
   	    	Feng.error('银行账号格式错误！');
   	  		return;
       }
    }
	var params = {
			companyId:companyId,
			companyCode :companyCode,
			oldCompName:oldCompName,
			oldFullName:oldFullName,
			oldCompBrand :oldCompBrand,
			newCompName :newCompName,
			newFullName :newFullName,
			newCompBrand :newCompBrand,
			address :address,
			mail :mail,
			compTel :compTel,
			compWeb :compWeb,
			fax :fax,
			email :email,
			nationalTax :nationalTax,
			landTax :landTax,
			bankAccount :bankAccount,
			bank:bank
	}
    $.ajax({
			type : 'POST',
			url :  Feng.ctxPath+'/company/update',
			async : false,
			data : params,
			dataType : 'json',
			beforeSend : function(){
				
			},
			success : function(data){    
				if(data.flag =="1"){
					Feng.success('修改成功');
					window.parent.Company.table.refresh();
				    CompanyInfoDlg.close();
					return;
					//window.parent.window.frames['iframeContent'].query();
				}else if(data.flag =="-1"){
					Feng.error('修改失败，参数为空');
					return;
				}else if(data.flag =="-3"){
					Feng.error('修改失败，公司简称重复');
					return;
				}else if(data.flag =="-4"){
					Feng.error('修改失败，公司全称重复');
					return;
				}else if(data.flag =="-5"){
					Feng.error('修改失败，公司品牌重复');
					return;
				}else{
					Feng.error('修改失败');
					return;
				}
			}
	});
}

/**
 * 提交修改
 */
CompanyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/company/update", function(data){
        Feng.success("修改成功!");
        window.parent.Company.table.refresh();
        CompanyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.Feng.error + "!");
    });
    ajax.set(this.companyInfoData);
    ajax.start();
}


