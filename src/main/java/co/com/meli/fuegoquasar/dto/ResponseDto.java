package co.com.meli.fuegoquasar.dto;

import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiModel;

/**
 * Clase DTO usada para poder mapear la respuesta del servicio.
 * 
 * @version 1.0
 * @since 16/10/2020
 * @author Miguel Angel Zapata
 */
@ApiModel(value = "ResponseDto")
public class ResponseDto {

	private PositionDto position;
	private String message;

	public ResponseDto() {

	}

	/**
	 * @param reponseCode
	 * @param position
	 * @param message
	 */
	public ResponseDto(PositionDto position, String message) {
		super();
		this.position = position;
		this.message = message;
	}

	/**
	 * @return the position
	 */
	public PositionDto getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(PositionDto position) {
		this.position = position;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseDto [position=" + position.toString() + ", message=" + message + "]";
	}
}