/**
 * 代码从档管理初始化
 */
var pubcode01 = {
    id: "pubcode01Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

$(function() {
	
    var defaultColunms = pubcode01.initColumn();
    var table = new BSTable(pubcode01.id, "/pubCode/listPubCode01", defaultColunms);
    table.setPaginationType("server");
    table.isShowRefresh(false);
    table.isShowColumns(false);
    table.setHeight(330);
    table.setPageSize(5);
    table.setQueryParams(pubcode01.queryParams());
    pubcode01.table = table.init();
});

pubcode01.queryParams = function(){
	var queryData = {};
	queryData['tbpubcodeId'] = $("#tbpubcodeId").val();
	return queryData;
}

/**
 * 初始化表格的列
 */
pubcode01.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'tbpubcode01Id', visible: false, align: 'center', valign: 'middle'},
        {title: '配置项目代码', field: 'configCode', align: 'center', valign: 'middle', sortable: false},
        {title: '顺序号', field: 'seq', align: 'center', valign: 'middle', sortable: false},
        {title: '描述', field: 'desc0', align: 'center', valign: 'middle', sortable: false},
        {title: '描述1', field: 'desc1', align: 'center', valign: 'middle', sortable: false}
        ];
};

/**
 * 检查是否选中
 */
pubcode01.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        pubcode01.seItem = selected[0];
        return true;
    }
};

/**
 * 代码从档录入
 */
pubcode01.openAddpubcode01 = function () {
	
//	layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    var index = layer.open({
        type: 2,
        title: '添加代码从档信息',
        area: ['700px', '360px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pubCode/toAddPubCode01?configCode='+$("#configCode").val()+"&tbpubcodeId="+$("#tbpubcodeId").val()
    });
    this.layerIndex = index;
};


/**
 * 修改
 */
pubcode01.modify = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '代码从档修改',
			area: ['700px', '360px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/pubCode/toModifyPubCode01/' + pubcode01.seItem.tbpubcode01Id
		});
		this.layerIndex = index;
	}
};

/**
 * 代码从档删除
 */
pubcode01.del = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/pubCode/deletePubCode01", function (data) {
                Feng.success("删除成功!"); 
                pubcode01.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("tbpubcode01Id", pubcode01.seItem.tbpubcode01Id);
            ajax.start();
        };

        Feng.confirm("是否刪除代码从档 ?", operation);
    }
};


