package ds2020.assignment2.activityProducer;

public class ActivityReaderApplication {

    public static void main(String args[]) {
        SensorDataProcessing processing = new SensorDataProcessing();
        Producer.sendData(processing.getDataJson());
    }
}
