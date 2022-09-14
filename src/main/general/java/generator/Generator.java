package generator;

import DTO.MeasurementDTO;
import DTO.SensorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Generator {


    private final List<SensorDTO> sensors;


    {
        sensors = new ArrayList<>();
        createSensors();
    }

    private void createSensors() {
        for (int i = 1; i <= 10; i++) {
            sensors.add(new SensorDTO("Test" + i));
        }
    }

    List<SensorDTO> getSensors() {
        return sensors;
    }

    MeasurementDTO createMeasurement() {

        Random r = new Random();
        double value = Math.ceil((-100 + r.nextDouble() * (100 + 100)) * 100) / 100;


        int forSensor = (int) Math.ceil(r.nextDouble() * getSensors().size()) - 1;
        SensorDTO sensorDTO = getSensors().get(forSensor);


        boolean[] bool = new boolean[]{true, false};
        int zeroOrOne = r.nextDouble() > 0.5 ? 1 : 0;
        boolean raining = bool[zeroOrOne];

        return new MeasurementDTO(value, raining, sensorDTO);
    }


    List<MeasurementDTO> get1000measurements() {
        List<MeasurementDTO> measurements = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            measurements.add(createMeasurement());
        }

        return measurements;
    }


}
