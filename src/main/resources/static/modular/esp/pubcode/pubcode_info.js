/**
 * 代码主档详情对话框（可用于添加和修改对话框）
 */
var pubcodeInfoDlg = {
    pubcodeInfoData: {},
    validateFields: {
//    	hotelGroupId: {
//            validators: {
//            	 notEmpty: {
//                     message: '集团编号不能为空'
//                 }
//            }
//        },
//        hotelId: {
//        	validators: {
//        		notEmpty: {
//        			message: '酒店编号不能为空'
//        		}
//        	}
//        },
        configCode: {
            validators: {
                notEmpty: {
                    message: '配置项目代码不能为空'
                }
            }
        },
        configName: {
            validators: {
                notEmpty: {
                    message: '配置项目名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
pubcodeInfoDlg.clearData = function () {
    this.pubcodeInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
pubcodeInfoDlg.set = function (key, val) {
    this.pubcodeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
pubcodeInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
pubcodeInfoDlg.close = function () {
    parent.layer.close(window.parent.pubcode.layerIndex);
};



/**
 * 收集数据
 */
pubcodeInfoDlg.collectData = function () {
//    this.set('tbpubcodeId').set('hotelGroupId').set('hotelId').set('configName').set('configCode').set('notice');
	this.set('tbpubcodeId').set('configName').set('configCode').set('notice');
};


/**
 * 验证数据是否为空
 */
pubcodeInfoDlg.validate = function () {
    $('#pubcodeInfoForm').data("bootstrapValidator").resetForm();
    $('#pubcodeInfoForm').bootstrapValidator('validate');
    return $("#pubcodeInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加代码主档
 */
pubcodeInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax_json(Feng.ctxPath + "/pubCode/pubCodeAdd", function (data) {
    			
        if(data.code == 403){
            Feng.error( data.message + "!");
        }else{
            Feng.success("添加成功!");
            window.parent.pubcode.table.refresh();
            pubcodeInfoDlg.close();
        }
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pubcodeInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
pubcodeInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax_json(Feng.ctxPath + "/pubCode/pubCodeEdit", function (data) {
        if(data.code == 403){
            Feng.error("修改失败! " + data.message + "!");
        }else{
            Feng.success("修改成功!");
        }
        if (window.parent.pubcode != undefined) {
            window.parent.pubcode.table.refresh();
            pubcodeInfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pubcodeInfoData);
    ajax.start();
};


$(function () {
    Feng.initValidator("pubcodeInfoForm", pubcodeInfoDlg.validateFields);
});

