package com.ideas.finder.nodes.gender;

import java.util.HashSet;
import java.util.Set;

import com.ideas.finder.nodes.subGender.SubGender;

import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * Vertebrate
 * Invertebrate
 */

@Node("Gender")
public class Gender {
	@Id
    @GeneratedValue
	private Long id;

	@Property("displayName")
	private String displayName;

	@Property("description")
	private String description;

	/**
	 * Reptile, Mamals ...
	 */
	@Relationship(type = "INCLUDES", direction = Direction.OUTGOING)
	private Set<SubGender> subGender = new HashSet<>();

	// @Relationship(type = "DIRECTED", direction = INCOMING)
	// private Set<PersonEntity> directors = new HashSet<>();

	public Gender(String displayName, String description) {
		this.displayName = displayName;
		this.description = description;
	}

	public Long getId(){
		return this.id;
	}

	public Set<SubGender> getSubGenders() {
		return this.subGender;
	}

	public void addSubGender(SubGender subGender) {
		this.subGender.add(subGender);
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