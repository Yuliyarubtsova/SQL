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

    public static AuthInfo getAuthInfoInvalid() {
        return new AuthInfo("vasya", "123");
    }
    @Value
    public static class VerificationCode {
        private String code;
    }
}
