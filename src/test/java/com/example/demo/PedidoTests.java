package com.example.demo;

import com.example.dto.request.LoginRequest;
import com.example.dto.request.SignupRequest;
import com.example.model.*;
import com.example.repository.PedidoDBRepository;
import com.example.repository.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PedidoTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PedidoDBRepository pedidoDBRepository;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserRepository userRepository;

	private Tipo tipo = new Tipo(1, "Fotografia", 30, 30, "Arial");
	private Optional<User> user;
	private Pedido pedido = new Pedido("Pedido test", "Pedido sub", "Arial", 30, 30, "Desc", 1, "Solicitado", tipo);
	private String accessToken;


	@BeforeEach
	void setupUser() throws Exception {
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setEmail("a@a.a");
		signupRequest.setUsername("user");
		signupRequest.setPassword("password");

		MockHttpServletResponse signup = mockMvc.perform(MockMvcRequestBuilders.post("/noRequireAuth/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtil.toJson(signupRequest)))
				.andReturn().getResponse();

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("user");
		loginRequest.setPassword("password");

		user = userRepository.findByUsername("user");

		MockHttpServletResponse login = mockMvc.perform(MockMvcRequestBuilders.post("/noRequireAuth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtil.toJson(loginRequest)))
				.andReturn().getResponse();

		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> map = mapper.readValue(login.getContentAsString(), Map.class);

		accessToken = map.get("accessToken");
		String userName = jwtUtils.getUserNameFromJwtToken(accessToken);
	}

	@Transactional
	@Test
	void nuevoPedido() throws Exception{

		pedido.setPropietario(user.get());
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/create")
				.contentType(MediaType.APPLICATION_JSON)
				.header("authorization",": " + accessToken)
				.content(JsonUtil.toJson(pedido)))
				.andReturn().getResponse();
		assertEquals(200, response.getStatus());
		// Hacer búsqueda por alguno de los datos ingresados en el pedido que se generó
		Pedido p = pedidoDBRepository.findAll().get(0);
		Assertions.assertTrue(response.getContentAsString().contains(p.getId()));
		Assertions.assertTrue(user.get().getUsername().equals(p.getPropietario().getUsername()));

	}

	@Test
	void dummyTest() {

	}

}
