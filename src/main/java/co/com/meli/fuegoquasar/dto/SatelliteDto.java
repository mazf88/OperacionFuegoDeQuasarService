package co.com.meli.fuegoquasar.dto;

import java.util.List;

/**
 * Clase DTO que representa cada satelite.
 * 
 * @version 1.0
 * @since 16/10/2020
 * @author Miguel Angel Zapata
 */
public class SatelliteDto {

	private String name;
	private float distance;
	private List<String> message;

	/**
	 * 
	 */
	public SatelliteDto() {
		super();
	}

	/**
	 * @param name
	 * @param distance
	 * @param message
	 */
	public SatelliteDto(String name, float distance, List<String> message) {
		super();
		this.name = name;
		this.distance = distance;
		this.message = message;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the distance
	 */
	public float getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}

	/**
	 * @return the message
	 */
	public List<String> getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(List<String> message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Satellite [name=" + name + ", distance=" + distance + ", message=" + message + "]";
	}
}