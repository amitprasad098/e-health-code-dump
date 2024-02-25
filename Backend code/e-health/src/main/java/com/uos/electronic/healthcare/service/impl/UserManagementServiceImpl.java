package com.uos.electronic.healthcare.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uos.electronic.healthcare.constant.Constants;
import com.uos.electronic.healthcare.entity.EmailDetails;
import com.uos.electronic.healthcare.entity.User;
import com.uos.electronic.healthcare.entity.UserType;
import com.uos.electronic.healthcare.model.UserRegistrationBean;
import com.uos.electronic.healthcare.model.UserSignInBean;
import com.uos.electronic.healthcare.repository.PracticeRepository;
import com.uos.electronic.healthcare.repository.UserRepository;
import com.uos.electronic.healthcare.repository.UserTypeRepository;
import com.uos.electronic.healthcare.service.EmailService;
import com.uos.electronic.healthcare.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private PracticeRepository practiceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public List<UserType> fetchUserTypes() {
		return userTypeRepository.findAll().stream().filter(userType -> !userType.getUserRole().equals("Admin"))
				.collect(Collectors.toList());
	}

	@Override
	public void registerUser(UserRegistrationBean userRegistrationBean) {
		User user = new User();
		user.setUserName(userRegistrationBean.getUserName());
		user.setUserEmail(userRegistrationBean.getUserEmail());
		user.setUserPassword(Base64.getEncoder().encodeToString(userRegistrationBean.getUserPassword().getBytes()));
		user.setUserType(userTypeRepository.findByUserRole(Constants.PATIENT_USER_ROLE).orElse(null));
		userRepository.save(user);

	}

	@Override
	public boolean signInUser(UserSignInBean userSignInBean) {
		List<User> users = userRepository.findAll();
		return users.stream()
				.anyMatch(user -> user.getUserEmail().equals(userSignInBean.getUserEmail())
						&& new String(Base64.getDecoder().decode(user.getUserPassword()))
								.equals(userSignInBean.getUserPassword())
						&& user.getUserType().getUserRole().equals(userSignInBean.getUserRole()));
	}

	@Override
	public void forgotPasswordManagement(String userEmail) throws Exception {
		EmailDetails emailDetails = new EmailDetails();
		Random randomGenerator = new Random();
		Optional<User> userOptional = userRepository.findByUserEmail(userEmail);
		
		if (userOptional.isPresent()) {
			emailDetails.setRecipient(userEmail);
			emailDetails.setSubject("OTP to Reset Password");
			String numbers = "0123456789";
		    char[] otp = new char[6];  
		    for (int i = 0; i < 6; i++) {  
		        otp[i] = numbers.charAt(randomGenerator.nextInt(numbers.length()));  
		    }
		    emailDetails.setMsgBody(new String(otp));
			emailService.sendSimpleMail(emailDetails);
		}
		else {
			throw new Exception("Provided email does not exist");
		}
	}

}
