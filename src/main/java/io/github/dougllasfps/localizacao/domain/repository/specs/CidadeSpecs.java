package io.github.dougllasfps.localizacao.domain.repository.specs;

import io.github.dougllasfps.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public class CidadeSpecs {

    public static Specification<Cidade> nomeEqual(String nome){
        return (root, query, cb) -> cb.equal( root.get("nome") , nome );
    }

    public static Specification<Cidade> habitantesGreaterThan(String value){
        return (root, query, cb) -> cb.greaterThan( root.get("habitantes") , value );
    }
}
