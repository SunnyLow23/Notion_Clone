package com.sunnylow.notion_clone.service;

import com.nimbusds.jose.JOSEException;
import com.sunnylow.notion_clone.dto.AuthDTO;
import com.sunnylow.notion_clone.dto.IntrospectDTO;
import com.sunnylow.notion_clone.dto.UserDTO;

import java.text.ParseException;

public interface AuthService {

	AuthDTO login(UserDTO dto);

	UserDTO signup(UserDTO dto);

	IntrospectDTO introspect(IntrospectDTO dto) throws JOSEException, ParseException;
}
