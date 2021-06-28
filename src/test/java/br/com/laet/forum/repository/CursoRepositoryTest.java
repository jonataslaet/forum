package br.com.laet.forum.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.laet.forum.modelo.Curso;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Test
	public void testEncontraNomeCursoNaoNuloPeloNome() {
		String nomeCurso = "HTML 5";
		Curso cursoCarregado = cursoRepository.findByNome(nomeCurso);
		assertNotNull(cursoCarregado);
		assertEquals(nomeCurso, cursoCarregado.getNome());
	}
	
	@Test
	public void testNaoEncontraNomeCursoNaoNuloPeloNome() {
		String nomeCurso = "HTML 53333";
		Curso cursoCarregado = cursoRepository.findByNome(nomeCurso);
		assertNull(cursoCarregado);
	}

}
