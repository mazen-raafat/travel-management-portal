package com.crossover.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crossover.prime.faces.beans.LoginUser;
import com.crossover.prime.faces.beans.SessionUtils;

/** 
 * @author Mazen
 */
@WebFilter("/pages/user/protected/*")
public class LoginFilter implements Filter {

	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Get the loginBean from session attribute
		LoginUser loginBean = (LoginUser) ((HttpServletRequest) request).getSession().getAttribute("loginUser");

		// For the first application request there is no loginBean in the
		// session so user needs to log in
		// For other requests loginBean is present but we need to check if user
		// has logged in successfully
		
		if (loginBean == null || !loginBean.isLoggedin()) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/pages/user/public/login.xhtml");
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}

}
