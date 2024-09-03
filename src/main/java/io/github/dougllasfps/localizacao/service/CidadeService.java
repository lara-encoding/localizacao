package io.github.dougllasfps.localizacao.service;

import io.github.dougllasfps.localizacao.domain.entity.Cidade;
import io.github.dougllasfps.localizacao.domain.repository.CidadeRepository;
import io.github.dougllasfps.localizacao.domain.repository.specs.CidadeSpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.dougllasfps.localizacao.domain.repository.specs.CidadeSpecs.*;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository){
        this.repository = repository;
    }

    @Transactional
    void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 12396372L);
        repository.save(cidade);
    }

    public void listarCidadesPorQuantidadeHabitantes(){
        repository
                .findByHabitantesLessThanAndNomeLike(1000001L, "Br%")
                .forEach(System.out::println);
    }

    public void listarCidadesPorNome(){
        Pageable pageable = PageRequest.of(3, 4);
        repository
                .findByNomeLike("%%%%", pageable)
                .forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes(){
        repository.findByHabitantes(78787900L).forEach(System.out::println);
    }

    void listarCidades(){
        repository.findAll().forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }

    public void listarCidadesByNomeSpec(){
        repository
                .findAll(nomeEqual("São Paulo").and(idEqual(1L)))
                .forEach(System.out::println);
    }
}
