package com.qcap.core.filter;


import com.qcap.core.common.RestConstant;
import com.qcap.core.common.RestParams;
import com.qcap.core.model.Menu;
import com.qcap.core.properties.FameProperties;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.cac.model.TbUser;
import com.qcap.core.utils.RenderUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AuthFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private AntPathMatcher antPathMatcher =new AntPathMatcher();
   
	@Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FameProperties fameProperties;

    @Autowired
    private RedisUtil redisUtil;
    

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        

        String[] anonPath = fameProperties.getAnonPath();
		String servletPath = request.getServletPath();

		//过滤系统交互之间的回调， (二维码支付的回调)不进行token验证
//		if(servletPath!=null&&servletPath.indexOf(espProperties.getPayCallBack())>=0) {
//
//		}
//		//restful 请求不做token验证
//		else if(servletPath!=null&&servletPath.indexOf(espProperties.getRestfulSign())>=0) {
//
//		}
        //过滤静态资源
		  if (!doFilterResourse(servletPath)) {
            //过滤路径
//            if (!servletPath.equals("/app_index/login")
//                    && !servletPath.equals("/app_index/sendMsg")
//                    && !servletPath.equals("/app_index/forget")
//                    && !servletPath.equals("/login")
//                    && !servletPath.equals("/register")
//                    && !servletPath.equals("/logout") ){
//            if(!Arrays.asList(anonPath).contains(servletPath)){
                if(Arrays.stream(anonPath).noneMatch(e->antPathMatcher.match(e,servletPath))){
                String token = request.getHeader(jwtProperties.getTokenHeader());
                if(StringUtils.isEmpty(token)) {
                	token=request.getParameter("access_token");
                }
                String authToken = null;
                if (token != null && !token.equals("")) {
                    authToken = token;

                    // 验证token是否过期
                    try {
                    	Map data = redisUtil.getMap(authToken);//去除redis中的原始数据
                    	if(data == null || data.isEmpty()) {
                    		jwtTokenUtil.isTokenExpired(authToken);// 同时会校验token是否正确，如不正确就会抛
                    	}
                    	String userId = TenpayUtil.toString(data.get("userId"));
                    	if(userId != null && userId.contains("_123")) {//APP登录
                    		jwtTokenUtil.isTokenExpired(authToken);// 同时会校验token是否正确，如不正确就会抛
                    	}else {//PC登录
                    		String longMills = TenpayUtil.toString(data.get("time"));                    	
                        	Date expiratedDate = TenpayUtil.long2Date(TenpayUtil.toLong(longMills));
                        	Date now = new Date();
                        	Date point = TenpayUtil.newDate(expiratedDate, Calendar.MINUTE, -60);

                        	//point为token失效的前60分钟
                        	if(!now.before(point))  {
                        		if(now.before(expiratedDate)) {	  
                        			redisUtil.delete(authToken);
                        			//设置redis键的过期时间为60分钟
                    				Date newDate = TenpayUtil.newDate(now, Calendar.MINUTE, 300);
                        			long newEnd = newDate.getTime();      
                        			data.put("time", TenpayUtil.toString(newEnd));//刷新token的过期时间
                        			
                        			redisUtil.setMap(token,data);//存储token新的过期时间和用户ID
                                    redisUtil.setExpire(token, 300, TimeUnit.MINUTES);//设置key过期时间
                            	}else {
                            		jwtTokenUtil.isTokenExpired(authToken);// 同时会校验token是否正确，如不正确就会抛
                            	}
                        	}  
                    	}	                	
                    } catch (ExpiredJwtException e) {
                        // 过期直接抛异常
                        logger.error("token超时异常:" + "_" + e.getMessage(), e);
                        isAjaxOrRestful(servletPath,request,response);
                        return;
                    } catch (JwtException e) {
                        // 有异常就是token解析失败
                        logger.error("token解析异常:" + "_" + e.getMessage(), e);
                        isAjaxOrRestful(servletPath,request,response);
                        return;
                    }
                } else {
                    // header没有token
                    isAjaxOrRestful(servletPath,request,response);

                    return;
                }
            }

        }

		chain.doFilter(request, response);
	}


	/**
	 *
	 * @Description: 判断请求类型，根据不同类型输出不同返回类型
	 *
	 *
	 * @MethodName: isAjaxOrRestful
	 * @Parameters: [response]
	 * @ReturnType: void
	 *
	 * @author huangxiang
	 * @date 2018/5/15 10:48
	 */
    private void isAjaxOrRestful(String servletPath, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");//* or origin as u prefer
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "content-type, authorization,access_token,api_version");
        if (servletPath.indexOf(fameProperties.getRestfulSign()) >= 0) {
            RenderUtil.renderJson(response, RestParams.newInstance(RestConstant.TOKEN_NOT_EXISTS_CODE,
							RestConstant.TOKEN_NOT_EXISTS_DESC, null));
            return;
        }else {
            RenderUtil.renderJson(response, RestParams.newInstance(RestConstant.TOKEN_NOT_MATCH_CODE,
                        RestConstant.TOKEN_NOT_EXISTS_DESC, null));
            return;
        }
    }


    /**
     *
     * @Title: getUserSession
     * @param request
     * @return  session有效 true  ; 失效 false
     * @return: boolean
     */
    boolean getUserSession(HttpServletRequest request) {

        boolean isLogin = false;
        try {
            HttpSession session = request.getSession();
            if (null != session) {
            	TbUser manager = (TbUser) session.getAttribute("manager");
                if (null != manager) {
                    isLogin = true;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return isLogin;
    }

    public boolean checkAuth(HttpServletRequest request,String url){
        HttpSession session = request.getSession();
        List<Menu> auths  = (List<Menu>) session.getAttribute("auths");
        for(Menu auth:auths){
            if(url.equals(auth.getUrl())){
                return true;
            }
        }
        return false;
    }

    /**
	 * 过滤静态资源
	 * 
	 * @Title: doFilterResourse
	 * @param servletPath
	 * @return 是：true 不是：false
	 * @return: boolean
	 */
	private boolean doFilterResourse(String servletPath) {

		final String resourseSuffix[] = { ".js", ".css", ".jpg", ".png", ".gif", ".ico", ".pdf" };
		for (String suffix : resourseSuffix) {
			if (servletPath.toLowerCase().endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

}