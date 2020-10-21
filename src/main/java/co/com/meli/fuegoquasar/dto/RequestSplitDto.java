package co.com.meli.fuegoquasar.dto;

import java.util.List;

public class RequestSplitDto {

	private float distance;
	private List<String> message;

	/**
	 * 
	 */
	public RequestSplitDto() {
		super();
	}

	/**
	 * @param distance
	 * @param message
	 */
	public RequestSplitDto(float distance, List<String> message) {
		super();
		this.distance = distance;
		this.message = message;
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
		return "RequestSplitDto [distance=" + distance + ", message=" + message + "]";
	}
}