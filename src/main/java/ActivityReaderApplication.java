
public class ActivityReaderApplication {


    public ActivityReaderApplication() {
    }


    public static void main(String args[]) {

        DataProcessing processing = new SensorDataProcessing();
        System.out.println("The activity reader is running");
        Producer.sendData(processing.getDataJson());

    }
}
