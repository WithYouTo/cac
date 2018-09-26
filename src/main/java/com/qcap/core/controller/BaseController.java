package com.qcap.core.controller;

import com.qcap.core.kit.HttpKit;
import com.qcap.core.model.Menu;
import com.qcap.core.tips.SuccessTip;
import com.qcap.core.warpper.BaseControllerWarpper;
import com.qcap.cac.model.TbUser;
import com.qcap.core.tips.UnAuthTip;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class BaseController {

    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    public static final String LOGIN_USER = "manager";


    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    protected static UnAuthTip UNAUTH_TIP = new UnAuthTip();

    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }

    protected TbUser getTbUser(){
        return (TbUser)getSession().getAttribute("manager");
    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
//    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
//        return new PageInfoBT<T>(page);
//    }

    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object warpObject(BaseControllerWarpper warpper) {
        return warpper.warp();
    }

    /**
     * 删除cookie
     */
    protected void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 返回前台文件流
     *
     * @author fengshuonan
     * @date 2017年2月28日 下午2:53:19
     */
//    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
//        byte[] bytes = FileUtil.toByteArray(filePath);
//        return renderFile(fileName, bytes);
//    }

    /**
     * 返回前台文件流
     *
     * @author fengshuonan
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }

    public boolean checkAuth(String url){
        HttpSession session = getSession();
        List<Menu> auths  = (List<Menu>) session.getAttribute("auths");
        for(Menu auth:auths){
            if(url.equals(auth.getUrl())){
                return true;
            }
        }
        return false;
    }

    public static String getLoginUserAccount() {
        return null == getLoginUser() ? "" : getLoginUser().getAccount();
    }

    public static TbUser getLoginUser() {
        return null == HttpKit.getSession() ? null : (TbUser) HttpKit.getSession().getAttribute(LOGIN_USER);
    }

    public static <T> T setCreateEmpAndTime(T t) {
        if (null == t){
            return t;
        }
        try {
            PropertyUtils.setProperty(t, "createEmp", getLoginUserAccount());
            PropertyUtils.setProperty(t, "createDate", new Date());
        } catch (Exception e) {
        }
        return t;
    }


    public static <T> T setUpdateEmpAndTime(T t) {
        if (null == t){
            return t;
        }
        try {
            PropertyUtils.setProperty(t, "updateEmp", getLoginUserAccount());
            PropertyUtils.setProperty(t, "createDate", new Date());
        } catch (Exception e) {
        }
        return t;
    }
}
