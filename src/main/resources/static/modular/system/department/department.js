
var Dept={
    oldNodeName:"",//用于捕获编辑按钮的 click 事件，并且根据返回值确定是否允许进入名称编辑状态
    id: "DeptTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
    
};

/**
 * 初始化表格的列
 */
Dept.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'tbbb04Id', visible: false, align: 'center', valign: 'middle'},
            {title: '部门姓名', field: 'deptName', visible: true, align: 'center', valign: 'middle'},
            {title: '部门代码', field: 'deptCode', visible: true, align: 'center', valign: 'middle'},
            {title: '上级部门', field: 'parentDeptName', visible: true, align: 'center', valign: 'middle'},
            {title: '公司编码', field: 'companyCode', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Dept.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	Dept.seItem = selected[0];
        return true;
    }
};

	  
Dept.beforeEditName=function(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(treeNode);
	return true;
}
	
  //用于捕获编辑名称结束（Input 失去焦点 或 按下 Enter 键）之后，更新分类名称数据之前的事件回调函数  
Dept.beforeRename=  function(treeId, treeNode, newName) { 
    	Dept.oldNodeName=treeNode.deptName;
    	var zTree = $.fn.zTree.getZTreeObj("tree");
        if (newName.length == 0 || newName.indexOf("请输入名称")>=0) {  
             setTimeout( function(){zTree.editName(treeNode)}, 10);  
             return false;  
       }  
        if(newName.length > 25){  
            setTimeout( function(){zTree.editName(treeNode)}, 10);  
            return false;  
       }
       return true;  
}  ;
     //执行编辑操作  
Dept.onRename= function(e, treeId, treeNode) { 
	
		//没做修改 不调用后台
		if(Dept.oldNodeName==treeNode.deptName){
			return;
		}
    	var zTree = $.fn.zTree.getZTreeObj("tree");
         var params = {tbbb04Id:treeNode.tbbb04Id,
      		   parentTbbb04Id:treeNode.parentTbbb04Id,
      		   deptName:treeNode.deptName};  
         $.ajax({  
        	 async: false,  
             type: "post",  
             data:params,  
             url: Feng.ctxPath + "dept/update",  
             success : function(data){  
            	 if(data.result == "1" ){
               	  	 Feng.success('修改成功');
	               	 if(treeNode.isParent){
	               		Dept.search(treeNode.tbbb04Id);
	              	}
               	  zTree.updateNode(treeNode);
                 }else if(data.result == "2" ){
                	 Feng.error('该名称已存在，请重新命名');
                	  zTree.cancelEditName(Dept.oldNodeName);
                  }else if(data.result == "-1" ){
                	  Feng.error('公司名称只能在公司管理页面修改');
                  }else{   
                	  Feng.error('操作失败，请稍后再试！');
                  }  
             },  
             error : function()    {  
            	 Feng.error('网络繁忙......！');
             }  
        });  
}  ;
   
Dept.add=function (e) {
		var name=$('#name').val(),
		ok=true,parentNode;
		var zTree = $.fn.zTree.getZTreeObj("tree"),
		isParent = e.data.isParent,
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		parentNode=treeNode;
		if(!name){
			Feng.error('名称不能为空！');
			ok=false;
		}
		
		if(ok&&treeNode){
			var parentTbbb04Id=treeNode.tbbb04Id;
			var params = {
	       			deptName : name,
	       			parentTbbb04Id:parentTbbb04Id
	    	};
			$.ajax({  
          	  async: true,  
              type: "post",  
              data : params,
  			  dataType : 'json',
              url: Feng.ctxPath + "dept/insert",  
              success : function(data){ 
            	  if(data.result == "2" ){
            		  Feng.info('该名称已存在，请重新命名！');
                  }else if(data.result == "1" ){
	            	if(data.tbbb04Id != "" ){  
	           			var tbbb04Id=data.tbbb04Id;
	           			var treeNode={tbbb04Id:tbbb04Id, parentTbbb04Id:parentTbbb04Id,isParent:false, deptName:name};
	           			zTree.addNodes(nodes[0],treeNode);
	                }
                  }
            	  zTree.selectNode(parentNode);
            	  Dept.search(parentNode.tbbb04Id);
              },  
              error : function(){  
            	  Feng.error('网络繁忙.......！');
              }  
           });  
		}else {
			if(nodes.length == 0){
				 Feng.error('请先选择一个节点');
			}else if(treeNode.level==3){
				 Feng.error('叶子节点被锁定，无法增加子节点');
			}
  		}
		
};

Dept.onClick= function (event, treeId, treeNode) {
	Dept.search(treeNode.tbbb04Id);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if(treeNode.open){
		zTree.expandNode(treeNode, false, false, true);
	}else{
		zTree.expandNode(treeNode, true, false, true);
	}
};


Dept.setting = {
		async: {
			enable: true
		},
		data: {
			key:{
				name:"deptName"
			},
			simpleData: {
				enable: true,
				idKey: "tbbb04Id",
				pIdKey: "parentTbbb04Id",
				rootPId: -1
			}
		},
		view: {
			dblClickExpand: false,
			selectedMulti: false,
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: false, 
			showRenameBtn: false
		},
		callback: {
			beforeEditName: Dept.beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
			beforeRename:Dept.beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求
			onRename:Dept.onRename,//编辑后触发，用于操作后台
			onClick:Dept.onClick 
		}
		
	}; 
	
	  
/**修改节点名称*/
Dept.edit=function() {
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	if (nodes.length == 0) {
		Feng.info('请先选择一个节点');
		return;
	}
	zTree.editName(treeNode);
};
	

Dept.beforeEditName=function (treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(treeNode);
	return true;
}

   
/**
 * 删除部门管理
 */
Dept.del = function () {
    if (this.check()) {
    	
    	var tbbb04Id=this.seItem.tbbb04Id;
    	var deptName=this.seItem.deptName;
    	
    	var zTree = $.fn.zTree.getZTreeObj("tree");
    	var node = zTree.getNodeByParam("deptName", deptName, null);
    	var parentNode = node.getParentNode();
    	var parentId=parentNode.tbbb04Id;
       	var params = {
       			tbbb04Id : tbbb04Id
    	}
        Feng.confirm("确定删除？",function() { 
			$.ajax({
				type : 'POST',
	 			url : Feng.ctxPath + "dept/delete",
	 			async : true,
	 			data : params,
	 			dataType : 'json',
	 			beforeSend : function(){
	 			},
	 			success : function(data){ 
	 				if(data.result == '1'){
	 					zTree.removeNode(node);
	 					Feng.success('删除成功');
	 					Dept.search(parentId);
	 					return;
	 				}else if(data.result == '-1'){
	 					Feng.error('参数为空');
	 					return;
	 				}else if(data.result == '2'){
	 					Feng.error('删除失败，部门正在使用中！');
	 					return;
	 				}else if(data.result == '3'){
	 					Feng.error('该部门存在子部门，无法删除！');
	 					return;
	 				}else{
	 					Feng.error('程序异常');
	 					return;
	 				}
	 			}
 		});
        })
    }
};



//加载ztree  
Dept.onloadZTree=function (){  
  var ztreeNodes;  
  $.ajax( {  
       async : true, //是否异步  
       cache : false, //是否使用缓存  
       type : 'post', //请求方式,post  
       dataType : "json", //数据传输格式  
       url : Feng.ctxPath + "dept/select", //请求链接   
       error : function() {  
    	   Feng.error('网络繁忙.......！');
       },  
       success : function(data) {  
            ztreeNodes = data.deptList; //将string类型转换成json对象  
            $.fn.zTree.init($( "#tree"), Dept.setting, ztreeNodes);  
       }  
  });  
}  

/**
 * 查询公司管理列表
 */
Dept.search = function (tbdd04Id) {
    var queryData = {
    	parentTbbb04Id: tbdd04Id
    }
        
    Dept.table.refresh({query: queryData});
};  
   
//初始化操作  
$(document).ready( function(){  
	  Dept.onloadZTree(); 
	  
	  var defaultColunms = Dept.initColumn();
      var table = new BSTable(Dept.id, "/dept/selectBb04ByPage", defaultColunms);
      table.setPaginationType("server");
      table.setQueryParams({});
      Dept.table = table.init();

      $("#addLeaf").bind("click",{isParent:true},Dept.add);
	  $("#editName").bind("click", Dept.edit);
});
   
