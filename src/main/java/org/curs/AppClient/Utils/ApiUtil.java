package org.curs.AppClient.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
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

    public static JsonObject getUserData(String apiPath, String login, String password) throws IOException {
        URL url = new URL(SERVER_URL + apiPath + "?login=" + login);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));


        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        inputStream.close();
        reader.close();
        connection.disconnect();

        return  new JsonParser().parse(sb.toString()).getAsJsonObject();
    }

}
