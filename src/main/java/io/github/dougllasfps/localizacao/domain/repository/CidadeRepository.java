package io.github.dougllasfps.localizacao.domain.repository;

import io.github.dougllasfps.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    // busca pelo nome correto
    List<Cidade> findByNome(String nome);

    // busca o nome pelas letras iniciais
    List<Cidade> findByNomeStartingWith(String nome);

    // busca o nome pelas letras finais
    List<Cidade> findByNomeEndingWith(String nome);

    // busca pelo nome contendo aquele pedaço
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

}
