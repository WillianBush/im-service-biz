package net.chenlin.dp.common.xss;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * XSS过滤
 * @author wang<fangyuan.co@outlook.com>
 */
@Slf4j
public class XssFilter implements Filter {

	private FilterConfig filterConfig = null;

	private final List<String> urlExclusion = null;

	@Override
	public void init(FilterConfig config) {
		this.filterConfig = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String servletPath = httpServletRequest.getServletPath();
		httpServletRequest.getParameterMap();
//		log.info("servletPath:{}",servletPath);
		if (getUrlExclusion() != null && UrlExclusion(servletPath)) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(new XssHttpServletRequestWrapper(httpServletRequest), response);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	public List<String> getUrlExclusion() {
		List<String> urlExclusion = new LinkedList<>();
		urlExclusion.add("/forward");
		urlExclusion.add("/api");
		return urlExclusion;
	}

	private boolean UrlExclusion(String servletPath ){
		for (String urlExclusion : getUrlExclusion()) {
			if (servletPath.startsWith(urlExclusion)) {
				return true;
			}
		}
		return false;
	}



}