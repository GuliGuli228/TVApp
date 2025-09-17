package org.curs.AppClient;


import com.google.gson.*;
import jakarta.annotation.Nullable;
import org.curs.AppClient.Enums.ApiPaths;
import org.curs.AppClient.Enums.ApiRequests;
import org.curs.AppClient.Enums.Scenes;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class AppCache {
    @Nullable private static Integer agentId;
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

    public static Integer getAgentId(){
        return AppCache.agentId;
    }
    public static String getUserLogin() {
        return AppCache.login;
    }
    public static Integer getUserId() {
        return AppCache.userId;
    }
    public static Scenes getLastScene(){
        return AppCache.lastScene;
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
                JsonArray contracts = ApiUtil.nonParametricRequest(ApiPaths.GET_ALL_CONTRACT).getAsJsonArray();
                JsonArray agents = ApiUtil.nonParametricRequest(ApiPaths.GET_ALL_AGENTS).getAsJsonArray();
                JsonArray customers = ApiUtil.nonParametricRequest(ApiPaths.GET_ALL_CUSTOMERS).getAsJsonArray();

                adminContracts = AppCache.parser(AdminContract.class, contracts);
                adminAgentResponses =  AppCache.parser(AdminAgentResponse.class, agents);
                customersResponses = AppCache.parser(CustomersResponse.class, customers);

                AppCache.setLastScene(Scenes.ADMIN_CONTRACTS);
            }
            if(role.equals("Agent")){
                agentId = Objects.requireNonNull(ApiUtil.parametricRequest(
                        ApiPaths.GET_AGENT_BY_USER_ID,
                        ApiRequests.GET,
                        Map.of("userId", userId.toString()))).getAsJsonObject().get("id").getAsInt();

                JsonArray contracts = Objects.requireNonNull(ApiUtil.parametricRequest(
                        ApiPaths.GET_CONTRACTS_BY_AGENT_ID,
                        ApiRequests.GET,
                        Map.of("agentId", Integer.toString(agentId)))).getAsJsonArray();

                JsonArray customers = Objects.requireNonNull(ApiUtil.parametricRequest(
                        ApiPaths.GET_CUSTOMERS_BY_AGENT_ID,
                        ApiRequests.GET,
                        Map.of("agentId", Integer.toString(agentId)))).getAsJsonArray();

                agentContractResponses = AppCache.parser(AgentContractResponse.class, contracts);
                customersResponses = AppCache.parser(CustomersResponse.class, customers);

                AppCache.setLastScene(Scenes.AGENT_CONTRACTS);

            }

            JsonArray promos = ApiUtil.nonParametricRequest(ApiPaths.GET_ALL_PROMOS).getAsJsonArray();
            JsonArray telecasts = ApiUtil.nonParametricRequest(ApiPaths.GET_ALL_TELECASTS).getAsJsonArray();
            JsonArray playbacks = ApiUtil.nonParametricRequest(ApiPaths.GET_ALL_PLAYBACKS).getAsJsonArray();


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
