package com.ideas.finder.repositories;

import com.ideas.finder.nodes.subGender.SubGender;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface SubGenderRepository extends ReactiveNeo4jRepository<SubGender, Long>  {
}
