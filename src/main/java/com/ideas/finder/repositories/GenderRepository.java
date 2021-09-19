package com.ideas.finder.repositories;

import com.ideas.finder.nodes.gender.Gender;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

import reactor.core.publisher.Flux;

public interface GenderRepository extends ReactiveNeo4jRepository<Gender, Long>  {
    //Mono<Gender> findOneByDisplayName(String displayName);
    Flux<Gender> findAllByDisplayName(String displayName);
}
