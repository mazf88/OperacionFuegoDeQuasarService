package co.com.meli.fuegoquasar.dto;

/**
 * Clase DTO que representa la posicion de cada satelite.
 * 
 * @version 1.0
 * @since 16/10/2020
 * @author Miguel Angel Zapata
 */
public class PositionDto {

	private float x;
	private float y;

	/**
	 * 
	 */
	public PositionDto() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public PositionDto(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
}