package ru.bazhanov.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, String> phoneNumber;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile ListAttribute<Person, Order> orders;
	public static volatile SingularAttribute<Person, Integer> id;
	public static volatile SingularAttribute<Person, User> user;
	public static volatile ListAttribute<Person, Tool> tools;

	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String NAME = "name";
	public static final String ORDERS = "orders";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String TOOLS = "tools";

}

