package br.com.alura.screenmatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.alura.screenmatch.principal.Principal;

@SpringBootTest
class ScreenmatchApplicationTests {

	@MockBean
	private Principal principal;

	@Test
	void contextLoads() {
	}

}
