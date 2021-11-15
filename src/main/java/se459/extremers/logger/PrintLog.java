package se459.extremers.logger;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintLog {

    FileWriter myWriter;

    SimpleDateFormat formatter;

    public PrintLog(String name) {

        formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            this.myWriter = new FileWriter(name);
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }

    public void write(String tmp) {
        try {
            this.myWriter.write(tmp + '\n');
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public void CloseFile() {

        try {
            this.myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }

    public String getDateTime() {
        return formatter.format(new Date(System.currentTimeMillis()));
    }
    
}
