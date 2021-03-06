package ds2020.assignment2.activityProducer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class SensorData {

    private UUID patient_id;
    private String activity;
    private long startTime;
    private long endTime;

    public SensorData(UUID patientID, LocalDateTime startTime, LocalDateTime endTime, String activity) {
        this.patient_id = patientID;
        this.startTime = startTime.until(LocalDateTime.of(2020, 11, 13, 16, 0), ChronoUnit.SECONDS);
        this.endTime = endTime.until(LocalDateTime.of(2020, 11, 13, 16, 0), ChronoUnit.SECONDS);
        this.activity = activity;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
