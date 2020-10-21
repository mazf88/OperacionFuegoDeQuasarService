package co.com.meli.fuegoquasar.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Satellite {

	@Id
	@Column
	private int id;
	private String name;
	private float distance;
	@ElementCollection()
	private List<String> message = new ArrayList<String>();

	/**
	 * 
	 */
	public Satellite() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param distance
	 * @param message
	 */
	public Satellite(int id, String name, float distance, List<String> message) {
		super();
		this.id = id;
		this.name = name;
		this.distance = distance;
		this.message = message;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
}