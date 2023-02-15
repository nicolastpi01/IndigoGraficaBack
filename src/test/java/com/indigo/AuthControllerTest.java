package com.indigo;

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

import com.indigo.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@ContextConfiguration(classes = IndigoApplication.class)
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    private User user1 = new User("diego", "a@a","Diego", "Apellido", "Ubicacion", "Contacto", "password1");
    private User user2 = new User("diego", "a@a ","Diego", "Apellido", "Ubicacion", "Contacto", "password2");

    @Transactional
    @Test
    public void registrarUsuario() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/noRequireAuth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(user1)))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Transactional
    @Test
    public void registrarUsuarioConUserNameRepetido() throws Exception {
        MockHttpServletResponse response1 = mockMvc.perform(MockMvcRequestBuilders.post("/noRequireAuth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(user1)))
                .andReturn().getResponse();

        MockHttpServletResponse response2 = mockMvc.perform(MockMvcRequestBuilders.post("/noRequireAuth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(user2)))
                .andReturn().getResponse();

        assertEquals(400, response2.getStatus());
    }

}
