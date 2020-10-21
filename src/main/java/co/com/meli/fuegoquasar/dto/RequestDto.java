package co.com.meli.fuegoquasar.dto;

import java.util.List;

/**
 * Clase DTO usada para poder mapear el request del servicio.
 * 
 * @version 1.0
 * @since 16/10/2020
 * @author Miguel Angel Zapata
 */
public class RequestDto {

	private List<SatelliteDto> satellites;

	/**
	 * 
	 */
	public RequestDto() {
		super();
	}

	/**
	 * @param satellites
	 */
	public RequestDto(List<SatelliteDto> satellites) {
		super();
		this.satellites = satellites;
	}

	/**
	 * @return the satellites
	 */
	public List<SatelliteDto> getSatellites() {
		return satellites;
	}

	/**
	 * @param listSatellites the listSatellites to set
	 */
	public void setSatellites(List<SatelliteDto> satellites) {
		this.satellites = satellites;
	}
}
