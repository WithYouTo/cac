
var Location={
    oldNodeName:"",//用于捕获编辑按钮的 click 事件，并且根据返回值确定是否允许进入名称编辑状态
    id: "LocationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
    
};

/**
 * 初始化表格的列
 */
Location.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '地区名称', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Location.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	Location.seItem = selected[0];
        return true;
    }
};

	  
Location.beforeEditName=function(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(treeNode);
	return true;
}
	
  //用于捕获编辑名称结束（Input 失去焦点 或 按下 Enter 键）之后，更新分类名称数据之前的事件回调函数  
Location.beforeRename=  function(treeId, treeNode, newName) { 
    	Location.oldNodeName=treeNode.name;
    	var zTree = $.fn.zTree.getZTreeObj("tree");
        if (newName.length == 0 || newName.indexOf("请输入名称")>=0) {  
             setTimeout( function(){zTree.editName(treeNode)}, 10);  
             return false;  
       }  
        if(newName.length > 100){  
            setTimeout( function(){zTree.editName(treeNode)}, 10);  
            return false;  
       }
       
       return true;  
}  ;
     //执行编辑操作  
Location.onRename= function(e, treeId, treeNode) { 
		//没做修改  不调用后台
		if(Location.oldNodeName==treeNode.name){
			return ;
		}
    	var zTree = $.fn.zTree.getZTreeObj("tree");
         var params = {id:treeNode.id,
      		   pId:treeNode.pId,
      		   name:treeNode.name};  
         $.ajax({  
        	 async: false,  
             type: "post",  
             data:params,  
             url: Feng.ctxPath + "/location/update",  
             success : function(data){  
            	 if(data.result == "1" ){
               	  	  Feng.success('修改成功');
	               	  if(treeNode.isParent){
	               		 Location.search(treeNode.id);
	              	  }
               	      zTree.updateNode(treeNode);
                 }else if(data.result == "2" ){
                	  Feng.error('该名称已存在，请重新命名');
                	  zTree.cancelEditName(Location.oldNodeName);
                 }else if(data.result == "-1" ){
                	  Feng.error('修改异常');
                 }else{   
                	  Feng.error('操作失败，请稍后再试！');
                 }  
             },  
             error : function()    {  
            	 Feng.error('网络繁忙......！');
             }  
        });  
}  ;
   
Location.add=function (e) {
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
			var pId=treeNode.id;
			var params = {
	       			name : name,
	       			pId:pId
	    	};
			$.ajax({  
          	  async: true,  
              type: "post",  
              data : params,
  			  dataType : 'json',
              url: Feng.ctxPath + "/location/insert",  
              success : function(data){ 
            	  if(data.result == "2" ){
            		  Feng.info('该名称已存在，请重新命名！');
                  }else if(data.result == "1" ){
	            	if(data.maxId != "" ){  
	           			var id=data.maxId;
	           			var treeNode={id:id, pId:pId,isParent:false, name:name};
	           			zTree.addNodes(nodes[0],treeNode);
	                }
                  }
            	  zTree.selectNode(parentNode);
            	  Location.search(parentNode.id);
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

Location.onClick= function (event, treeId, treeNode) {
	Location.search(treeNode.id);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if(treeNode.open){
		zTree.expandNode(treeNode, false, false, true);
	}else{
		zTree.expandNode(treeNode, true, false, true);
	}
};


Location.setting = {
		async: {
			enable: true
		},
		data: {
			key:{
				name:"name"
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: '0'
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
			beforeEditName: Location.beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
			beforeRename:Location.beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求
			onRename:Location.onRename,//编辑后触发，用于操作后台
			onClick:Location.onClick 
		}
		
	}; 
	
	  
/**修改节点名称*/
Location.edit=function() {
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	if (nodes.length == 0) {
		Feng.info('请先选择一个节点');
		return;
	}
	zTree.editName(treeNode);
};
	

Location.beforeEditName=function (treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(treeNode);
	return true;
}

   
/**
 * 删除部门管理
 */
Location.del = function () {
    if (this.check()) {
    	
    	var id=this.seItem.id;
    	var name=this.seItem.name;
    	
    	var zTree = $.fn.zTree.getZTreeObj("tree");
    	var node = zTree.getNodeByParam("name", name, null);
    	var parentNode = node.getParentNode();
    	var parentId=parentNode.id;
       	var params = {
       			id : id
    	}
        Feng.confirm("确定删除？",function() { 
			$.ajax({
				type : 'POST',
	 			url : Feng.ctxPath + "/location/delete",
	 			async : true,
	 			data : params,
	 			dataType : 'json',
	 			beforeSend : function(){
	 			},
	 			success : function(data){ 
	 				if(data.result == '1'){
	 					zTree.removeNode(node);
	 					Feng.success('删除成功');
	 					Location.search(parentId);
	 					return;
	 				}else if(data.result == '-1'){
	 					Feng.error('参数为空');
	 					return;
	 				}else if(data.result == '2'){
	 					Feng.error('删除失败，地区正在使用中！');
	 					return;
	 				}else if(data.result == '3'){
	 					Feng.error('存在下级地区，无法删除！');
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
Location.onloadZTree=function (){  
  var ztreeNodes;  
  $.ajax( {  
       async : true, //是否异步  
       cache : false, //是否使用缓存  
       type : 'post', //请求方式,post  
       dataType : "json", //数据传输格式  
       url : Feng.ctxPath + "/location/select", //请求链接   
       error : function() {  
    	   Feng.error('网络繁忙.......！');
       },  
       success : function(data) {  
            ztreeNodes = data.locationList; //将string类型转换成json对象  
            $.fn.zTree.init($( "#tree"), Location.setting, ztreeNodes);  
       }  
  });  
}  

/**
 * 查询公司管理列表
 */
Location.search = function (id) {
    var queryData = {
    	pId: id
    }
        
    Location.table.refresh({query: queryData});
};  
   
//初始化操作  
$(document).ready( function(){  
	  Location.onloadZTree(); 
	  
	  var defaultColunms = Location.initColumn();
      var table = new BSTable(Location.id, "/location/selectLocationByPage", defaultColunms);
      table.setPaginationType("server");
      table.setQueryParams({pId:'0'});
      Location.table = table.init();

      $("#addLeaf").bind("click",{isParent:true},Location.add);
	  $("#editName").bind("click", Location.edit);
});
   
