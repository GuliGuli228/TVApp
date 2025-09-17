package org.curs.AppClient.Utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.curs.AppClient.Enums.ApiPaths;
import org.curs.AppClient.Enums.ApiRequests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiUtil {
    private static final String SERVER_URL = "http://localhost:8085";

    //TODO Убрать все методы кроме parametricRequest
    public static HttpURLConnection fetchApi(String apiPath, ApiRequests requestMethod, JsonObject jsonObject) throws IOException {
        URL url = new URL(SERVER_URL + apiPath); // Url сервера
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Открываю подключение по Url сервера
        connection.setRequestMethod(requestMethod.name()); // Устанавливаю тип запроса (Одно подключение - один запрос)

        if (jsonObject!=null && requestMethod == ApiRequests.POST) {
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); //Настраиваю тип передаваемых значений
            connection.setDoOutput(true);// Открываю канал для передачи данных

            OutputStream os = connection.getOutputStream(); // Открываю Stream внутри канала
            os.write(jsonObject.toString().getBytes());// передаю в Stream данные
            os.flush();// Проталкиваю данные из буфера
            os.close();// Закрываю Stream
        }
        return connection;
    }

//    public static JsonObject getUserData(String apiPath, String login) throws IOException {
//        return ApiUtil.nonParametricRequest(apiPath + "?login=" + login).getAsJsonObject();
//    }
//    public static JsonObject getAgentContractsById(String apiPath, Integer id) throws IOException{
//        return ApiUtil.nonParametricRequest(apiPath + "?id=" + id).getAsJsonObject();
//    }

    public static JsonElement nonParametricRequest(ApiPaths apiPath) throws IOException{
        URL url = new URL(SERVER_URL + apiPath.getPath());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        InputStream input = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            sb.append(line);
        }
        input.close();
        reader.close();
        connection.disconnect();

        return new JsonParser().parse(sb.toString());
    }
    //TODO реализовать проверку кода ответа
    public static JsonElement parametricRequest(ApiPaths apiPath,
                                                ApiRequests requestMethod,
                                                Map<String, String> parameters) throws IOException{

        StringBuilder path = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()){
            path.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if (!path.isEmpty()) {
            path.deleteCharAt(path.length() - 1); // убираем последний &
        }

        URL url = new URL(SERVER_URL+ apiPath.getPath() + "?" + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod.name());
        connection.setRequestProperty("Accept", "application/json");

        if (requestMethod == ApiRequests.GET) {
            InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            input.close();
            reader.close();
            connection.disconnect();

            return new JsonParser().parse(sb.toString());
        }
        return  null;
    }

    //TODO реализовать проверку кода ответа
    public static void bodyRequest(ApiPaths apiPath, ApiRequests requestMethod, JsonObject jsonObject) throws IOException{

        URL url = new URL(SERVER_URL+ apiPath.getPath());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod.name());
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

        if (jsonObject!=null && requestMethod == ApiRequests.POST) {
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); //Настраиваю тип передаваемых значений
            connection.setDoOutput(true);// Открываю канал для передачи данных

            OutputStream os = connection.getOutputStream(); // Открываю Stream внутри канала
            os.write(jsonObject.toString().getBytes());// передаю в Stream данные
            os.flush();// Проталкиваю данные из буфера
            os.close();// Закрываю Stream
            connection.getResponseCode();
        }
    }
}
