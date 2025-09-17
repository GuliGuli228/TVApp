package org.curs.AppClient.Enums;

import lombok.Getter;

public enum ApiPaths {
    GET_ALL_CONTRACT("/api/v1/contract/all"),
    GET_ALL_AGENTS("/api/v1/agent/getAll"),
    GET_ALL_CUSTOMERS("/api/v1/customer/getAll"),
    GET_ALL_PROMOS("/api/v1/promo/getAll"),
    GET_ALL_TELECASTS("/api/v1/telecast/getAll"),
    GET_ALL_PLAYBACKS("/api/v1/playback/getAll"),
    GET_USER_DATA("/api/v1/user/login"),
    GET_AGENT_BY_USER_ID("/api/v1/agent/findByUserId"),
    GET_CONTRACTS_BY_AGENT_ID("/api/v1/contract/findByAgentId"),
    GET_CUSTOMERS_BY_AGENT_ID("/api/v1/customer/getAllForAgent"),

    POST_TELECAST("/api/v1/telecast/addTelecast"),
    POST_CUSTOMER("/api/v1/customer/addCustomer"),
    POST_AGENT("/api/v1/agent/addAgent"),
    POST_CONTRACT("/api/v1/contract/addContract"),
    POST_PROMO("/api/v1/promo/addPromo");


    @Getter
    private final String path;
    
    ApiPaths(String path) {
        this.path = path;
    }}
