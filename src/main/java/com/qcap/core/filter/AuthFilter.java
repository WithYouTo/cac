package com.qcap.core.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.util.StringUtil;
import com.qcap.core.entity.TbManager;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.RedisUtil;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.qcap.cac.tools.RedisTools;
import com.qcap.core.common.RestConstant;
import com.qcap.core.common.RestParams;
import com.qcap.core.properties.RestProperties;
import com.qcap.core.utils.RenderUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author huangxiang
 */
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

	// private static final List<String> NO_CHECK_URL_LIST =
	// Arrays.asList("/login", "/register", "/logout", "/v2/**",
	// "/swagger-ui.html", "/swagger-resources/**", "/eventTask/**");

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Resource
	private JwtProperties jwtProperties;

	@Resource
	private RedisUtil redisUtil;

	@Autowired
	private RestProperties restProperties;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String servletPath = request.getServletPath();
		// 过滤静态资源
		if (!doFilterResource(servletPath)) {
			// 过滤路径
			try {
				List<String> noCheckUrlList = this.getNoCheckUrl();
				if (noCheckUrlList.stream().noneMatch(e -> antPathMatcher.match(e, servletPath))) {
					String token = request.getHeader(JwtProperties.getTokenHeader());

					if (StringUtils.isNotEmpty(token)) {

						// 验证token是否过期
						jwtTokenUtil.isTokenExpired(token);
//						if(jwtTokenUtil.isTokenExpired(token)){
//							final Date createdDate = new Date();
//							final Date expirationDate = new Date(createdDate.getTime() + 10 * 1000);
//							Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody().setExpiration(expirationDate);
//						}else{
//							return;
//						}
						//						String managerId = jwtTokenUtil.getUsernameFromToken(token);
//						String m = redisUtil.get(AppUtils.getApplicationName() + ":manager:" + managerId);
//						if(StringUtil.isEmpty(m)){
//							return;
//						}
//						TbManager m = redisUtil.get(AppUtils.getApplicationName() + ":manager:" + managerId, TbManager.class);

					} else {
						// header没有token
						isAjaxOrRestful(servletPath, request, response);
						return;
					}

				}
			} catch (ExpiredJwtException e) {
				// 过期直接抛异常
				logger.error("token超时异常:" + "_" + e.getMessage(), e);
				isAjaxOrRestful(servletPath, request, response);
				return;
			} catch (JwtException e) {
				// 有异常就是token解析失败
				logger.error("token解析异常:" + "_" + e.getMessage(), e);
				isAjaxOrRestful(servletPath, request, response);
				return;
			} catch (Exception e) {
				// 获取配置异常
				logger.error("获取配置异常:" + "_" + e.getMessage(), e);
				isAjaxOrRestful(servletPath, request, response);
				return;
			}

		}
		chain.doFilter(request, response);
	}

	/**
	 * 判断请求类型，根据不同类型输出不同返回类型
	 *
	 * @param servletPath
	 *            url路径
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 */
	private void isAjaxOrRestful(String servletPath, HttpServletRequest request, HttpServletResponse response) {
		// * or origin as u prefer
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "content-type, authorization,access_token,api_version");
		if (servletPath.contains(restProperties.getRestfulSign())) {
			RenderUtil.renderJson(response, RestParams.newInstance(RestConstant.TOKEN_NOT_EXISTS_CODE,
					RestConstant.TOKEN_NOT_EXISTS_DESC, null));
		} else {
			RenderUtil.renderJson(response, RestParams.newInstance(RestConstant.TOKEN_NOT_MATCH_CODE,
					RestConstant.TOKEN_NOT_EXISTS_DESC, null));
		}
	}

	/**
	 * 过滤静态资源
	 *
	 * doFilterResource
	 * 
	 * @param servletPath
	 *            servlet路径
	 * @return 是：true 不是：false
	 */
	private boolean doFilterResource(String servletPath) {

		final String[] resourceSuffix = { ".js", ".css", ".jpg", ".png", ".gif", ".ico", ".pdf" };
		for (String suffix : resourceSuffix) {
			if (servletPath.toLowerCase().endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

	private List<String> getNoCheckUrl() throws Exception {
		// String key = AppUtils.getApplicationName() + StrUtil.COLON + "SYSTEM"
		// + StrUtil.COLON + "CAC_NO_CHECK_URL";
		String value = RedisTools.getCommonConfig("CAC_NO_CHECK_URL");
		if (StringUtils.isNotBlank(value)) {
			String[] urlArray = value.split(";");
			return Arrays.asList(urlArray);
		} else {
			throw new Exception("缺少免登陆url配置");
		}
	}

}