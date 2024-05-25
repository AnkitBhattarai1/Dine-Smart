package com.example.user_service.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Defines a data transfer object (DTO) to represent user response information.
 * This record is designed to be used in API responses where null fields are
 * omitted in the serialized JSON when their values are not set (non-default).
 *
 * @param firstName  the user's first name
 * @param middleName the user's middle name (optional)
 * @param lastName   the user's last name
 * @param email      the user's email address
 * @param phone      the user's phone number
 * @param address    the user's address (optional)
 * @param response   the user's photo details wrapped in a {@link PhotoResponse}
 *                   object (optional)
 */

@JsonInclude(NON_DEFAULT)
public record ResponseUser(String firstName,
        String middleName,
        String lastName,
        String email,
        String phone,
        String address,
        PhotoResponse response) {

    public ResponseUser {
    }

    /**
     * Builder class for {@Link ResponseUser}. Simplifies the process of
     * constructing
     * a new ResponseUser instance by providing settable fields and a build method.
     */
    public static class ResponseUserBuilder {

        private final String firstName;
        private final String lastName;
        private final String phone;
        private final String email;
        // Optional.....
        private String middleName;
        private String address;

        private PhotoResponse photoResponse;

        /**
         * Constructs a new builder for a {@link ResponseUser} with required fields.
         *
         * @param firstName the first name of the user, cannot be null
         * @param lastName  the last name of the user, cannot be null
         * @param phone     the phone number of the user, cannot be null
         * @param email     the email address of the user, cannot be null
         */
        public ResponseUserBuilder(String firstName, String lastName, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
        }

        /**
         * Sets the middle name of the user.
         *
         * @param middleName the middle name to be set
         * @return this builder instance for chaining method calls
         */
        public ResponseUserBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        /**
         * Sets the address of the user.
         *
         * @param address the address to be set
         * @return this builder instance for chaining method calls
         */
        public ResponseUserBuilder address(String address) {
            this.address = address;
            return this;
        }

        /**
         * Sets the photo response details of the user.
         *
         * @param response a {@link PhotoResponse} object containing the user's photo
         *                 details
         * @return this builder instance for chaining method calls
         */
        public ResponseUserBuilder photoResponse(PhotoResponse response) {
            this.photoResponse = response;
            return this;
        }

        /**
         * Constructs a new {@link ResponseUser} instance based on the set attributes.
         *
         * @return a new instance of ResponseUser populated with builder's data
         */
        public ResponseUser build() {
            validate(this);
            return new ResponseUser(firstName, middleName, lastName, email, phone, address, photoResponse);
        }

        private void validate(ResponseUserBuilder responseUserBuilder) {
            // TODO Validation is to be done....
        }
    }
}
