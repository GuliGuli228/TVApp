package org.curs.AppClient;


import com.google.gson.*;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppCache {
    private static Integer userId;
    private static String userName;
    private static String role;
    private static List<AdminContract> adminContracts = new ArrayList<>();
    private static List<AdminPlaybackResponse> adminPlaybackResponses = new ArrayList<>();
    private static List<AdminAgentResponse> adminAgentResponses = new ArrayList<>();
    private static List<AdminCustomersResponse> adminCustomersResponses = new ArrayList<>();
    private static List<PromoResponse> promoResponses = new ArrayList<>();
    private static List<TelecastResponse> telecastResponses = new ArrayList<>();


    /*--- POJOs ---*/
    public record AdminContract(Integer contractId,
                                Integer agentId,
                                Integer customerId,
                                Double price){}

    public record AdminPlaybackResponse(Integer playbackId,
                                        Integer contractId,
                                        Integer promoId,
                                        String telecastName,
                                        String playbackTime,
                                        String playbackDate,
                                        Double price){}

    public record AdminAgentResponse(Integer agentId,
                                     String agentLogin,
                                     Double percent,
                                     Double income,
                                     Integer amountOfContracts){};

    public record AdminCustomersResponse (Integer customerId,
                                          String iban,
                                          String phone,
                                          String contactPerson,
                                          Integer amountOfContracts,
                                          Double priceOfContracts) {}
    public record PromoResponse(Integer promoId,
                                Integer customerId,
                                String duration,
                                String promoUrl){}

    public record TelecastResponse(Integer id,
                                   Double rating,
                                   Double minuteCost,
                                   String telecastName){}
    /*-----------*/

    public static void loadCache(){
        try {
            if (role.equals("Admin")){
                JsonArray contracts = ApiUtil.nonParametricRequest("/api/v1/contract/all").getAsJsonArray();
                JsonArray playbacks = ApiUtil.nonParametricRequest("/api/v1/playback/getAll").getAsJsonArray();
                JsonArray agents = ApiUtil.nonParametricRequest("/api/v1/agent/getAll").getAsJsonArray();
                JsonArray customers = ApiUtil.nonParametricRequest("/api/v1/customer/getAll").getAsJsonArray();
                JsonArray promos = ApiUtil.nonParametricRequest("/api/v1/promo/getAll").getAsJsonArray();
                JsonArray telecasts = ApiUtil.nonParametricRequest("/api/v1/telecast/getAll").getAsJsonArray();

                adminContracts = AppCache.parser(AdminContract.class, contracts);
                adminPlaybackResponses = AppCache.parser(AdminPlaybackResponse.class, playbacks);
                adminAgentResponses =  AppCache.parser(AdminAgentResponse.class, agents);
                adminCustomersResponses = AppCache.parser(AdminCustomersResponse.class, customers);
                promoResponses = AppCache.parser(PromoResponse.class, promos);
                telecastResponses = AppCache.parser(TelecastResponse.class, telecasts);

            }
            if(role.equals("Agent")){
                JsonObject contracts = ApiUtil.parametricRequest("/api/v1/contracts/byAgentId", ApiUtil.RequestMethod.GET, Map.of("id", userId.toString())).getAsJsonObject();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    /*---Getters & Setters---*/
    public static Integer getUserId() {
        return AppCache.userId;
    }
    public static String getUserName() {
        return AppCache.userName;
    }
    public static void setUserId(Integer userId) {
        AppCache.userId = userId;
    }
    public static void setUserName(String userName) {
        AppCache.userName = userName;
    }
    public static String getRole() {
        return AppCache.role;
    }
    public static void setRole(String role) {
        AppCache.role = role;
    }
    public static List<AdminContract> getContracts(){
        return  AppCache.adminContracts;
    }
    public static List<AdminPlaybackResponse> getAdminPlaybackResponses(){
        return AppCache.adminPlaybackResponses;
    }
    public static List<AdminAgentResponse> getAdminAgentResponses(){
        return AppCache.adminAgentResponses;
    }
    public static List<AdminCustomersResponse> getAdminCustomersResponses(){
        return AppCache.adminCustomersResponses;
    }
    public static List<PromoResponse> getPromoResponses(){
        return AppCache.promoResponses;
    }
    public static List<TelecastResponse> getTelecastResponses(){
        return AppCache.telecastResponses;
    }
    /*--------------------*/

    /*---Methods---*/

    private static <T> List<T> parser(Class<T> pojo, JsonArray data){
        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        for (JsonElement elem : data) {
            T obj = gson.fromJson(elem, pojo);
            list.add(obj);
        }
        return list;
    }

}
