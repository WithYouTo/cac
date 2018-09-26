package com.qcap.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

	@RequestMapping("/")
	public String indexPage() {
		return "/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "/index";
	}

	@RequestMapping("/blackboard")
	public String blackboard() {
		return "/blackboard";
	}

	@RequestMapping("/toUser")
	public String toUser() {
		return "/system/user/user";
	}

	@RequestMapping("/toRole")
	public String toRole() {
		return "/system/role/role";
	}

	@RequestMapping("/toMenu")
	public String toMenu() {
		return "/system/menu/menu";
	}

	@RequestMapping("/toOrg")
	public String toOrg() {
		return "/system/org/org";
	}

	@RequestMapping("/toTest")
	public String toTest() {
		return "/system/test/test";
	}

	@RequestMapping("/toPrint")
	public String toPrint() {
		return "/system/print/print";
	}

	@RequestMapping("/toPrint2")
	public String toPrint2() {
		return "/system/print2/print";
	}

	@RequestMapping("/toVePrint")
	public String toVePrint() {
		return "/system/vehicle/print";
	}

	@RequestMapping("/toPrinted")
	public String toPrinted() {
		return "/system/print/printed";
	}

	@RequestMapping("/toPrinted2")
	public String toPrinted2() {
		return "/system/print2/printed";
	}

	@RequestMapping("/toVePrinted")
	public String toVePrinted() {
		return "/system/vehicle/printed";
	}

	@RequestMapping("/toRegister")
	public String toRegister() {
		return "/system/print/register";
	}

	@RequestMapping("/toVeRegister")
	public String toVeRegister() {
		return "/system/vehicle/register";
	}

	@RequestMapping("/onScreen")
	public String onScreen() {
		return "/system/print/screen";
	}

	@RequestMapping("/toVeScreen")
	public String toVeScreen() {
		return "/system/vehicle/screen";
	}

	@RequestMapping("/toCheck")
	public String toCheck() {
		return "/system/print/check";
	}

	@RequestMapping("/toDemo")
	public String toDemo() {
		return "/system/print/demo";
	}

	@RequestMapping("/toInfo")
	public String toInfo() {
		return "/system/info/info";
	}

	@RequestMapping("/toSignInfo")
	public String toSignInfo() {
		return "/system/info/signInfo";
	}
}
