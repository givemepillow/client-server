package common;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

/**
 * The type Car.
 */
public class Car implements Serializable {
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
