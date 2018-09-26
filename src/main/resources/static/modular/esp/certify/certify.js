/**
 * 代码主档管理初始化
 */
var certify = {
    id: "certifyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

$(function() {
	
    var defaultColunms = certify.initColumn();
    var table = new BSTable(certify.id, "/aa01/list", defaultColunms);
    table.setPaginationType("server");
    table.isShowRefresh(false);
    table.isShowColumns(false);
    table.setQueryParams(certify.queryParams());
    certify.table = table.init();
});

certify.queryParams = function(){
	var queryData = {};
	queryData['name'] = $("#name").val();
	queryData['phone'] = $("#phone").val();
	queryData['communityId'] = $("#community").val();
	queryData['blockId'] = $("#block").val();
	queryData['unitId'] = $("#unit").val();
	queryData['roomId'] = $("#room").val();
	queryData['userType'] = $("#userType").val();
	return queryData;
}

/**
 * 查询代码主档列表
 */
certify.search = function () {
	certify.table.refresh({query: certify.queryParams()});
};

certify.reset = function () {
	$("#name").val("");
	$("#phone").val("");
	$("#userType").val("");
};

/**
 * 初始化表格的列
 */
certify.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '客户姓名', field: 'name', align: 'center', valign: 'middle', sortable: false},
        {title: '联系电话', field: 'phone', align: 'center', valign: 'middle', sortable: false},
        {title: '身份证号', field: 'idCard', align: 'center', valign: 'middle', sortable: false},
        {title: '小区名称', field: 'areaName', align: 'center', valign: 'middle', sortable: false},
        {title: '楼栋', field: 'building', align: 'center', valign: 'middle', sortable: false},
        {title: '单元', field: 'cellName', align: 'center', valign: 'middle', sortable: false},
        {title: '房间', field: 'roomName', align: 'center', valign: 'middle', sortable: false},
        {title: '学历', field: 'education', align: 'center', valign: 'middle', sortable: false,formatter: function(value,row,index){
			if (row.education == '1'){
				return '博士';
			} else if (row.education == '2'){
				return '硕士';
			}else if (row.education == '3'){
				return '本科';
			}else if (row.education == '4'){
				return '高中';
			}else if (row.education == '5'){
				return '初中';
			}else if (row.education == '6'){
				return '其它';
			}
		}},
        {title: '职业', field: 'career', align: 'center', valign: 'middle', sortable: false},
        {title: '收入', field: 'income', align: 'center', valign: 'middle', sortable: false,formatter: function(value,row,index){
			if (row.income == '1'){
				return '1万以下';
			} else if (row.income == '2'){
				return '1~5万之间';
			}else if (row.income == '3'){
				return '5~10万之间';
			}else if (row.income == '4'){
				return '10~20万之间';
			}else if (row.income == '5'){
				return '20~50万之间';
			}else if (row.income == '6'){
				return '50万以上';
			}
		}},
        {title: '客户类型', field: 'mainType', align: 'center', valign: 'middle', sortable: false,formatter: function(value,row,index){
			if (row.mainType == '1'){
				return '业主';
			} else if (row.mainType == '2'){
				return '家属';
			}
		}}
        ];
};

/**
 * 检查是否选中
 */
certify.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        certify.seItem = selected[0];
        return true;
    }
};

/**
 * 代码主档录入
 */
certify.openAddCertify = function () {
	
//	layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    var index = layer.open({
        type: 2,
        title: '添加代码主档信息',
        area: ['900px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/aa01/toadd'
    });
    this.layerIndex = index;
};


/**
 * 修改
 */
certify.modify = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '客户资料修改',
			area: ['900px', '500px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/aa01/toEdit/' + certify.seItem.id
		});
		this.layerIndex = index;
	}
};

/**
 * 客户资料删除
 */
certify.del = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/aa01/deleteUserInfo", function (data) {
                Feng.success("删除成功!"); 
                certify.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", certify.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除客户资料?", operation);
    }
};

