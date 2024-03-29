package br.com.laet.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.laet.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
