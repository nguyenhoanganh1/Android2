

package com.eshop.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

import com.eshop.dao.CustomerDAO;
import com.eshop.dao.RoleDAO;
import com.eshop.entity.Customer;
import com.eshop.entity.Provider;
import com.eshop.security.CustomOAuth2User;
import com.eshop.utils.PasswordEncoder;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerService {
	@Autowired
	private CustomerDAO cdao;
	
	
	private OAuth2UserRequest oAuth2UserRequest;
	
	@Autowired
	private RoleDAO rdao;
	
	@Autowired
	private MailerService mailer;
	
	public Customer getCustomerById(String id) {
		return cdao.findCustomer(id);
	}
	
	public void registerCustomer(Customer customer) {
		Customer newUser = new Customer();
		newUser.setEmail(customer.getEmail());
		newUser.setProvider(Provider.LOCAL);
		newUser.setActivated(true);
		newUser.setFullname(customer.getFullname());
		newUser.setPhoto("user.png");
		newUser.addRole(rdao.findByName("Customer"));
		cdao.save(newUser);
	}
	
	public void forgotPassword(Customer customer) {
		String OTP = RandomString.make(8);
		String from = "EShop <hoanganh25022@gmail.com>";
		String to = customer.getEmail();
		String subject = "Hello " + customer.getFullname();
		String body = "Mật khẩu mới của bạn là: " + OTP + "</hr>";
					
		mailer.send(from, to, subject, body);		
		String encrypt = PasswordEncoder.setBCryptPasswordEncoder(OTP);		 
		customer.setPassword(encrypt);		
		cdao.save(customer);
	}
	
	public void generateOneTimePassword(Customer customer) {
		String OTP = RandomString.make(8);
		String from = "EShop <hoanganh25022@gmail.com>";
		String to = customer.getEmail();
		String subject = "Hello " + customer.getFullname();
		String body = "Mã OTP của bạn là: " + OTP + "<hr/>" 
					+ "Mã OTP có hiệu lực trong vòng 5 phút";
		mailer.send(from, to, subject, body);
		String encrypt = PasswordEncoder.setBCryptPasswordEncoder(OTP);		 	
		customer.setOneTimePassword(encrypt);
		customer.setOtpTime(new Date());		
		cdao.save(customer);
	}
	public void RegisterUserLoginSocial(OAuth2UserRequest oAuth2UserRequest, CustomOAuth2User UserInfo) {
		
		Customer newUser = new Customer();
		newUser.setId(UserInfo.getId());
		newUser.setEmail(UserInfo.getEmail());
		newUser.setProvider(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		newUser.setActivated(true);
		newUser.setFullname(UserInfo.getName());
		newUser.setPhoto("user.png");
		newUser.addRole(rdao.findByName("Customer"));
		cdao.save(newUser);
	}
	
	public void CreateNewCustomerAfterOAthLoginSucces(String id,String name, String email) {
		Customer existCustomer = cdao.findCustomer(id);
		Customer newUser = new Customer();
		newUser.setId(id);
		newUser.setEmail(email);
		
		newUser.setProvider(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		newUser.setActivated(true);
		newUser.setFullname(name);
		newUser.setPhoto("user.png");
		newUser.addRole(rdao.findByName("Customer"));
		cdao.save(newUser);
		/*
		 * if (id != null) { if (existCustomer == null) { Customer newUser = new
		 * Customer(); newUser.setId(id); newUser.setEmail(email);
		 * newUser.setProvider(Provider.facebook); newUser.setActivated(true);
		 * newUser.setFullname(name); newUser.setPhoto("user.png");
		 * newUser.addRole(rdao.findByName("Customer")); cdao.save(newUser); } else {
		 * Customer newUser = new Customer(); newUser.setId(id);
		 * newUser.setEmail(email); newUser.setProvider(Provider.facebook);
		 * newUser.setActivated(true); newUser.setFullname(name);
		 * newUser.setPhoto("user.png"); newUser.addRole(rdao.findByName("Customer"));
		 * cdao.save(newUser); }
		 * 
		 * } else { if (existCustomer == null) { Customer newUser = new Customer();
		 * newUser.setId(email); newUser.setEmail(email);
		 * newUser.setProvider(Provider.google); newUser.setActivated(true);
		 * newUser.setFullname(name); newUser.setPhoto("user.png");
		 * newUser.addRole(rdao.findByName("Customer")); cdao.save(newUser); } else {
		 * Customer newUser = new Customer(); newUser.setId(email);
		 * newUser.setEmail(email); newUser.setProvider(Provider.google);
		 * newUser.setActivated(true); newUser.setFullname(name);
		 * newUser.setPhoto("user.png"); newUser.addRole(rdao.findByName("Customer"));
		 * cdao.save(newUser); }
		 * 
		 * }
		 */
		
	}

	public void clearOTP(Customer customer) {
		customer.setOneTimePassword(null);
		customer.setOtpTime(null);
		cdao.save(customer);
	}
}
