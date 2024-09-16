package com.sunnylow.notion_clone.controller;

import com.nimbusds.jose.JOSEException;
import com.sunnylow.notion_clone.controller.api.AuthAPI;
import com.sunnylow.notion_clone.dto.AuthDTO;
import com.sunnylow.notion_clone.dto.IntrospectDTO;
import com.sunnylow.notion_clone.dto.UserDTO;
import com.sunnylow.notion_clone.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class AuthController implements AuthAPI {

	@Autowired
	private AuthService authService;

	@Override
	public ResponseEntity<AuthDTO> login(UserDTO dto) {
		return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> signup(UserDTO dto) {
		return new ResponseEntity<>(authService.signup(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<IntrospectDTO> introspect(IntrospectDTO dto) throws ParseException, JOSEException {
		return new ResponseEntity<>(authService.introspect(dto), HttpStatus.OK);
	}
}
