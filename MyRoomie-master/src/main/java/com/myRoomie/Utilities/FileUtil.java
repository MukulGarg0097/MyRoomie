package com.myRoomie.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

    public static <T> String loadResourceFileData(String filename, Class<T> clazz) throws IOException {
        InputStream stream = clazz.getClassLoader().getResourceAsStream(filename);

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder dataBuffer = new StringBuilder();
        String str = null;
        while ((str = reader.readLine()) != null) {
            dataBuffer.append(str);
        }
        reader.close();
        return dataBuffer.toString();
    }

    public static String secureUrl(String url) {
        if (url != null && url.startsWith("http://")) {
            url = url.replace("http://", "https://");
        }
        return url;
    }

}
