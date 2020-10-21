package co.com.meli.fuegoquasar.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.com.meli.fuegoquasar.dto.RequestDto;
import co.com.meli.fuegoquasar.dto.RequestSplitDto;
import co.com.meli.fuegoquasar.dto.ResponseDto;
import co.com.meli.fuegoquasar.service.IBusinessService;

@RestController
@RequestMapping
public class AppController {

	@Autowired
	private IBusinessService iBusinessService;

	@PostMapping("/topsecret")
	@ApiOperation(value = "Operacion POST que retorna la ubicacion y mensaje de auxilio enviado "
			+ "por nave portacarga.", notes = "", response = ResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDto.class, message = "OK"),
			@ApiResponse(code = 400, response = ResponseDto.class, message = "Bad request"),
			@ApiResponse(code = 404, response = ResponseDto.class, message = "Not found"),
			@ApiResponse(code = 500, response = ResponseDto.class, message = "Internal server error"),
			@ApiResponse(code = 504, response = ResponseDto.class, message = "Gateway timeout") })
	public ResponseEntity<?> topSecret(@RequestBody RequestDto requestDto) throws Exception {

		ResponseEntity<ResponseDto> errorValidacion = validar(requestDto);
		if (errorValidacion != null)
			return errorValidacion;

		if (iBusinessService.getLocationMessage(requestDto).getPosition() == null)
			return ResponseEntity.badRequest().body(iBusinessService.getLocationMessage(requestDto));
		return ResponseEntity.ok(iBusinessService.getLocationMessage(requestDto));

	}

	@PostMapping("/topsecret_split")
	@ApiOperation(value = "Operacion POST que permite almacenar informacion para posterior generacion "
			+ "de ubicacion y mensaje de auxilio enviado por nave portacarga..", notes = "", response = ResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDto.class, message = "OK"),
			@ApiResponse(code = 400, response = ResponseDto.class, message = "Bad request"),
			@ApiResponse(code = 404, response = ResponseDto.class, message = "Not found"),
			@ApiResponse(code = 500, response = ResponseDto.class, message = "Internal server error"),
			@ApiResponse(code = 504, response = ResponseDto.class, message = "Gateway timeout") })
	public ResponseEntity<?> topSecretSplit(@RequestParam("satellite_name") String satelliteName,
			@RequestBody RequestSplitDto requestSplitDto) throws Exception {
		ResponseEntity<ResponseDto> errorValidacion = validar(requestSplitDto, satelliteName);
		if (errorValidacion != null)
			return errorValidacion;
		return ResponseEntity.ok(iBusinessService.postSplitLocationMessage(requestSplitDto, satelliteName));

	}

	@GetMapping("/topsecret_split")
	@ApiOperation(value = "Operacion GET que retorna la ubicacion y mensaje de auxilio enviado por nave portacarga, "
			+ "a partir de informacion recibida previamente de los tres satellites en metodo POST /topsecret_split.", notes = "", response = ResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ResponseDto.class, message = "OK"),
			@ApiResponse(code = 400, response = ResponseDto.class, message = "Bad request"),
			@ApiResponse(code = 404, response = ResponseDto.class, message = "Not found"),
			@ApiResponse(code = 500, response = ResponseDto.class, message = "Internal server error"),
			@ApiResponse(code = 504, response = ResponseDto.class, message = "Gateway timeout") })
	public ResponseEntity<?> getTopSecretSplit() throws Exception {
		if (iBusinessService.getSplitLocationMessage().getPosition() == null)
			return ResponseEntity.badRequest().body(iBusinessService.getSplitLocationMessage());
		return ResponseEntity.ok(iBusinessService.getSplitLocationMessage());

	}

	private ResponseEntity<ResponseDto> validar(RequestDto requestDto) {
		ResponseDto response = new ResponseDto();

		if (requestDto.getSatellites().size() != 3) {
			response.setMessage("Hace falta la informacion de los 3 satelites");
			return ResponseEntity.badRequest().body(response);
		}

		if (requestDto.getSatellites().get(0).getMessage().size() != requestDto.getSatellites().get(1).getMessage()
				.size()
				|| requestDto.getSatellites().get(0).getMessage().size() != requestDto.getSatellites().get(2)
						.getMessage().size()
				|| requestDto.getSatellites().get(1).getMessage().size() != requestDto.getSatellites().get(2)
						.getMessage().size()) {
			response.setMessage("Hace falta la informacion completa de uno de los 3 mensajes recibidos");
			return ResponseEntity.badRequest().body(response);
		}
		return null;
	}

	private ResponseEntity<ResponseDto> validar(RequestSplitDto requestSplitDto, String satelliteName) {
		ResponseDto response = new ResponseDto();

		if (requestSplitDto == null) {
			response.setMessage("Body vacio");
			return ResponseEntity.badRequest().body(response);
		}

		if (requestSplitDto.getMessage() == null) {
			response.setMessage("Hace falta la informacion del mensaje");
			return ResponseEntity.badRequest().body(response);
		}
		return null;
	}
}
