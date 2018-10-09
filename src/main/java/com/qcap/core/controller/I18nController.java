package com.qcap.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/i18n")
public class I18nController {

	@RequestMapping("/changeSessionLanguage")
	public String changeSessionLanauage(HttpServletRequest request, HttpServletResponse response, String lang) {
		System.out.println(lang);
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		localeResolver.setLocale(request, response, LocaleUtils.toLocale(lang));
		return "/login";
	}
}
