package co.com.meli.fuegoquasar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.meli.fuegoquasar.dto.PositionDto;
import co.com.meli.fuegoquasar.dto.RequestDto;
import co.com.meli.fuegoquasar.dto.RequestSplitDto;
import co.com.meli.fuegoquasar.dto.ResponseDto;
import co.com.meli.fuegoquasar.entities.Satellite;
import co.com.meli.fuegoquasar.repository.SatelliteRepository;

@Service
public class BusinessService implements IBusinessService {

	@Autowired
	private SatelliteRepository satelliteRepository;

	private final float kenobiLocationX = -500.0f;
	private final float kenobiLocationY = -200.0f;
	private final float skyWalkerLocationX = 100.0f;
	private final float skyWalkerLocationY = -100.0f;
	private final float satoLocationX = 500.0f;
	private final float satoLocationY = 100.0f;

	/**
	 * Metodo de negocio que hace llamados a getLocation y getMessage para retornar
	 * la ubicacion y el mensaje recibido de la nave de carga .
	 * 
	 * @version 1.0
	 * @param RequestDto requestDto
	 */
	@Override
	public ResponseDto getLocationMessage(RequestDto requestDto) throws Exception {
		ResponseDto responseDto = new ResponseDto();
		PositionDto positionDto = new PositionDto();

		float[] shipPosition = getLocation(requestDto.getSatellites().get(0).getDistance(),
				requestDto.getSatellites().get(1).getDistance(), requestDto.getSatellites().get(2).getDistance());

		positionDto.setX(shipPosition[0]);
		positionDto.setY(shipPosition[1]);

		responseDto.setPosition(positionDto);

		String[] messageKenoby = new String[requestDto.getSatellites().get(0).getMessage().size()];
		String[] messageSkyWalker = new String[requestDto.getSatellites().get(0).getMessage().size()];
		String[] messageSato = new String[requestDto.getSatellites().get(0).getMessage().size()];

		for (int i = 0; i < requestDto.getSatellites().get(0).getMessage().size(); i++) {
			messageKenoby[i] = requestDto.getSatellites().get(0).getMessage().get(i);
		}

		for (int i = 0; i < requestDto.getSatellites().get(1).getMessage().size(); i++) {
			messageSkyWalker[i] = requestDto.getSatellites().get(1).getMessage().get(i);
		}

		for (int i = 0; i < requestDto.getSatellites().get(2).getMessage().size(); i++) {
			messageSato[i] = requestDto.getSatellites().get(2).getMessage().get(i);
		}

		if (getMessage(messageKenoby, messageSkyWalker, messageSato).equals("No se puede armar mensaje")) {
			responseDto = new ResponseDto();
			responseDto.setMessage("Informacion (Palabras) insuficientes para poder calcular ubicacion y mensaje "
					+ "enviado por nave montacarga");

			return responseDto;
		}
		responseDto.setMessage(getMessage(messageKenoby, messageSkyWalker, messageSato));

		return responseDto;
	}

	/**
	 * Metodo de negocio que se encarga de almacenar la informacion recibida por el
	 * metodo post /topsecret_split para posteriormente construir respuesta en
	 * metodo GET si es posible.
	 * 
	 * @version 1.0
	 * @param RequestSplitDto requestSplitDto
	 * @param String          shipName
	 */
	@Override
	public ResponseDto postSplitLocationMessage(RequestSplitDto requestSplitDto, String shipName) throws Exception {

		if (shipName.toLowerCase().equals("kenobi")) {
			if (!satelliteRepository.existsById(1)) {
				Satellite satellite = new Satellite();
				satellite.setId(1);
				satellite.setDistance(requestSplitDto.getDistance());
				satellite.setName(shipName);
				satellite.setMessage(requestSplitDto.getMessage());
				satelliteRepository.save(satellite);
			}
			satelliteRepository.deleteById(1);
			Satellite satellite = new Satellite();
			satellite.setId(1);
			satellite.setDistance(requestSplitDto.getDistance());
			satellite.setName(shipName);
			satellite.setMessage(requestSplitDto.getMessage());

			satelliteRepository.save(satellite);
		}

		if (shipName.toLowerCase().equals("skywalker")) {
			if (!satelliteRepository.existsById(2)) {
				Satellite satellite = new Satellite();
				satellite.setId(2);
				satellite.setDistance(requestSplitDto.getDistance());
				satellite.setName(shipName);
				satellite.setMessage(requestSplitDto.getMessage());
				satelliteRepository.save(satellite);
			}
			satelliteRepository.deleteById(2);
			Satellite satellite = new Satellite();
			satellite.setId(2);
			satellite.setDistance(requestSplitDto.getDistance());
			satellite.setName(shipName);
			satellite.setMessage(requestSplitDto.getMessage());

			satelliteRepository.save(satellite);
		}

		if (shipName.toLowerCase().equals("sato")) {
			if (!satelliteRepository.existsById(3)) {
				Satellite satellite = new Satellite();
				satellite.setId(3);
				satellite.setDistance(requestSplitDto.getDistance());
				satellite.setName(shipName);
				satellite.setMessage(requestSplitDto.getMessage());
				satelliteRepository.save(satellite);
			}
			satelliteRepository.deleteById(3);
			Satellite satellite = new Satellite();
			satellite.setId(3);
			satellite.setDistance(requestSplitDto.getDistance());
			satellite.setName(shipName);
			satellite.setMessage(requestSplitDto.getMessage());

			satelliteRepository.save(satellite);
		}

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Datos ingresados correctamente");
		return responseDto;
	}

	/**
	 * Metodo de negocio que hace llamado a getSplitLocationMessage quien a su vez
	 * invoca getLocation y getMessage para retornar la ubicacion y el mensaje
	 * recibido de la nave de carga a partir de las peticiones recibidas en la
	 * operacion /topsecret_split.
	 * 
	 * @version 1.0
	 * @return ResponseDto
	 */
	@Override
	public ResponseDto getSplitLocationMessage() throws Exception {

		ResponseDto responseDto = getSplitLocationMessage(satelliteRepository.findAll());

		return responseDto;
	}

	/**
	 * Metodo usado para calcular la ubicacion de la nave portacarga, Se usa el
	 * metodo trilateracion con la ubicacion de los tres satelites y su respectiva
	 * distancia a la nave portacarga, para hacer dicho calculo.
	 * 
	 * @version 1.0
	 * @param float distanceKenoby
	 * @param float distanceSkyWalker
	 * @param float distanceSato
	 * @return float[] location
	 */
	public float[] getLocation(float distanceKenoby, float distanceSkyWalker, float distanceSato) {

		float ax, ay, bx, by, cx, cy, x, y;

		ax = kenobiLocationX;
		ay = kenobiLocationY;

		bx = skyWalkerLocationX;
		by = skyWalkerLocationY;

		cx = satoLocationX;
		cy = satoLocationY;

		// Se usa trilateracion para calcular las coordenadas de la nave portacarga.

		float a1Sq = ax * ax, a2Sq = bx * bx, a3Sq = cx * cx, b1Sq = ay * ay, b2Sq = by * by, b3Sq = cy * cy,
				r1Sq = distanceKenoby * distanceKenoby, r2Sq = distanceSkyWalker * distanceSkyWalker,
				r3Sq = distanceSato * distanceSato;

		float numerator1 = (bx - ax) * (a3Sq + b3Sq - r3Sq) + (ax - cx) * (a2Sq + b2Sq - r2Sq)
				+ (cx - bx) * (a1Sq + b1Sq - r1Sq);

		float denominator1 = 2 * (cy * (bx - ax) + by * (ax - cx) + ay * (cx - bx));

		y = numerator1 / denominator1;

		float numerator2 = r2Sq - r1Sq + a1Sq - a2Sq + b1Sq - b2Sq - 2 * (ay - by) * y;

		float denominator2 = 2 * (ax - bx);

		x = numerator2 / denominator2;

		float[] location = new float[] { x, y };

		return location;
	}

	/**
	 * Metodo usado para calcular el mensaje enviado por la nave portacarga, a
	 * partir de los mensajes recibidos por los tres satelites.
	 * 
	 * @version 1.0
	 * @param String[] messageKenoby
	 * @param String[] messageSkyWalker
	 * @param String[] messageSato
	 * @return String
	 */
	public String getMessage(String[] messageKenoby, String[] messageSkyWalker, String[] messageSato) {

		if (messageKenoby.length != messageSkyWalker.length || messageKenoby.length != messageSato.length) {
		}

		String[] messageToShip = new String[messageKenoby.length];

		for (int i = 0; i < messageKenoby.length; i++) {
			if (messageKenoby[i] == "" && messageSkyWalker[i] == "" && messageSato[i] == "") {
				return "No se puede armar mensaje";
			}
		}

		for (int i = 0; i < messageKenoby.length; i++) {

			if (messageKenoby[i] != "") {

				messageToShip[i] = messageKenoby[i];

			}

			if (messageSkyWalker[i] != "") {
				messageToShip[i] = messageSkyWalker[i];
			}

			if (messageSato[i] != "") {
				messageToShip[i] = messageSato[i];
			}

		}

		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < messageToShip.length; i++) {
			stringBuffer.append(messageToShip[i] + " ");
		}

		return stringBuffer.toString().trim();
	}

	/**
	 * Metodo usado para calcular el mensaje enviado por la nave portacarga, a
	 * partir de los mensajes recibidos por los tres satelites en las peticiones de
	 * la operacion /topsecret_split.
	 * 
	 * @version 1.0
	 * @param List<Satellite> liSatellites
	 * @return ResponseDto responseDto
	 */
	public ResponseDto getSplitLocationMessage(List<Satellite> liSatellites) throws Exception {

		ResponseDto responseDto;

		if (liSatellites.size() != 3) {
			responseDto = new ResponseDto();
			responseDto.setMessage("Informacion insuficiente para poder calcular ubicacion y mensaje "
					+ "enviado por nave montacarga");

			return responseDto;
		}
		responseDto = new ResponseDto();
		PositionDto positionDto = new PositionDto();

		float[] shipPosition = getLocation(liSatellites.get(0).getDistance(), liSatellites.get(1).getDistance(),
				liSatellites.get(2).getDistance());

		positionDto.setX(shipPosition[0]);
		positionDto.setY(shipPosition[1]);

		responseDto.setPosition(positionDto);

		String[] messageKenoby = new String[liSatellites.get(0).getMessage().size()];
		String[] messageSkyWalker = new String[liSatellites.get(0).getMessage().size()];
		String[] messageSato = new String[liSatellites.get(0).getMessage().size()];

		for (int i = 0; i < liSatellites.get(0).getMessage().size(); i++) {
			messageKenoby[i] = liSatellites.get(0).getMessage().get(i);
		}

		for (int i = 0; i < liSatellites.get(1).getMessage().size(); i++) {
			messageSkyWalker[i] = liSatellites.get(1).getMessage().get(i);
		}

		for (int i = 0; i < liSatellites.get(2).getMessage().size(); i++) {
			messageSato[i] = liSatellites.get(2).getMessage().get(i);
		}

		if (getMessage(messageKenoby, messageSkyWalker, messageSato).equals("No se puede armar mensaje")) {
			responseDto = new ResponseDto();
			responseDto.setMessage("Informacion (Palabras) insuficientes para poder calcular ubicacion y mensaje "
					+ "enviado por nave montacarga");

			return responseDto;
		}
		responseDto.setMessage(getMessage(messageKenoby, messageSkyWalker, messageSato));

		return responseDto;
	}
}