package com.example.user_service.dto;

public record RequestUser(String firstName,
        String middleName,
        String lastName,
        String email,
        String password,
        String phone,
        String address) {

    public RequestUser {
    }

    public static class RequestUserBuilder {

        private final String firstName;
        private final String lastName;
        private final String phone;
        private final String email;
        private final String password;

        private String middleName;
        private String address;

        RequestUserBuilder(String firstName, String lastName, String password, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.phone = phone;
            this.email = email;
            this.middleName = null;
            this.address = null;
        }

        public RequestUserBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public RequestUserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public RequestUser build() {
            return new RequestUser(firstName, middleName, lastName, email, password, phone, address);
        }

    }
}
