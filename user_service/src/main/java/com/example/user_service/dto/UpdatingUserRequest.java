package com.example.user_service.dto;

public record UpdatingUserRequest(String firstName,
        String middleName,
        String lastName,
        String email,
        String phone,
        String address) {

    public UpdatingUserRequest {
    }

    public static Builder Builder(String firstName, String lastName, String password, String phone, String email) {
        return new Builder(firstName, lastName, password, phone, email);
    }

    public static class Builder {

        private final String firstName;
        private final String lastName;
        private final String phone;
        private final String email;
        // OPTIONAL PARTS
        private String middleName;
        private String address;

        public Builder(String firstName, String lastName, String password, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.middleName = null;
            this.address = null;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public UpdatingUserRequest build() {
            return new UpdatingUserRequest(firstName, middleName, lastName, email, phone, address);
        }

    }

}
