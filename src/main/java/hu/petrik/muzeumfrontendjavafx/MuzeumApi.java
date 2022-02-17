package hu.petrik.muzeumfrontendjavafx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MuzeumApi {
    private static final String BASE_URL = "http://127.0.0.1:8000";
    private static final String FESTMENY_API_URL = BASE_URL + "/api/paintings";
    private static final String SZOBOR_API_URL = BASE_URL + "/api/statues";

    public static List<Festmeny> getFestmenyek() throws IOException {
        Response response = RequestHandler.get(FESTMENY_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400) {
            System.out.println(json);
            String uzenet = jsonConvert.fromJson(json, ErrorApi.class).getUzenet();
            throw new IOException(uzenet);

        }
        Type type = new TypeToken<List<Festmeny>>(){}.getType();
         return jsonConvert.fromJson(json, type);
    }


    public static List<Szobor> getSzobrok() throws IOException {
        Response response = RequestHandler.get(SZOBOR_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400) {
            System.out.println(json);
            String uzenet = jsonConvert.fromJson(json, ErrorApi.class).getUzenet();
            throw new IOException(uzenet);
        }
        Type type = new TypeToken<List<Szobor>>(){}.getType();
        return jsonConvert.fromJson(json, type);
    }




}
