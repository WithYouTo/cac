package com.qcap.cac.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcap.cac.tools.JpushTools;
import com.qcap.cac.tools.RedisTools;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/demo")
public class DemoController {

	// @Resource
	// private RedisUtil redisUtil;

	@RequestMapping(value = "/jpushTest", method = RequestMethod.POST)
	@ApiOperation(value = "极光推送", notes = "极光推送", response = Map.class, httpMethod = "POST")
	@ResponseBody
	public Object jpushTest() throws Exception {
		String masterSecret1 = RedisTools.getCommonConfig("dev", "CAC_MASTER_SECRET_KEY");
		String appKey1 = RedisTools.getCommonConfig("CAC_APP_KEY");

		String masterSecret2 = RedisTools.getCommonConfig("CAC_MASTER_SECRET_KEY");
		String appKey2 = RedisTools.getCommonConfig("CAC_APP_KEY");

		String userNo = "user1";
		List<String> userArr = Arrays.asList("user1", "user2", "user3");
		String msg = "helloworld!";
		// 广播
		JpushTools.broadcast(masterSecret2, appKey2, msg);
		// 发送单一目标
		JpushTools.pushSingle(masterSecret2, appKey2, userNo, msg);
		// 发送多个目标
		JpushTools.pushArray(masterSecret2, appKey2, userArr, msg);
		return null;
	}
}
