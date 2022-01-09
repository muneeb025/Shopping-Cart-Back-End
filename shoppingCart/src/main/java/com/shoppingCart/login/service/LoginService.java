package com.shoppingCart.login.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingCart.login.model.Email;
import com.shoppingCart.login.model.Login;
import com.shoppingCart.login.model.Products;
import com.shoppingCart.login.repository.ILoginRepository;

@RestController
public class LoginService implements ILoginSerive {

	@Autowired
	private ILoginRepository loginRepo;
	
	@GetMapping("/authenticate/{userName}/{password}")
	public Boolean authenticateUser(@PathVariable String userName,@PathVariable String password) {
		return loginRepo.authenticate(userName, password);
	}

	@PostMapping(value = "/create")
	public Login create(@Validated @RequestBody Login login) {
		
		loginRepo.createUser(login);
		return login;
	}

	@GetMapping("/products")
	public List<Products> getAllProducts() {
	
		return loginRepo.findAll();
	}

	@GetMapping("/products/{pCate}")
	public List <Products> getByCate(@PathVariable String pCate) {
	
		List <Products> product = loginRepo.getByCate(pCate);
		
		return product;
	}

	@Autowired
	JavaMailSender javaMailSender;
	
	@PostMapping("/sendEmail")
	public void sendEmail(@Validated @RequestBody Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("librarykarachi@gmail.com");
		message.setTo(email.getEmail());
		message.setSubject("Order placed successfully");
		message.setText("Thank you for being our cutomer. "
				+ "Your product tracking Id is *********,"
				+ " I hope we serve you well. Please come back soon");
	
		javaMailSender.send(message);
		System.out.println("Email send successfully");
	}
}
