package ru.bazhanov.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Boolean> stopped;
	public static volatile SingularAttribute<Order, Person> person;
	public static volatile SingularAttribute<Order, Integer> id;
	public static volatile SingularAttribute<Order, Boolean> completed;
	public static volatile SingularAttribute<Order, Tool> tool;
	public static volatile SingularAttribute<Order, LocalDate> startDate;
	public static volatile SingularAttribute<Order, LocalDate> stopDate;

	public static final String STOPPED = "stopped";
	public static final String PERSON = "person";
	public static final String ID = "id";
	public static final String COMPLETED = "completed";
	public static final String TOOL = "tool";
	public static final String START_DATE = "startDate";
	public static final String STOP_DATE = "stopDate";

}

