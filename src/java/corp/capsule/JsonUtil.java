package corp.capsule;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
    private static final String FILE_PATH = "C:/Users/psalb/Documents/NetBeansProjects/ApiSoapPoC/build/web/WEB-INF/personajes.json";
    private static final Gson gson = new Gson();

    public static List<Personaje> readJson() throws IOException {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type personajeListType = new TypeToken<List<Personaje>>(){}.getType();
            return gson.fromJson(reader, personajeListType);
        }
    }

    public static void writeJson(List<Personaje> personajes) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(personajes, writer);
        }
    }
}
