package org.curs.AppClient;


import com.google.gson.*;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppCache {
    private static Integer userId;
    private static String userName;
    private static String role;
    private static List<AdminContract> adminContracts = new ArrayList<>();
    private static List<AdminPlaybackResponse> adminPlaybackResponses = new ArrayList<>();
    private static List<AdminAgentResponse> adminAgentResponses = new ArrayList<>();

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
    /*-----------*/

    public static void loadCache(){
        try {
            if (role.equals("Admin")){
                JsonArray contracts = ApiUtil.getData("/api/v1/contract/all").getAsJsonArray();
                JsonArray playbacks = ApiUtil.getData("/api/v1/playback/getAll").getAsJsonArray();
                JsonArray agents = ApiUtil.getData("/api/v1/agent/getAll").getAsJsonArray();

                adminContracts = AppCache.parser(AdminContract.class, contracts);
                adminPlaybackResponses = AppCache.parser(AdminPlaybackResponse.class, playbacks);
                adminAgentResponses =  AppCache.parser(AdminAgentResponse.class, agents);

            }
            if(role.equals("Agent")){
                JsonObject contracts = ApiUtil.getAgentContractsById("/api/v1/adminContracts/ByAgentId", userId);
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
