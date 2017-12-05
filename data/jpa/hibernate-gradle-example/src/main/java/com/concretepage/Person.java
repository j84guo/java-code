package com.concretepage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// JPA entity to persist
// see how JPA specification defines the annotations that ORM implentations must use
// Hibernate uses the annotation information to persist objects correctly
@Entity
@Table(name="person")
public class Person {

	@Id
	@Column(name="id")
    private int id;

	@Column(name="name")
    private String name;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
