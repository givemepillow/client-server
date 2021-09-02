package dev.kirilllapushinskiy.core;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The type Car.
 */
public class Car {
	private  String name;
	private  boolean cool;

    /**
     * Instantiates a new Car.
     *
     * @param name the name
     * @param cool the cool
     */
    public Car(String name, boolean cool) {
		this.name = name;
		this.cool = cool;
	}

    /**
     * Instantiates a new Car.
     */
	@JsonCreator
    public Car() {
	}

	@Override
	public String toString() {
		return name + ", крутая - " + cool;
	}
}
