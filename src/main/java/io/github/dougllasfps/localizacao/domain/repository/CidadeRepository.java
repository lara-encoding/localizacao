package io.github.dougllasfps.localizacao.domain.repository;

import io.github.dougllasfps.localizacao.domain.entity.Cidade;
import io.github.dougllasfps.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome =:nome ")
    List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);

    // busca pelo nome correto
    List<Cidade> findByNome(String nome);

    // busca pelo nome Like ordenado
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    // busca pelo nome Like paginado
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);

    // busca o nome pelas letras iniciais
    List<Cidade> findByNomeStartingWith(String nome);

    // busca o nome pelas letras finais
    List<Cidade> findByNomeEndingWith(String nome);

    // busca pelo nome contendo aquele pedaço
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

    // menor que
    List<Cidade> findByHabitantesLessThan(Long habitantes);

    // maior que
    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    // menor ou igual a
    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
}
