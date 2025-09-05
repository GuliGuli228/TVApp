package org.curs.AppClient;

import lombok.Data;
import lombok.Getter;



public class AppContext {
    private static Integer userId;
    private static String userName;
    private static String role;

    public static Integer getUserId() {
        return AppContext.userId;
    }
    public static String getUserName() {
        return AppContext.userName;
    }
    public static void setUserId(Integer userId) {
        AppContext.userId = userId;
    }
    public static void setUserName(String userName) {
        AppContext.userName = userName;
    }
    public static String getRole() {
        return AppContext.role;
    }
    public static void setRole(String role) {
        AppContext.role = role;
    }
}
