package com.ideas.finder.nodes.subGender;

import java.util.HashSet;
import java.util.Set;

import com.ideas.finder.nodes.gender.Gender;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node("subGender")
public class SubGender {
    
    @Id
    @GeneratedValue
	public Long id;

	@Property("displayName")
	public String displayName;

    @Property("description")
	public String description;

    // @Relationship(type = "SUBGENDER_OF", direction = Direction.OUTGOING)
	// private Set<Gender> genders = new HashSet<>();


    public SubGender(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public Long getId(){
        return this.id;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    // public Set<Gender> getGenders() {
	// 	return this.genders;
	// }
}
