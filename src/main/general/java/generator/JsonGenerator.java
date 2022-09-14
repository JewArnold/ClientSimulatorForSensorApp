package generator;

import DTO.MeasurementDTO;
import DTO.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonGenerator {

    private final Generator generator;
    private final RestTemplate restTemplate;

    {
        generator = new Generator();
        restTemplate = new RestTemplate();
    }


    public Map<String, String> sensorsForRegistration(SensorDTO sensorDTO) {
        Map<String, String> map = new HashMap<>();
        map.put("name", sensorDTO.getName());
        return map;
    }

    public Map<String, Object> measurementsForRegistration(MeasurementDTO measurementDTO) {
        Map<String, Object> map = new HashMap<>();

        map.put("value", measurementDTO.getValue());
        map.put("raining", measurementDTO.isRaining());

        Map<String, String> sensor = new HashMap<>();
        sensor.put("name", measurementDTO.getSensor().getName());

        map.put("sensor", sensor);

        return map;
    }


    private void registrationSensors() {

        for (SensorDTO s :
                generator.getSensors()) {

            Map<String, String> map = sensorsForRegistration(s);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(map);
            String url = "http://localhost:8080/sensors/registration";
            String response = restTemplate.postForObject(url, request, String.class);
            System.out.println(response);

        }
    }

    private void registrationMeasurements() {

        List<MeasurementDTO> measurementDTOS = generator.get1000measurements();

        for (MeasurementDTO mes :
                measurementDTOS) {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(measurementsForRegistration(mes));
            String url = "http://localhost:8080/measurements/add";
            String response = restTemplate.postForObject(url, request, String.class);
            System.out.println(response);
        }


    }

    public void run() {

        registrationSensors();
        registrationMeasurements();
    }


}
