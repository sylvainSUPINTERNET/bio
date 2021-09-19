package com.ideas.finder.nodes.animal;

import java.util.HashSet;
import java.util.Set;

import com.ideas.finder.nodes.subGender.SubGender;

import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Animal")
public class Animal {
	@Id
    @GeneratedValue
	private Long id;

	@Property("displayName")
	private String displayName;

	@Property("description")
	private String description;

	// @Relationship(type = "INCLUDES", direction = Direction.OUTGOING)
	// private Set<SubGender> lizards = new HashSet<>();

	// @Relationship(type = "DIRECTED", direction = INCOMING)
	// private Set<PersonEntity> directors = new HashSet<>();

	public Animal(String displayName, String description) {
		this.displayName = displayName;
		this.description = description;
	}

	public Long getId(){
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String dn) {
		this.description = dn;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String dn) {
		this.displayName = dn;
	}

}