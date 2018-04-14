package storage;

import model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static void main(String[] args) throws IOException {
        // open file input stream
        // BufferedReader reader = new BufferedReader(new FileReader(
        //       ".persons.csv"));

        File inputF = new File("persons.csv");
        InputStream inputFS = new FileInputStream(inputF);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputFS));

        // read file line by line
        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<Person> pList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            Person p = new Person();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    p.setEmailAddress(data);
                else if (index == 1)
                    p.firstName = data;
                else if (index == 2)
                    p.lastName = data;
                else
                    System.out.println("invalid data::" + data);
                index++;
            }
            index = 0;
            pList.add(p);
        }

        //close reader
        reader.close();

        System.out.println(pList);

    }
}