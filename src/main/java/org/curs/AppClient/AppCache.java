package org.curs.AppClient;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.util.List;

public class AppCache {
    private static Integer userId;
    private static String userName;
    private static String role;
    private static List<AdminContract> adminContracts;

    /*--- POJOs ---*/
    public record AdminContract(Integer contractId, Double price, Integer agentId, Integer customerId){}
    /*-----------*/

    public static void loadCache(){
        try {
            if (role.equals("Admin")){
                JsonObject contracts = ApiUtil.getData("/api/v1/adminContracts/all");
                AppCache.contractsParsing(contracts);
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
    /*--------------------*/

    /*---Methods---*/
    private static void contractsParsing(JsonObject contracts){
        JsonArray contractsArray = JsonParser.parseString(contracts.getAsString()).getAsJsonArray();
        for (int i =0; i < contractsArray.size(); i++){
            JsonObject currentContract = contractsArray.get(i).getAsJsonObject();
            Integer contractId = currentContract.get("contractId").getAsInt();
            Double price = currentContract.get("price").getAsDouble();
            Integer agentId = currentContract.get("agentId").getAsInt();
            Integer customerId = currentContract.get("customerId").getAsInt();
            AppCache.adminContracts.add(new AdminContract(contractId, price, agentId, customerId));
        }
    }

}
