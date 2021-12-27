package com.eshop.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;


public class CustomOAuth2User implements OAuth2User {

	private OAuth2User oAuth2User;
	
	public CustomOAuth2User(OAuth2User oauth2User) {
        this.oAuth2User = oauth2User;
    }

	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oAuth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oAuth2User.getAttribute("name");
	}
	
	public String getFullname() {
        return oAuth2User.getAttribute("name");     
    }
	// của facebook và github là id &&  ảnh là: picture || data || url
	// của google là sub && ảnh của google là picture
	// ảnh của github là: avatar_url
	public String getId() {
        return oAuth2User.getAttribute("id");     
    }
	
	
	public String getUsername() {
        return oAuth2User.getAttribute("email");     
    }
	
	public String getEmail() {
        return oAuth2User.<String>getAttribute("email");     
    }
	


}
