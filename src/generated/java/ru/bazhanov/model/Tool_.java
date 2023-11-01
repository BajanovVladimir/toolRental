package ru.bazhanov.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tool.class)
public abstract class Tool_ {

	public static volatile SingularAttribute<Tool, Address> address;
	public static volatile SingularAttribute<Tool, Person> person;
	public static volatile SingularAttribute<Tool, Double> price;
	public static volatile SingularAttribute<Tool, String> name;
	public static volatile ListAttribute<Tool, Order> orders;
	public static volatile SingularAttribute<Tool, Integer> id;
	public static volatile SingularAttribute<Tool, String> specifications;

	public static final String ADDRESS = "address";
	public static final String PERSON = "person";
	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String ORDERS = "orders";
	public static final String ID = "id";
	public static final String SPECIFICATIONS = "specifications";

}

