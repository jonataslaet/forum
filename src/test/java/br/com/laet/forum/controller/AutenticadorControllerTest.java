package br.com.laet.forum.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AutenticadorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testErroLoginComDadosErrados() throws Exception {
		//EMAIL CORRETO: aluno@email.com
		//SENHA CORRETA: 123456
		URI uri = new URI("/auth");
		String json = "{\"email\":\"alun@email.com\",\"senha\":\"123456\"}";
		System.out.println("Dados: "+ json);
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(MockMvcResultMatchers.status().is(400));
	}

}
