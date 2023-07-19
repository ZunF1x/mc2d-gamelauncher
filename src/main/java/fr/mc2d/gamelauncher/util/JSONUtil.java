package fr.mc2d.gamelauncher.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONUtil {

    public static void writeToJSON(String jsonString, Path jsonFile) throws Exception {
        BufferedWriter writer = Files.newBufferedWriter(jsonFile);

        writer.write(jsonString);

        writer.flush();
        writer.close();
    }

    public static JSONObject readFromJSON(Path jsonFile) throws Exception {
        JSONParser parser = new JSONParser();

        BufferedReader reader = Files.newBufferedReader(jsonFile);

        StringBuilder content = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        reader.close();

        return (JSONObject) parser.parse(content.toString());
    }
}
