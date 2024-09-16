package com.sunnylow.notion_clone.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sunnylow.notion_clone.dto.AuthDTO;
import com.sunnylow.notion_clone.dto.IntrospectDTO;
import com.sunnylow.notion_clone.dto.UserDTO;
import com.sunnylow.notion_clone.exception.*;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.model.UserRole;
import com.sunnylow.notion_clone.model.Workspace;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.repository.WorkspaceRepository;
import com.sunnylow.notion_clone.service.AuthService;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WorkspaceRepository workspaceRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@NonFinal
	@Value("${jwt.signerKey}")
	protected String SIGNER_KEY;

	@Override
	public AuthDTO login(UserDTO dto) {
		PasswordEncoder encoder = new BCryptPasswordEncoder(10);

		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with email: " + dto.getEmail(),
						ErrorCode.USER_NOT_FOUND
				));

		boolean authenticated = encoder.matches(dto.getPassword(), user.getPassword());

		if (!authenticated) {
			throw new AppException(
					"Incorrect password",
					ErrorCode.UNAUTHENTICATED
			);
		}

		var token = generateToken(user);

		return AuthDTO.builder()
				.token(token)
				.authenticated(true)
				.build();
	}

	@Override
	public UserDTO signup(UserDTO dto) {
		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new EntityAlreadyExistsException(
					"Email already exists.",
					ErrorCode.USER_ALREADY_EXISTS
			);
		}

		User user = new User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRole(UserRole.USER);

		userRepository.save(user);
		createSharedWorkspace(user);

		return UserDTO.toUserDTO(user);
	}

	private void createSharedWorkspace(User user) {
		Workspace workspace = new Workspace();
		workspace.setName("Shared Workspace");
		workspace.setUser(user);
		workspace.setCreatedAt(LocalDate.now());
		workspace.setUpdatedAt(LocalDate.now());
		workspace.setEditable(false);

		workspaceRepository.save(workspace);
	}

	@Override
	public IntrospectDTO introspect(IntrospectDTO dto) throws JOSEException, ParseException {
		var token = dto.getToken();

		JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
		SignedJWT signedJWT = SignedJWT.parse(token);
		Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

		var verified = signedJWT.verify(verifier);

		return IntrospectDTO.builder()
				.token(dto.getToken())
				.valid(verified && expiration.after(new Date()))
				.build();
	}

	private String generateToken(User user) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
				.subject(user.getEmail())
				.issuer("sunnylow")
				.issueTime(new Date())
				.expirationTime(new Date(
						Instant.now().plus(6, ChronoUnit.HOURS).toEpochMilli()
				))
				.claim("id", user.getId())
				.claim("scope", user.getRole().name())
				.build();

		Payload payload = new Payload(jwtClaimsSet.toJSONObject());

		JWSObject jwsObject = new JWSObject(header, payload);

		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			log.error("Cannot create token ", e);
			throw new RuntimeException(e);
		}
	}
}
