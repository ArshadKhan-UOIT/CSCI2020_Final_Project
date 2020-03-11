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

        public static List<String[]> read(String fileName, int numArgs) {
            List<String[]> data = null;
            try {
                Reader read = Files.newBufferedReader(Paths.get("DataFiles/" + fileName));
                data = new ArrayList<>();

                Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);

                for (CSVRecord record : info) {
                    if (numArgs == 5) {//for courses
                        data.add(new String[]{record.get(0), record.get(1), record.get(2),record.get(3),record.get(4)});

                    }
                    else if (numArgs == 4) { //for assignments
                        data.add(new String[]{record.get(0), record.get(1), record.get(2),record.get(3),record.get(4)});

                    }
                    else if (numArgs == 6) { //for assignments
                        data.add(new String[]{record.get(0), record.get(1), record.get(2),record.get(3),record.get(4),record.get(5)});

                    }
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
