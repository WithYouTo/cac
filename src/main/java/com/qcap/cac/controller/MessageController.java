package com.qcap.cac.controller;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.MessageDto;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//@Api(tags = "PC端通知公告")
@RestController
@RequestMapping(value = "message")
public class MessageController {

	@Resource
	private MessageRestSrv messageRestSrv;

	/**
	 * 
	 * 通知公告列表
	 * @Title: listMessage
	 * @param messageDto
	 * @return
	 * @return: Object
	 */
   @ResponseBody
   @RequestMapping(value = "/listMessage", method = RequestMethod.POST)
   public Object listMessage( @Valid MessageDto messageDto){
	   new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String , Object>>list = this.messageRestSrv.listMessage(messageDto);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
   }

   /**
    * 新增通知公告
    *
    * @Title: insertMessage
    * @return
    * @return: Object
    */
   @RequestMapping(value = "/insertMessage", method = RequestMethod.POST)
   public Object insertMessage( @Valid MessageDto messageDto){
       String str="PC";
       return this.messageRestSrv.insertMessage(messageDto,str);

   }
   
   /**
    * 修改通知公告
    *
    * @Title: updateMessage
    * @param messageDto
    * @return
    * @return: Object
    */
   @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
   public Object updateMessage( @Valid MessageDto messageDto,@RequestParam("messageId")String messageId){
       messageDto.setMessageId(messageId);

//       this.messageRestSrv.JpushMessage("8040654","11111111111","测试测测试测试","lalalallalallall");


	   return this.messageRestSrv.updateMessage(messageDto);

   }
   
   /**
    * 
    * 删除通知公告
    * @Title: deleteMessage 
    * @param messageId
    * @return
    * @return: Object
    */
   @ResponseBody
   @RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
   public Object deleteMessage(String messageId){
       
       return this.messageRestSrv.deleteMessage(messageId);

   }

}
