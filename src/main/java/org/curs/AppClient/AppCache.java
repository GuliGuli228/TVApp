package org.curs.AppClient;


import com.google.gson.*;
import org.curs.AppClient.ScenesControllers.Scenes;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AppCache {
    private static Integer userId;
    private static String userName;
    private static String role;
    private static String login;
    private static List<AdminContract> adminContracts = new ArrayList<>();
    private static List<PlaybackResponse> playbackResponses = new ArrayList<>();
    private static List<AdminAgentResponse> adminAgentResponses = new ArrayList<>();
    private static List<CustomersResponse> customersResponses = new ArrayList<>();
    private static List<PromoResponse> promoResponses = new ArrayList<>();
    private static List<TelecastResponse> telecastResponses = new ArrayList<>();
    private static List<AgentContractResponse> agentContractResponses = new ArrayList<>();
    private static Scenes lastScene;
    private static Scenes previousScene;

    private final static Logger logger = Logger.getLogger(AppCache.class.getName());



    /*--- POJOs ---*/
    /*&&--- Admin POJOs ---&&*/
    public record AdminContract(Integer contractId,
                                Integer agentId,
                                Integer customerId,
                                Double price){}


    public record AdminAgentResponse(Integer agentId,
                                     String agentLogin,
                                     Double percent,
                                     Double income,
                                     Integer amountOfContracts){};
    /*&&-------------------&&*/



    /*&&--- Agent POJOs ---&&*/
    public record AgentContractResponse (Integer contractId,
                                         Integer customerId,
                                         Double price){}
    /*&&-------------------&&*/



    /*&&--- Common POJOs ---&&*/
    public record PromoResponse(Integer promoId,
                                Integer customerId,
                                String duration,
                                String promoUrl){}

    public record TelecastResponse(Integer id,
                                   Double rating,
                                   Double minuteCost,
                                   String telecastName){}

    public record PlaybackResponse(Integer playbackId,
                                   Integer contractId,
                                   Integer promoId,
                                   String telecastName,
                                   String playbackTime,
                                   String playbackDate,
                                   Double price){}

    public record CustomersResponse (Integer customerId,
                                     String iban,
                                     String phone,
                                     String contactPerson,
                                     Integer amountOfContracts,
                                     Double priceOfContracts) {}
    /*&&--------------------&&*/
    /*-----------*/


    /*---Getters & Setters---*/
    public static void setLastScene(Scenes lastScene){
        previousScene = AppCache.lastScene;
        AppCache.lastScene = lastScene;
    }
    public static void setUserId(Integer userId) {
        AppCache.userId = userId;
    }
    public static void setUserName(String userName) {
        AppCache.userName = userName;
    }
    public static void setRole(String role) {
        AppCache.role = role;
    }
    public static void setUserLogin(String login) {
        AppCache.login = login;
    }

    public static String getUserLogin() {
        return AppCache.login;
    }
    public static Integer getUserId() {
        return AppCache.userId;
    }
    public static String getUserName() {
        return AppCache.userName;
    }
    public static String getRole() {
        return AppCache.role;
    }
    public static Scenes getPreviousScene() {
        return AppCache.previousScene;
    }
    public static List<AdminContract> getAdminContracts(){
        return  AppCache.adminContracts;
    }
    public static List<PlaybackResponse> getPlaybackResponses(){
        return AppCache.playbackResponses;
    }
    public static List<AdminAgentResponse> getAdminAgentResponses(){
        return AppCache.adminAgentResponses;
    }
    public static List<CustomersResponse> getCustomersResponses(){
        return AppCache.customersResponses;
    }
    public static List<PromoResponse> getPromoResponses(){
        return AppCache.promoResponses;
    }
    public static List<TelecastResponse> getTelecastResponses(){
        return AppCache.telecastResponses;
    }
    public static List<AgentContractResponse> getAgentContractResponses(){
        return AppCache.agentContractResponses;
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

    public static void loadCache(){
        try {
            if (role.equals("Admin")){
                JsonArray contracts = ApiUtil.nonParametricRequest("/api/v1/contract/all").getAsJsonArray();
                JsonArray agents = ApiUtil.nonParametricRequest("/api/v1/agent/getAll").getAsJsonArray();
                JsonArray customers = ApiUtil.nonParametricRequest("/api/v1/customer/getAll").getAsJsonArray();

                adminContracts = AppCache.parser(AdminContract.class, contracts);
                adminAgentResponses =  AppCache.parser(AdminAgentResponse.class, agents);
                customersResponses = AppCache.parser(CustomersResponse.class, customers);

                AppCache.setLastScene(Scenes.ADMIN_CONTRACTS);
            }
            if(role.equals("Agent")){
                Integer agentId = ApiUtil.parametricRequest(
                        "/api/v1/agent/findByUserId",
                        ApiUtil.RequestMethod.GET,
                        Map.of("userId", userId.toString())).getAsJsonObject().get("id").getAsInt();

                JsonArray contracts = ApiUtil.parametricRequest(
                        "/api/v1/contract/findByAgentId",
                        ApiUtil.RequestMethod.GET,
                        Map.of("agentId", agentId.toString())).getAsJsonArray();

                JsonArray customers = ApiUtil.parametricRequest(
                        "/api/v1/customer/getAllForAgent",
                        ApiUtil.RequestMethod.GET,
                        Map.of("agentId", agentId.toString())).getAsJsonArray();

                agentContractResponses = AppCache.parser(AgentContractResponse.class, contracts);
                customersResponses = AppCache.parser(CustomersResponse.class, customers);

                AppCache.setLastScene(Scenes.AGENT_CONTRACTS);

            }

            JsonArray promos = ApiUtil.nonParametricRequest("/api/v1/promo/getAll").getAsJsonArray();
            JsonArray telecasts = ApiUtil.nonParametricRequest("/api/v1/telecast/getAll").getAsJsonArray();
            JsonArray playbacks = ApiUtil.nonParametricRequest("/api/v1/playback/getAll").getAsJsonArray();


            promoResponses = AppCache.parser(PromoResponse.class, promos);
            telecastResponses = AppCache.parser(TelecastResponse.class, telecasts);
            playbackResponses = AppCache.parser(PlaybackResponse.class, playbacks);

            logger.info("Current user: id" + userId + " name: " + userName + " role: " + role );
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearCache(){
        userId = null;
        userName = null;
        role = null;
        lastScene = null;

        if (adminContracts!=null) adminContracts.clear();
        if (playbackResponses!=null) playbackResponses.clear();
        if (adminAgentResponses!=null) adminAgentResponses.clear();
        if (customersResponses!=null) customersResponses.clear();
        if (promoResponses!=null) promoResponses.clear();
        if (telecastResponses!=null) telecastResponses.clear();
        if (agentContractResponses!=null) agentContractResponses.clear();
    }

}
