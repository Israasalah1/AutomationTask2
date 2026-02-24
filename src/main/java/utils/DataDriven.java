package utils;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.Scanner;


public class DataDriven {

    public static JSONObject jsonReader() {
        try {
            InputStream is = DataDriven.class
                    .getClassLoader()
                    .getResourceAsStream("testData.json");

            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";

            return new JSONObject(content);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
