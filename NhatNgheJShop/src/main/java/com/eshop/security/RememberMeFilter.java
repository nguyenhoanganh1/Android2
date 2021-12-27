package com.eshop.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;


public class RememberMeFilter extends RememberMeAuthenticationFilter   {

	public RememberMeFilter(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
		super(authenticationManager, rememberMeServices);
		// TODO Auto-generated constructor stub
	}

	Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
		return null;
		
	} 
	

}
