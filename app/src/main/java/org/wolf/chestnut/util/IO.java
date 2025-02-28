package org.wolf.chestnut.util;

import java.lang.StringBuilder;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Exception;

public class IO {
    public static String read(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            InputStream stream = new FileInputStream(IO.class.getClassLoader().getResource(path).getFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line = reader.readLine();

            while(line != null) {
                builder.append(line);
                line = reader.readLine();
                if(line != null) builder.append("\n");
            }
            
        } catch(Exception e) {
            Logger.log("File \"" + path + "\" could not be read!");
            System.exit(-1);
        }

        return builder.toString();
    }
}