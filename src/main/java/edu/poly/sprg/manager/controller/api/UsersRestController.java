package edu.poly.sprg.manager.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.sprg.manager.entity.Users;
import edu.poly.sprg.manager.model.UsersDTO;
import edu.poly.sprg.manager.service.UsersService;
import edu.poly.sprg.manager.validation.UsersValidation;

@RestController
@RequestMapping("/api/v4")
public class UsersRestController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private UsersValidation usersValidation;
	
	@Autowired
	private MessageSource messageSource;

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/list-user", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UsersDTO>> listUsers(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {

		PageRequest pageRequest = new PageRequest(pageNumber, 4);
		Page<Users> users = usersService.findAll(pageRequest);

		try {
			List<UsersDTO> usersDTOs = new ArrayList<>();
			ResponseEntity<List<UsersDTO>> responseEntity = new ResponseEntity<>(usersDTOs, HttpStatus.OK);

			for (Users user : users.getContent()) {
				UsersDTO dto = new UsersDTO();
				dto.setFullName(user.getFullName());
				dto.setUsername(user.getUsername());
				dto.setPassword(user.getPassword());

				usersDTOs.add(dto);
			}
			model.addAttribute("data", users);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-user", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<UsersDTO> getUser(Model model, @RequestParam String username) {
		Users user = usersService.getOne(username);
		try {
			UsersDTO dto = new UsersDTO();
			ResponseEntity<UsersDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);

			dto.setFullName(user.getFullName());
			dto.setUsername(user.getUsername());
			dto.setPassword(user.getPassword());

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/add-user", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UsersDTO>> addUser(@RequestParam(value = "fullName", required = false) String fullName,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		try {

			Users user = new Users();
			user.setFullName(fullName);
			user.setPassword(password);
			user.setUsername(username);

			usersService.save(user);

			List<UsersDTO> usersDTOs = returnDefaultListUserDTOs();
			ResponseEntity<List<UsersDTO>> responseEntity = new ResponseEntity<>(usersDTOs, HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("deprecation")
	public List<UsersDTO> returnDefaultListUserDTOs() {
		PageRequest pageRequest = new PageRequest(0, 4);
		Page<Users> users = usersService.findAll(pageRequest);

		List<UsersDTO> usersDTOs = new ArrayList<>();
		for (Users user : users.getContent()) {
			UsersDTO dto = new UsersDTO();
			dto.setFullName(user.getFullName());
			dto.setUsername(user.getUsername());
			dto.setPassword(user.getPassword());

			usersDTOs.add(dto);
		}

		return usersDTOs;
	}

	@GetMapping(value = "/edit-user", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UsersDTO>> editUser(@RequestParam("fullName") String fullName,
			@RequestParam String username, @RequestParam String password) {

		try {
			Users user = usersService.getOne(username);
			user.setFullName(fullName);
			user.setPassword(password);
			usersService.save(user);

			List<UsersDTO> usersDTOs = returnDefaultListUserDTOs();
			ResponseEntity<List<UsersDTO>> responseEntity = new ResponseEntity<>(usersDTOs, HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/delete-user", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UsersDTO>> removeUser(@RequestParam("username") String username) {
		try {
			usersService.deleteById(username);

			List<UsersDTO> usersDTOs = returnDefaultListUserDTOs();
			ResponseEntity<List<UsersDTO>> responseEntity = new ResponseEntity<>(usersDTOs, HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getLang", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> getLang(HttpServletRequest request) {
		try {
			// 1 is vietnamese
			int value = 1;
			ResponseEntity<Integer> responseEntity = new ResponseEntity<>(value, HttpStatus.OK);

			Locale locale = (Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
			if (locale == null || locale == Locale.ENGLISH) {
				locale = Locale.ENGLISH;
				value = 0;
				// 0 is english
			}

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/validation")
	public ResponseEntity<Map<Object, Object>> getResultViaAjax(
			@Validated(UsersValidation.class) @RequestBody UsersDTO user, BindingResult errors,
			HttpServletRequest request) {

		usersValidation.validate(user, errors);

		Map<Object, Object> errorMap = new HashMap<>();

		// If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {
			Locale locale =  LocaleContextHolder.getLocale();
//			List<FieldError> errs = errors.getFieldErrors();
////
//			for (FieldError fieldError : errs) {
//				errorMap.put(fieldError.getField(), messageSource.getMessage(fieldError, locale));
//			}

			errorMap = errors.getFieldErrors().stream().collect(Collectors.toMap(e -> e.getField(), e -> messageSource.getMessage(e, locale)));

			return ResponseEntity.badRequest().body(errorMap);
		}

		return ResponseEntity.ok(errorMap);
	}
}
