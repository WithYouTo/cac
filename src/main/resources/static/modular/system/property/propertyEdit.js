/**
 * 房产维护
 */
var PropertyEdit = {
    changeAreaFlag:false,
    PropertyInfoData: {},
    ztreeInstance: null
};

PropertyEdit.onExpand = function (event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("tree"); //加载指定节点  
	treeObj.selectNode(treeNode.children[0]); 
};
	  
PropertyEdit.beforeExpand= function (treeId, treeNode) {  
	if(!treeNode.children){
		getData(event, treeId, treeNode);
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var s=treeNode.children;
    	var node = treeObj.getNodeByParam("text", "undefined", null);
    	treeObj.removeNode(node);	
	}
};
PropertyEdit.onClick=function (event, treeId, treeNode) {
	PropertyEdit.getData(event, treeId, treeNode);
};

PropertyEdit.setting={
		async: {
			enable: true
		},
		data: {
			key:{
				name:"text"
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: 0
			}
		},
		view: {
			dblClickExpand: false,
			selectedMulti: false
		},
		edit: {
			enable: false
		},
		callback: {
			beforeExpand:PropertyEdit.beforeExpand,
			onExpand:PropertyEdit.onExpand,
			onClick: PropertyEdit.onClick
		}
};


//加载ztree  
//var ztreeNodes;  
PropertyEdit.onloadZTree=function (){  
      $.ajax({  
           async : true, //是否异步  
           cache : false, //是否使用缓存  
           type : 'post', //请求方式,post  
           dataType : "json", //数据传输格式  
           url : Feng.ctxPath + "/property/selectTreeCompany", //请求链接  
           error : function() {  
        	   Feng.error('网络繁忙.......！');
           },  
           success : function(data) {  
        	   //PropertyEdit.ztreeNodes = data; //将string类型转换成json对象  
                $.fn.zTree.init($( "#tree"), PropertyEdit.setting, data);  
           }  
      });  
}  ;
 
PropertyEdit.JSONLength= function(obj) {
	var size = 0, key;
	for (key in obj) {
		if (obj.hasOwnProperty(key))
			size++;
	}
	return size;
};

PropertyEdit.getData=function (event, treeId, treeNode){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		if (treeNode.layer != "5" ) {
			//展开子节点
			//已经有节点了，点击应该进行收起展开切换
			treeObj.selectNode(treeNode);
			var isOpen = treeNode.open;
			if(isOpen){
//					treeObj.selectNode(treeNode);
				treeObj.expandNode(treeNode, false, true, true);
			}else{
				treeObj.expandNode(treeNode, true, true, true);
			}
			
			var layer = treeNode.layer;
			var id = treeNode.cid;
			var num;
			if (layer != "4") {
				num = eval(layer)
			} else {
				num = eval(layer + "+" + 1)
			}
			var len = 0;
			var nodes = treeNode.children;
			len=PropertyEdit.JSONLength(nodes);
			if (len <=0) {
				$.ajax({
					async : false,  
					type : 'post',  
					data : {
						"id" : id
					},
					dataType : "json", //数据传输格式  
					url :Feng.ctxPath  + "/property/selectAa0" + num, //请求链接  
					error : function() {
						 Feng.error('网络繁忙.......！');
					},
					success : function(data) {
						treeObj.selectNode(treeNode); 
						treeObj.addNodes(treeNode,data,false);
						treeObj.expandNode(treeNode, true, true, true);
					}
				});
			}
		}
		if (treeNode.layer == "5") {
			//查询数据
			var tbaa05Id = treeNode.cid;
			//查询前先将房间id保存在页面中
			$("#tbaa05Id").val(tbaa05Id);
			$.ajax({
				type : 'post',
				async : false, 
				data : {
					"tbaa05Id" : tbaa05Id
				},
				dataType : 'json',
				url : Feng.ctxPath  + '/property/getRoomInfo',
				success : function(data) {
					var zdFields=['building','roomName','areaName','areaCode','haveGate','calArea','realArea','ownerName'
						,'ownerTel','wyPrice','wyAmount','remark1','hh','fh'];
					$.each(zdFields,function(index,item){
						$('#'+item).val(data[item]); 
					});
//					$('#building').val( data.building);
//					$('#roomName').val( data.roomName);
//					$('#areaName').val( data.areaName);
//					$('#areaCode').val( data.areaCode);
//					$('#haveGate').val( data.haveGate);
//					$('#calArea').val( data.calArea);
//					$('#realArea').val( data.realArea);
//					$('#ownerName').val( data.ownerName);
//					$('#ownerTel').val( data.ownerTel);
//					$('#wyPrice').val( data.wyPrice);
//					$('#wyAmount').val( data.wyAmount);
//					$('#remark1').val( data.remark1);
//					$('#hh').val( data.hh);
//					$('#fh').val( data.fh);
				},
				error : function() {
				     Feng.error('服务器繁忙.......！');
				}
			});
		}
	};
	
PropertyEdit.save=function(){
		
		//没有加载房屋信息，不调后台请求
	    if(""==$("#roomName").val()||null==$("#roomName").val()){
	    	return;
	    }
		
		//取出保存的房间id
		var tbaa05Id=$("#tbaa05Id").val();
		var ownerName=$("#ownerName").val();
		var ownerTel=$("#ownerTel").val();
		var remark1=$("#remark1").val();
		var paramMap={
				tbaa05Id : tbaa05Id,
				ownerName :ownerName,
				ownerTel : ownerTel,
				remark1 : remark1
		}
		$.ajax({
			async : false,
			type : 'post',
			data : paramMap,
			datatype : 'json',
			url : Feng.ctxPath  +"/property/updateOwnerInfo",
			beforeSend :function(){
				
			},
			success : function(data){
				if(data.flag=="1"){
					Feng.success("户主信息变更成功!");
				}else{
					Feng.error('户主信息变更失败');
				}
			}
		});
};
//初始化操作  
//$(document).ready( function(){  
//	PropertyEdit.onloadZTree(); 
//});

$(function () {
  PropertyEdit.onloadZTree(); 
});
