package ru.netology.web.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

        public static AuthInfo getAuthInfoVasya() {
            return new AuthInfo("vasya", "qwerty123");
        }
    }

    public static AuthInfo getAuthInfoPetya() {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
}