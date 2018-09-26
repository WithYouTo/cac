/**
 * 代码主档详情对话框（可用于添加和修改对话框）
 */
var certifyInfoDlg = {
    certifyInfoData: {}
};

/**
 * 清除数据
 */
certifyInfoDlg.clearData = function () {
    this.certifyInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
certifyInfoDlg.set = function (key, val) {
    this.certifyInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
certifyInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
certifyInfoDlg.close = function () {
    parent.layer.close(window.parent.certify.layerIndex);
};



/**
 * 收集数据
 */
certifyInfoDlg.collectData = function () {
	this.set('name').set('phone').set('communityId').set('blockId')
	.set('unitId').set('roomId').set('mainType').set('sex')
	.set('education').set('idCard').set('career').set('income');
};

certifyInfoDlg.check = function(){	
	//校验
    var name = $("#name").val();	  
    var mobile = $("#phone").val();	  
    var communityId = $("#communityId").val();
    var blockId = $("#blockId").val();
    var unitId = $("#unitId").val();
    var roomId = $("#roomId").val();
    var mainType = $("#mainType").val();
    var sex = $("#sex").val();
    var education = $("#education").val();
    var idCard = $("#idCard").val();
    var career = $("#career").val();
    var income = $("#income").val();


    if(!name){
    	Feng.info("请输入姓名！");
    	return false;
    }
    if(!mobile){
    	Feng.info('请输入手机号！');
    	return false;
    }
    if(!communityId){
    	Feng.info("请选择小区！");
    	return false;
    }
    if(!blockId){
    	Feng.info('请选择楼栋！');
    	return false;
    }
    if(!unitId){
    	Feng.info("请选择单元！");
    	return false;
    }
    if(!roomId){
    	Feng.info('请选择房间！');
    	return false;
    }

    if(!idCard){
    	Feng.info('请输入身份证号！');
    	return false;
    }
    
    var  re = /^1[34578]\d{9}$/;    //正则表达式
    if (!re.test(mobile)) {      //判断字符是否是11位数字
    	Feng.info('手机号码格式错误！');
  		return false;
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
 

/**
 * 提交添加客户信息
 */
certifyInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    var flag = certifyInfoDlg.check();
    if (!flag) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/aa01/addUserInfo", function(data){
    	if(data.flag =="1"){
			Feng.success('新增成功');
			window.parent.certify.table.refresh();
			certifyInfoDlg.close();	
			return;
		}else if(data.flag =="-1"){
			Feng.error('姓名或电话号码为空');
			return;
		}else if(data.flag =="-2"){
			Feng.error('未选择小区、楼栋、单元或房间！');
			return;
		}else if(data.flag =="-3"){
			Feng.error('新增失败，身份证号为空');
			return;
		}
    },function(data){
        Feng.error("新增失败!");
    });
    ajax.set(this.certifyInfoData);
    ajax.start();
 
};

/**
 * 提交修改
 */
certifyInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax_json(Feng.ctxPath + "/aa01/updateUserInfo", function (data) {
        if(data.code == 403){
            Feng.error("修改失败! " + data.message + "!");
        }else{
            Feng.success("修改成功!");
        }
        if (window.parent.certify != undefined) {
            window.parent.certify.table.refresh();
            certifyInfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.certifyInfoData);
    ajax.start();
};

function uploadPic(){
	var params = {
		//businessId:wpUserId
	};
	
	whxx.upload.multiPic(function(data){
		for(x in data){
			var bfUploadId = data[x].bfUploadId;
			var storePath = data[x].storePath;
			var storeName = data[x].storeName;
			var url = data[x].url;
			var img = "<div style='float:left' id='" + bfUploadId + "'> " 
				+ "<div>"
				+ "<a href='"+url+"' target='_blank'>"
				+ "<img id='image' style='width: 50px;height: 50px;' src='" + url + "' />"
				+ "</a>"
				+ "<div>"
				+ "<div style='text-align: center;'>"
				+ "</div>"
				+ "</div>";
			jQuery("#upload").append(img);
		}
	},params); 
} 

$(function () {
    Feng.initValidator("certifyInfoForm", certifyInfoDlg.validateFields);
});

