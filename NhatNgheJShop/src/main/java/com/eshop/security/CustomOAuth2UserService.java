package com.eshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.eshop.dao.CustomerDAO;
import com.eshop.dao.RoleDAO;
import com.eshop.entity.Customer;
import com.eshop.entity.Provider;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
	@Autowired
	private CustomerDAO cdao;
	
	@Autowired
	private RoleDAO rdao;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		return new CustomOAuth2User(super.loadUser(userRequest));
	}
	
	public Customer RegisterUserLoginSocial(OAuth2UserRequest oAuth2UserRequest, CustomOAuth2User UserInfo) {
		Customer newUser = new Customer();
		newUser.setId(UserInfo.getEmail());
		newUser.setEmail(UserInfo.getEmail());
		newUser.setProvider(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		newUser.setActivated(true);
		newUser.setFullname(UserInfo.getName());
		newUser.setPhoto("user.png");
		newUser.addRole(rdao.findByName("Customer"));
		return newUser;
		
	}
	//	processOAuthPostLogin
}
