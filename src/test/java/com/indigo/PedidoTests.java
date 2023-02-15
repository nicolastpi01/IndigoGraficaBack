package com.indigo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indigo.dto.request.LoginRequest;
import com.indigo.dto.request.SignupRequest;
import com.indigo.model.*;
import com.indigo.model.Estado.Estado;
import com.indigo.model.Estado.PendienteAtencion;
import com.indigo.repository.PedidoDBRepository;
import com.indigo.repository.UserRepository;
import com.indigo.security.jwt.JwtUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes = IndigoApplication.class)
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
	private Estado pendienteAtencion = new PendienteAtencion();
	private Optional<User> user;
	private Pedido pedido = new Pedido("Pedido test", "Pedido sub", "Arial", 30, 30, "Desc", 1, pendienteAtencion, tipo);
	private String accessToken;


	@BeforeEach
	public void setupUser() throws Exception {
		//test pull request
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
		//Assertions.assertTrue(response.getContentAsString().contains(p.getId() ));
		Assertions.assertTrue(user.get().getUsername().equals(p.getPropietario().getUsername()));
	}
 	
	@Test
	void dummyTest() {

	}

}
