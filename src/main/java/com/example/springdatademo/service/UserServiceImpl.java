package com.example.springdatademo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.AuditLog;
import com.example.springdatademo.entity.User;
import com.example.springdatademo.enums.AuditAction;
import com.example.springdatademo.enums.UserRole;
import com.example.springdatademo.exception.UserNotFoundException;
import com.example.springdatademo.repository.AuditRepository;
import com.example.springdatademo.repository.UserRepository;

/**
 * @author bhagyashree.naray
 *
 */
@Component
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuditRepository auditRepository;
	
	private String getRequestHeader() {
		 return  request.getHeader("logged_in_user");
		}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	
	@Override
	public User addUser(User demoUser) throws NumberFormatException, UserNotFoundException {
		String loggedInUserRole = getUserRole(Integer.parseInt(getRequestHeader()));
		logger.info("#####START:UserServiceImpl.AddUser()#####");
		User user = userRepository.save(demoUser);
		
		checkUserRoleAndAudit(loggedInUserRole, Integer.parseInt(getRequestHeader()), AuditAction.POST.getActionName(), user.getUserId());
		
		logger.info("#####END:UserServiceImpl.AddUser()#####");
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) throws UserNotFoundException {
		
		logger.info("#####START:UserServiceImpl.deleteUser()#####");
		String loggedInUserRole = getUserRole(Integer.parseInt(getRequestHeader()));
		Optional<User> demoUser = userRepository.findById(id);
		if (!demoUser.isPresent()) {
			logger.error("No User with this Id exists ");
			throw new UserNotFoundException("No User with this Id exists ");
		}
		userRepository.delete(demoUser.get());
		
		checkUserRoleAndAudit(loggedInUserRole, Integer.parseInt(getRequestHeader()), AuditAction.DELETE.getActionName(), demoUser.get().getUserId());
		logger.info("#####END:UserServiceImpl.deleteUser()#####");
	}

	@Override
	public User updateUser(User demoUser) throws NumberFormatException, UserNotFoundException {
		String loggedInUserRole = getUserRole(Integer.parseInt(getRequestHeader()));
		logger.info("#####START:UserServiceImpl.updateUser()#####");
		User user = userRepository.save(demoUser);
		
		checkUserRoleAndAudit(loggedInUserRole, Integer.parseInt(getRequestHeader()), AuditAction.UPDATE.getActionName(), user.getUserId());
		logger.info("#####END:UserServiceImpl.updateUser()#####");
		return user;

	}

	@Override
	public User getUserById(Integer id) throws UserNotFoundException {

		String loggedInUserRole = getUserRole(Integer.parseInt(getRequestHeader()));
		logger.info("#####START:UserServiceImpl.getUserById()#####");
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			logger.error("No User with this Id exists ");
			throw new UserNotFoundException("No User with this Id exists ");
		}

		checkUserRoleAndAudit(loggedInUserRole, Integer.parseInt(getRequestHeader()), AuditAction.GET.getActionName(), id);

		logger.info("#####END:UserServiceImpl.getUserById()#####");
		return optional.get();
	}

	public String getUserRole(Integer userId) throws UserNotFoundException {

		Optional<User> demoUser = userRepository.findById(userId);
		if (!demoUser.isPresent()) {
			logger.error("Logged in user id is invalid");
			throw new UserNotFoundException("Logged in user id is invalid");
		}

		return demoUser.get().getUserRole();

	}

	@Override
	public List<UserDto> groupUsersByExperience() {
		return userRepository.groupUsersByExperience();
	}

	@Override
	public List<User> getUsers(Integer pageNo, Integer pageSize, Integer yearsOfExperienceMin,
			Integer yearsOfExperienceMax, String sortBy) {
		Pageable page;
		Page<User> pagedResult;
		if (Strings.isBlank(sortBy))
			page = PageRequest.of(pageNo, pageSize);
		else
			page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		if (yearsOfExperienceMin != null && yearsOfExperienceMax != null) {

			pagedResult = userRepository.findByYearsOfExperienceGreaterThanEqualAndYearsOfExperienceLessThanEqual(
					yearsOfExperienceMin, yearsOfExperienceMax, page);

		} else {
			pagedResult = userRepository.findAll(page);
		}

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}

	}

	private void checkUserRoleAndAudit(String loggedInUserRole, Integer loggedInUserId, String actionName, Integer id) {
		if (loggedInUserRole.equalsIgnoreCase(UserRole.ADMIN.getUserRole())) {
			logger.info("Logged in user is an admin");
			AuditLog auditLog = new AuditLog(actionName, loggedInUserId, id, LocalDate.now());
			logger.info("Adding entry in AuditLog");
			auditRepository.save(auditLog);
		}else{
			logger.warn("Logged in user is not an admin");
		}
	}

}
