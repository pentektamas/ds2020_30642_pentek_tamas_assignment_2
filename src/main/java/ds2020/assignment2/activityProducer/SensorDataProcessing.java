package ds2020.assignment2.activityProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SensorDataProcessing {

    private final UUID patientID = UUID.fromString("019e8286-a32b-45e4-9e4a-5aec4c41c2c2");
    private final String FILE_NAME = "activity.txt";

    public SensorDataProcessing() {
    }

    public List<SensorData> getData() {

        List<SensorData> list = new ArrayList<SensorData>();
        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(FILE_NAME));
        } catch (IOException e) {
            System.out.println("Error while creating the stream form the file!");
        }

        List<String> start = stream.collect(Collectors.toList());
        Function<String, String[]> split = x -> x.split("[\\t]");
        for (String s : start) {
            String[] newString = split.apply(s);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            list.add(new SensorData(this.patientID, LocalDateTime.parse(newString[0], dtf), LocalDateTime.parse(newString[2], dtf), newString[4]));
        }
        return list;
    }

    public List<String> getDataJson() {
        List<String> activitiesJSON = new ArrayList<>();
        List<SensorData> activities = this.getData();
        ObjectMapper mapper = new ObjectMapper();
        for (SensorData sensorData : activities) {
            try {
                activitiesJSON.add(mapper.writeValueAsString(sensorData));
            } catch (JsonProcessingException e) {
                System.out.println("Error while converting!");
                e.printStackTrace();
            }
        }
        return activitiesJSON;
    }
}
