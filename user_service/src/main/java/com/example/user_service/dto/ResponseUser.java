package com.example.user_service.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_DEFAULT)
public record ResponseUser(String firstName,
        String middleName,
        String lastName,
        String email,
        String phone,
        String address) {

    public ResponseUser

    {
    }

    public static class ResponseUserBuilder {

        private final String firstName;
        private final String lastName;
        private final String phone;
        private final String email;

        private String middleName;
        private String address;

        public ResponseUserBuilder(String firstName, String lastName, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.middleName = null;
            this.address = null;
        }

        public ResponseUserBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public ResponseUserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public ResponseUser build() {
            return new ResponseUser(firstName, middleName, lastName, email, phone, address);
        }

    }
}
