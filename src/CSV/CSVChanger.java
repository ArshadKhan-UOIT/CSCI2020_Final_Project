package CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVChanger {

        public static void read(String fileName) {
            try {
                Reader read = Files.newBufferedReader(Paths.get("DataFiles/" + fileName));

                Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);

                for (CSVRecord record : info) {
                    System.out.println("Class: " + record.get(0));
                    System.out.println("Name: " + record.get(1));
                    System.out.println("Course Code: " + record.get(2));
                    System.out.println("Days: " + record.get(3));
                    System.out.println("Time: " + record.get(4));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void write(String fileName, List<Object[]> data) {
            try {
                Writer write = Files.newBufferedWriter(Paths.get("DataFiles/"+fileName));

                CSVPrinter printer = CSVFormat.DEFAULT.print(write);

                printer.printRecords(data);

                printer.flush();

                write.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
