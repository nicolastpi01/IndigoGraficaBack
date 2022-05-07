package com.example.demo;

import com.example.PedidoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PedidoTests {

	@Autowired
	PedidoController pedidoController;

	@Test
	void dummyTest() {
		assertTrue(pedidoController.dummyMethod());
	}

}
