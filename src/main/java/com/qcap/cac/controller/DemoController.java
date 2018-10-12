package com.qcap.cac.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcap.cac.tools.JpushTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.core.utils.RedisUtil;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Resource
	private RedisUtil redisUtil;

	@RequestMapping(value = "/jpushTest", method = RequestMethod.POST)
	@ApiOperation(value = "极光推送", notes = "极光推送", response = Map.class, httpMethod = "POST")
	@ResponseBody
	public Object jpushTest() throws Exception {
		String masterSecret = RedisTools.getCommonConfig(redisUtil, "SYSTEM", "CAC_MASTER_SECRET_KEY");
		String appKey = RedisTools.getCommonConfig(redisUtil, "SYSTEM", "CAC_APP_KEY");
		String userNo = "user1";
		List<String> userArr = Arrays.asList("user1", "user2", "user3");
		String msg = "helloworld!";
		// 广播
		JpushTools.broadcast(masterSecret, appKey, msg);
		// 发送单一目标
		JpushTools.pushSingle(masterSecret, appKey, userNo, msg);
		// 发送多个目标
		JpushTools.pushArray(masterSecret, appKey, userArr, msg);
		return null;
	}
}
