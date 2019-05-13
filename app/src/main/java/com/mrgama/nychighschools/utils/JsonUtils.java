package com.mrgama.nychighschools.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrgama.nychighschools.model.School;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static ArrayList<School> parseSchoolsFromRawFile(Context context, int rawFile) {
        String jsonString = "";
        InputStream inputStream = context.getResources().openRawResource(rawFile);
        try {
            jsonString = parseStream(inputStream);
        } catch (IOException e) {
            return null;
        }

        return new Gson().fromJson(jsonString, new TypeToken<List<School>>() {
        }.getType());
    }

    private static String parseStream(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
        }
        in.close();
        return builder.toString();
    }
}
