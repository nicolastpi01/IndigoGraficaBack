package com.example.demo;

import com.example.dto.request.LoginRequest;
import com.example.dto.request.SignupRequest;
import com.example.model.*;
import com.example.repository.PedidoDBRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.example.services.PedidoStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

	private Tipo tipo = new Tipo(1, "Fotografia", 30, 30, "Arial");;
	private Pedido pedido = new Pedido("Pedido test", "Pedido sub", "Arial", 30, 30, "Desc", 1, "Solicitado", tipo);
	private String accessToken;

	@Transactional
	@Test
	void nuevoPedido() throws Exception{

		// Reemplazarlo por un save directo al repository y hacerlo en un @BeforeAll / @BeforeEach
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setEmail("a@a");
		signupRequest.setUsername("user");
		signupRequest.setPassword("password");

		MockHttpServletResponse signup = mockMvc.perform(MockMvcRequestBuilders.post("/noRequireAuth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(signupRequest)))
				.andReturn().getResponse();

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("user");
		loginRequest.setPassword("password");
		//

		// Esto debería ir en un @beforeAll / @beforeEach
		// y guardar el token en la variable de instancia del test
		MockHttpServletResponse login = mockMvc.perform(MockMvcRequestBuilders.post("/noRequireAuth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtil.toJson(loginRequest)))
				.andReturn().getResponse();

		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> map = mapper.readValue(login.getContentAsString(), Map.class);

		String token = map.get("accessToken");
		String userName = jwtUtils.getUserNameFromJwtToken(token);
		//

		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/create")
				.contentType(MediaType.APPLICATION_JSON)
				.header("authorization",": " + token)
				.content(JsonUtil.toJson(pedido)))
				.andReturn().getResponse();
		assertEquals(200, response.getStatus());
		// Hacer búsqueda por alguno de los datos ingresados en el pedido que se generó
		Pedido pedido = pedidoDBRepository.findAll().get(0);
		Assertions.assertTrue(response.getContentAsString().contains(pedido.getId()));
	}

	@Test
	void dummyTest() {

	}

}
