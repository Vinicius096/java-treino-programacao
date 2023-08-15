package br.com.coruja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.coruja.domain.model.AlunoModel;

public interface AlunoRepository extends JpaRepository<AlunoModel, Integer>{


}
