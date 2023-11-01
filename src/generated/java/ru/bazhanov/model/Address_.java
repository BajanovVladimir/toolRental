package ru.bazhanov.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> district;
	public static volatile SingularAttribute<Address, Integer> id;

	public static final String STREET = "street";
	public static final String DISTRICT = "district";
	public static final String ID = "id";

}

