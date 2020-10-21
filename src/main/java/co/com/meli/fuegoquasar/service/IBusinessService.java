package co.com.meli.fuegoquasar.service;

import co.com.meli.fuegoquasar.dto.RequestDto;
import co.com.meli.fuegoquasar.dto.RequestSplitDto;
import co.com.meli.fuegoquasar.dto.ResponseDto;

/**
 * Interfaz de lógica de negocio del microservicio contiene método que
 * estructura la respuesta de negocio consumiendo clientes rest
 *
 * @author Miguel Angel Zapata
 * @versión 1.0
 */
public interface IBusinessService {

	/**
	 * Método para el manejo del negocio del microservicio
	 *
	 * @author Miguel Angel Zapata
	 * @param RequestDto {@link requestDto}
	 * @return {@link ResponseDto responseDto}
	 * @throws CallLegacyException     {@link CallLegacyException}
	 * @throws FallbackMethodException {@link FallbackMethodException}
	 *
	 */
	public ResponseDto getLocationMessage(RequestDto requestDto) throws Exception;
	
	/**
	 * Método para el manejo del negocio del microservicio.
	 *
	 * @author Miguel Angel Zapata
	 * @param RequestSplitDto {@link requestSplitDto}
	 * @return {@link ResponseDto responseDto}
	 * @throws CallLegacyException     {@link CallLegacyException}
	 * @throws FallbackMethodException {@link FallbackMethodException}
	 *
	 */
	public ResponseDto postSplitLocationMessage(RequestSplitDto requestSplitDto, String shipName) throws Exception;
	
	/**
	 * Método para el manejo del negocio del microservicio.
	 *
	 * @author Miguel Angel Zapata
	 * @param RequestSplitDto {@link requestSplitDto}
	 * @return {@link ResponseDto responseDto}
	 * @throws CallLegacyException     {@link CallLegacyException}
	 * @throws FallbackMethodException {@link FallbackMethodException}
	 *
	 */
	public ResponseDto getSplitLocationMessage() throws Exception;
}
