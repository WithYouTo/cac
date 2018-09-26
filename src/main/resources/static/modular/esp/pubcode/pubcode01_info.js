/**
 * 配置从档详情对话框（可用于添加和修改对话框）
 */
var pubcode01InfoDlg = {
    pubcode01InfoData: {},
    validateFields: {
    	seq: {
            validators: {
            	 notEmpty: {
                     message: '顺序号不能为空'
                 },
                 regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                     regexp: /^\d{1,}$/,
                     message: '只能填写数字'
                 }
            }
        },
        desc: {
            validators: {
                notEmpty: {
                    message: '描述不能为空'
                }
            }
        },
        desc1: {
            validators: {
                notEmpty: {
                    message: '描述1不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
pubcode01InfoDlg.clearData = function () {
    this.pubcode01InfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
pubcode01InfoDlg.set = function (key, val) {
    this.pubcode01InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
pubcode01InfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
pubcode01InfoDlg.close = function () {
    parent.layer.close(window.parent.pubcode01.layerIndex);
};



/**
 * 收集数据
 */
pubcode01InfoDlg.collectData = function () {
    this.set('tbpubcode01Id').set('tbpubcodeId').set('configCode').set('seq').set('desc').set('desc1').set('desc2').set('desc3').set('desc4')
        .set('desc5').set('desc6').set('desc7').set('desc8').set('desc9');
};


/**
 * 验证数据是否为空
 */
pubcode01InfoDlg.validate = function () {
    $('#pubcode01InfoForm').data("bootstrapValidator").resetForm();
    $('#pubcode01InfoForm').bootstrapValidator('validate');
    return $("#pubcode01InfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加代码从档
 */
pubcode01InfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax_json(Feng.ctxPath + "/pubCode/pubCode01Add", function (data) {
    			
        if(data.code == 403){
            Feng.error( data.message + "!");
        }else{
            Feng.success("添加成功!");
            window.parent.pubcode01.table.refresh();
            pubcode01InfoDlg.close();
        }
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pubcode01InfoData);
    ajax.start();
};

/**
 * 提交修改
 */
pubcode01InfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax_json(Feng.ctxPath + "/pubCode/pubCode01Edit", function (data) {
        if(data.code == 403){
            Feng.error("修改失败! " + data.message + "!");
        }else{
            Feng.success("修改成功!");
        }
        if (window.parent.pubcode01 != undefined) {
            window.parent.pubcode01.table.refresh();
            pubcode01InfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pubcode01InfoData);
    ajax.start();
};


$(function () {
    Feng.initValidator("pubcode01InfoForm", pubcode01InfoDlg.validateFields);
});

