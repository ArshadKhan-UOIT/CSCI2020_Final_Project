package main.java.CourseContent.CSV;

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

public class CSVChanger {

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
            CSVPrinter printer = CSVFormat.DEFAULT.withRecordSeparator("\r\n").print(write);
//            printer.println();
            printer.printRecords(data);

            printer.close(true);
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeOver(String fileName, List<String[]> data) {
        try {
            FileWriter write = new FileWriter(String.valueOf(Paths.get("DataFiles/" + fileName)));
            CSVPrinter printer = CSVFormat.DEFAULT.print(write);
            printer.printRecords(data);

            printer.flush();

            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
