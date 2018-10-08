package com.whxx.core.filter;


import com.whxx.core.common.RestConstant;
import com.whxx.core.common.RestParams;
import com.whxx.core.properties.RestProperties;
import com.whxx.core.utils.RedisUtil;
import com.whxx.core.utils.RenderUtil;
import com.whxx.core.utils.jwt.JwtProperties;
import com.whxx.core.utils.jwt.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangxiang
 */
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private static final List<String> NO_CHECK_URL_LIST = Arrays.asList("/login", "/register", "/logout");

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RestProperties restProperties;
    @Autowired
    private RedisUtil redisUtil;

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String servletPath = request.getServletPath();
        //过滤静态资源
        if (!doFilterResource(servletPath))
        {
            //过滤路径
            if (!NO_CHECK_URL_LIST.contains(servletPath))
            {
                String token = request.getHeader(JwtProperties.getTokenHeader());
                if (StringUtils.isNotEmpty(token))
                {
                    try {
                        // 验证token是否过期
                        jwtTokenUtil.isTokenExpired(token);
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
     * 判断请求类型，根据不同类型输出不同返回类型
     *
     * @param servletPath url路径
     * @param request     http请求
     * @param response    http响应
     */
    private void isAjaxOrRestful(String servletPath, HttpServletRequest request, HttpServletResponse response) {
        //* or origin as u prefer
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "content-type, authorization,access_token,api_version");
        if (servletPath.contains(restProperties.getRestfulSign())) {
            RenderUtil.renderJson(response, RestParams.newInstance(RestConstant.TOKEN_NOT_EXISTS_CODE, RestConstant.TOKEN_NOT_EXISTS_DESC, null));
        }else {
            RenderUtil.renderJson(response, RestParams.newInstance(RestConstant.TOKEN_NOT_MATCH_CODE, RestConstant.TOKEN_NOT_EXISTS_DESC, null));
        }
    }

    /**
	 * 过滤静态资源
     *
     *  doFilterResource
     * @param servletPath servlet路径
     * @return 是：true 不是：false
     */
    private boolean doFilterResource(String servletPath)
    {

        final String[] resourceSuffix = {".js", ".css", ".jpg", ".png", ".gif", ".ico", ".pdf"};
        for (String suffix : resourceSuffix)
        {
            if (servletPath.toLowerCase().endsWith(suffix))
            {
				return true;
			}
		}
		return false;
	}

}