package org.curs.AppClient.Utils;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtil {
    private static final String SERVER_URL = "http://localhost:8085";
    public  enum RequestMethod{POST , GET, PUT, DELETE};

    public static HttpURLConnection fetchApi(String apiPath, RequestMethod requestMethod, JsonObject jsonObject) throws IOException {
        URL url = new URL(SERVER_URL + apiPath); // Url сервера
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Открываю подключение по Url сервера
        connection.setRequestMethod(requestMethod.name()); // Устанавливаю тип запроса (Одно подключение - один запрос)

        if (jsonObject!=null && requestMethod == RequestMethod.POST) {
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); //Настраиваю тип передаваемых значений
            connection.setRequestProperty("Accept", "application/json");//Настраиваю тип получаемых значений

            connection.setDoOutput(true);// Открываю канал для передачи данных

            OutputStream os = connection.getOutputStream(); // Открываю Stream внутри канала
            os.write(jsonObject.toString().getBytes());// передаю в Stream данные
            os.flush();// Проталкиваю данные из буфера
            os.close();// Закрываю Stream
        }
        return connection;
    }
}
