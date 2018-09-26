/**
 * 公司管理管理初始化
 */
var Company = {
    id: "CompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Company.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'companyId', visible: false, align: 'center', valign: 'middle'},
            {title: '公司编码', field: 'companyCode', visible: true, align: 'center', valign: 'middle'},
            {title: '公司简称', field: 'compName', visible: true, align: 'center', valign: 'middle'},
            {title: '公司全称', field: 'fullName', visible: true, align: 'center', valign: 'middle'},
            {title: '公司地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '公司电话', field: 'compTel', visible: true, align: 'center', valign: 'middle'},
            {title: '传真号码', field: 'fax', visible: true, align: 'center', valign: 'middle'},
            {title: '公司网址', field: 'compWeb', visible: true, align: 'center', valign: 'middle'},
            {title: '电子邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Company.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Company.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公司管理
 */
Company.openAddCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['900px', '540px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/company_add'
    });
    this.layerIndex = index;
};


Company.clear=function(){
	$('#main_CompName').val('');
	$('#main_CompanyCode').val('');
}

/**
 * 打开查看公司管理详情
 */
Company.openCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['900px', '540px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/company/toUpdate/' + Company.seItem.companyId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公司管理
 */
Company.delete = function () {
    if (this.check()) {
    	var operation=function(){
	        var ajax = new $ax(Feng.ctxPath + "/company/delete", function (data) {
	            Feng.success("删除成功!");
	            Company.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("companyCode",Company.seItem.companyCode);
	        ajax.start();
    	}
    	Feng.confirm("是否刪除该公司?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Company.formParams = function() {

    var queryData = {
		 compName:  $('#main_CompName').val(),
		 companyCode:  $('#main_CompanyCode').val(),
	}
    return queryData;
}


/**
 * 查询公司管理列表
 */
Company.search = function () {
    var queryData = {
   		 compName:  $('#main_CompName').val(),
   		 companyCode:  $('#main_CompanyCode').val(),
   	}
    
    Company.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Company.initColumn();
    var table = new BSTable(Company.id, "/company/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Company.formParams());
    Company.table = table.init();
});

$(window).resize(function () {
    $('#CompanyTable').bootstrapTable('resetView');
});

