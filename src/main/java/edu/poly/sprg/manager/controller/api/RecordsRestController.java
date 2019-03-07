package edu.poly.sprg.manager.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.sprg.manager.entity.Records;
import edu.poly.sprg.manager.entity.Staffs;
import edu.poly.sprg.manager.model.RecordsDTO;
import edu.poly.sprg.manager.model.StaffsDTO;
import edu.poly.sprg.manager.service.MailService;
import edu.poly.sprg.manager.service.RecordsService;
import edu.poly.sprg.manager.service.StaffsService;

@RestController
@RequestMapping("/api/v3")
public class RecordsRestController {

	@Autowired
	private RecordsService recordsService;

	@Autowired
	private StaffsService staffsService;

	@Autowired
	private MailService mailService;

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/list-record", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<RecordsDTO>> listRecord(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {

		PageRequest pageRequest = new PageRequest(pageNumber, 4);
		Page<Records> records = recordsService.findAll(pageRequest);

		try {
			List<RecordsDTO> recordsDTOs = new ArrayList<>();
			ResponseEntity<List<RecordsDTO>> responseEntity = new ResponseEntity<>(recordsDTOs, HttpStatus.OK);

			for (Records record : records.getContent()) {
				RecordsDTO dto = new RecordsDTO();
				dto.setId(record.getId());
				dto.setDate(record.getDate());
				dto.setReason(record.getReason());
				dto.setStaffs(new StaffsDTO(record.getStaffs().getId(), record.getStaffs().getName(),
						record.getStaffs().isGender(), record.getStaffs().getBirthDay(), record.getStaffs().getPhoto(),
						record.getStaffs().getPhone(), record.getStaffs().getPhone(), record.getStaffs().getSalary(),
						record.getStaffs().getLevel()));
				dto.setType(record.isType());

				recordsDTOs.add(dto);
			}
			model.addAttribute("data", records);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<List<RecordsDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/add-record", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> addRecord(@RequestParam Integer staffId, @RequestParam Boolean type,
			@RequestParam String reason) {
		try {
			String result = "";
			ResponseEntity<String> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
			Records record = new Records();
			record.setDate(new Date());
			record.setReason(reason);
			record.setStaffs(new Staffs(staffId));
			record.setType(type);

			recordsService.save(record);

			// send mail
			mailService.sendEmail(type, staffsService.getOne(staffId), reason);

			result = "OK";
			
			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-record", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecordsDTO> getRecord(Model model, @RequestParam("id") int id) {
		try {
			Records records = recordsService.getOne(id);
			RecordsDTO dto = new RecordsDTO();

			ResponseEntity<RecordsDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);

			dto.setId(records.getId());
			dto.setDate(records.getDate());
			dto.setReason(records.getReason());
			dto.setStaffs(new StaffsDTO(records.getStaffs().getId(), records.getStaffs().getName(),
					records.getStaffs().isGender(), records.getStaffs().getBirthDay(), records.getStaffs().getPhoto(),
					records.getStaffs().getPhone(), records.getStaffs().getPhone(), records.getStaffs().getSalary(),
					records.getStaffs().getLevel()));
			dto.setType(records.isType());

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<RecordsDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getLang", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> getLang(HttpServletRequest request) {
		try {
			// 1 is vietnamese
			int value = 1;

			Locale locale = (Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
			if (locale == null || locale == Locale.ENGLISH) {
				locale = Locale.ENGLISH;
				value = 0;
				// 0 is english
			}
			ResponseEntity<Integer> responseEntity = new ResponseEntity<>(value, HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}

//	@PostMapping(value = "/validation")
//	public ResponseEntity<Map<Object, Object>> getResultViaAjax(
//			@Validated(RecordsValidation.class) @RequestBody RecordsDTO record, BindingResult errors,
//			HttpServletRequest request) {
//
//		recordsValidation.validate(record, errors);
//		
//		Map<Object, Object> errorMap = new HashMap<>();
//		if (errors.hasErrors()) {
//			Locale locale = LocaleContextHolder.getLocale();
//			errorMap = errors.getFieldErrors().stream()
//					.collect(Collectors.toMap(e -> e.getField(), e -> messageSource.getMessage(e, locale)));
//
//			return ResponseEntity.badRequest().body(errorMap);
//		}
//
//		return ResponseEntity.ok(errorMap);
//	}
}
