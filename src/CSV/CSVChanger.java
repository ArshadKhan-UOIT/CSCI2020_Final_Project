<<<<<<< HEAD
package CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVChanger {

        public static List<String[]> read(String fileName) {
            List<String[]> data = null;
            try {
                Reader read = Files.newBufferedReader(Paths.get("DataFiles/" + fileName));
                data = new ArrayList<>();

                Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);

                for (CSVRecord record : info) {
                    data.add(new String[]{record.get(0), record.get(1), record.get(2),record.get(3),record.get(4),record.get(5)});
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        public static void write(String fileName, List<String[]> data) {
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
=======
package CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVChanger implements Runnable {

    @Override
    public void run() {

    }

    public static List<String[]> read(String fileName, int size) {
        List<String[]> data = null;
        try {
            Reader read = Files.newBufferedReader(Paths.get("DataFiles/" + fileName));
            data = new ArrayList<>();

            String[] str;

            Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);

            for (CSVRecord record : info) {
                str = new String[size];
                for (int i = 0; i < size; i++) {
                    str[i] = record.get(i);
                }

                data.add(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void write(String fileName, List<String[]> data) {
        try {
            FileWriter write = new FileWriter(String.valueOf(Paths.get("DataFiles/" + fileName)), true);
            CSVPrinter printer = CSVFormat.DEFAULT.print(write);

            printer.printRecords(data);

            printer.flush();

            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> origin/Arshad
