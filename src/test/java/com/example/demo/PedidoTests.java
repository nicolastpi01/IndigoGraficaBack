package com.example.demo;

import com.example.model.*;
import com.example.repository.PedidoDBRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.services.PedidoStorageService;
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

	private Tipo tipo = new Tipo(1, "Fotografia", 30, 30, "Arial");;
	private Pedido pedido = new Pedido("Pedido test", "Pedido sub", "Arial", 30, 30, "Desc", 1, "Solicitado", tipo);

	@Transactional
	@Test
	void nuevoPedido() throws Exception{

		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/create")
				.contentType(MediaType.APPLICATION_JSON)
				.header("authorization","")
				.content(JsonUtil.toJson(pedido)))
				.andReturn().getResponse();
		assertEquals(200, response.getStatus());
		Pedido pedido = pedidoDBRepository.findAll().get(0);
		Assertions.assertTrue(response.getContentAsString().contains(pedido.getId()));
	}

	@Test
	void dummyTest() {

	}

}
