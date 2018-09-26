/**
 * 职位管理管理初始化
 */
var Position = {
    id: "PositionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Position.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'positionId', visible: false, align: 'center', valign: 'middle'},
            {title: '公司名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '职位名称', field: 'positionName', visible: true, align: 'center', valign: 'middle'},
            {title: '职位级别', field: 'positionLevel', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Position.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Position.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加职位管理
 */
Position.openAddPosition = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['680px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/position/position_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看职位管理详情
 */
Position.openPositionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['680px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/position/toUpdate/' + Position.seItem.tbbb05Id,
            success:function(layero, index){
            	console.log("sucess");
            }
        });
        this.layerIndex = index;
    }
};

/**
 * 删除职位管理
 */
Position.delete = function () {
    if (this.check()) {
        var operation=function(){
	        var ajax = new $ax(Feng.ctxPath + "/position/deletePosition", function (data) {
	            Feng.success("删除成功!");
	            Position.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("tbbb05Id",Position.seItem.tbbb05Id);
	        ajax.start();
        }
        
        Feng.confirm("是否刪除该职位?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Position.formParams = function() {
	var companyCode = $('#main_Company').val();
	var positionName = $('#main_PositionName').val();
	var queryData = {
			companyCode:  companyCode,
			positionName : positionName
	}
    return queryData;
}


/**
 * 查询职位管理列表
 */
Position.search = function () {
	var companyCode = $('#main_Company').val();
	var positionName = $('#main_PositionName').val();
	var queryData = {
			companyCode:  companyCode,
			positionName : positionName
	}
    
    Position.table.refresh({query: queryData});
};

$(function () {
	
	$.ajax({
		type : 'POST',
		url : Feng.ctxPath + "/property/selectSubCompanies",
		async : false,
		dataType : 'json',
		success : function(data) {
			$.each(data, function(index, value){
				var option = "<option value='"+value.code+"'>"+value.name+"</option>";
				$("#main_Company").append(option);
			});
			
			$("#main_Company").select2();
			
		}
	});
	
	
    var defaultColunms = Position.initColumn();
    var table = new BSTable(Position.id, "/position/selectPositionsByPage", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Position.formParams());
    Position.table = table.init();
});

$(window).resize(function () {
    $('#PositionTable').bootstrapTable('resetView');
});

