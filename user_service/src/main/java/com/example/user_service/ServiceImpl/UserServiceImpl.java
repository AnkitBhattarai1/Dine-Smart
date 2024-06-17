package com.example.user_service.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.user_service.Models.AuthUser;
import com.example.user_service.Models.User;
import com.example.user_service.Repo.UserRepo;
import com.example.user_service.Services.UserService;
import com.example.user_service.dto.NonRegisteredRequestUser;
import com.example.user_service.dto.Photo;
import com.example.user_service.dto.RequestUser;
import com.example.user_service.dto.ResponseUser;
import com.example.user_service.dto.UpdatingUserRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

/**
 * Implementation of the {@link UserService} interface.
 * This class provides methods to manage user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    // Constructor injection of userRepo....
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    RestTemplate restTemplate;

    // Function to map RequestUser to User entity
    private Function<RequestUser, User> userReqToUser = (userReq) -> {
        User u = new User();
        u.setFirstName(userReq.firstName());
        u.setLastName(userReq.lastName());
        u.setMiddleName(userReq.middleName());
        u.setAddress((userReq.address()));
        u.setEmail(userReq.email());
        // u.setPassword(userReq.password());
        u.setPhone(userReq.phone());
        u.setRegistered(true);
        return u;
    };

    // Function to map User entity to ResponseUser
    private Function<User, ResponseUser> userToUserRes = (user) -> {

        ResponseUser u = new ResponseUser.ResponseUserBuilder(user.getFirstName(),
                user.getLastName(), user.getPhone(), user.getEmail())
                .address(user.getAddress())
                .middleName(user.getMiddleName())
                .build();

        return u;
    };

    /**
     * Retrieves all users and maps them to ResponseUser DTO.
     *
     * @return List of ResponseUser DTOs representing all users.
     */

    @Override
    public List<ResponseUser> getAllUsers() {

        return userRepo.findAll().stream().map(
                (user) -> userToUserRes.apply(user)).toList();
    }

    /**
     * Saves a new registered user.
     * Registers the user with the authentication service.
     *
     * @param requestUser The RequestUser object containing user details.
     * @return The saved user as a ResponseUser DTO.
     * @throws IllegalArgumentException if the user is already registered.
     * @throws RuntimeException         if user registration fails.
     */
    @Override
    @Transactional
    public ResponseUser saveUser(RequestUser requestUser) {

        if (userRepo.findByEmail(requestUser.email()).isPresent())
            throw new IllegalArgumentException("User already registered");

        User u = userRepo.save(userReqToUser.apply(requestUser));

        AuthUser authUser = new AuthUser(u.getId(), u.getEmail(), requestUser.password());

        String url = "http://AUTH-SERVICE/auth/register";
        HttpEntity<AuthUser> request = new HttpEntity<>(authUser);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("Could not register the user");
        return userToUserRes.apply(u);
    }

    @Override
    public ResponseUser getUserByEmail(String emaiL) {
        User u = userRepo.findByEmail(emaiL)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find the user by this email"));

        return userToUserRes.apply(u);
    }

    @Override
    public ResponseUser getUserById(String Id) {
        User u = userRepo.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Canoot find the user by this Id"));
        return userToUserRes.apply(u);
    }

    @Override
    public ResponseUser updateUser(UpdatingUserRequest user, String id) {
        // TODO Must validate the user trying to edit details is the user who is
        // currently logged in ...
        User u = userRepo.findById(id).orElseThrow(() -> new NotFoundException("User cannot be found "));

        u.setFirstName(user.firstName() == null ? u.getFirstName() : user.firstName());
        u.setLastName(user.lastName() == null ? u.getLastName() : user.lastName());
        u.setAddress((user.address() == null ? u.getAddress() : user.address()));
        u.setEmail(user.email() == null ? u.getEmail() : user.email());
        u.setMiddleName(user.middleName() == null ? u.getMiddleName() : user.middleName());
        u.setPhone(user.phone() == null ? u.getPhone() : user.phone());

        return userToUserRes.apply(userRepo.save(u));
    }

    @Override
    public ResponseUser savenonRegisteredUser(NonRegisteredRequestUser user) {

        Optional<User> u = userRepo.findByPhone(user.phone());

        if (u.isPresent()) {
            // TODO To be implement to do what if user has already been used our
            // application.....
            return userToUserRes.apply(u.get());
        }

        User nonregisteredUser = new User();
        nonregisteredUser.setEmail(user.email());
        nonregisteredUser.setPhone(user.phone());
        nonregisteredUser.setRegistered(false);

        return userToUserRes.apply(userRepo.save(nonregisteredUser));
    }

    @Override
    public ResponseEntity<?> getUserPHoto(String id) {
        String url = "http://PHOTOSTORAGE-SERVICE/photo";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("id", id).queryParam("photoOf",
                "user");
        try {
            ResponseEntity<Photo> entity = restTemplate.getForEntity(builder.toUriString(), Photo.class);

            return entity;
        } catch (HttpClientErrorException e) {

            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Handle the 404 Not Found error
                ResponseEntity<String> response = new ResponseEntity<>("Photo Not Found in userservice",
                        HttpStatus.NOT_FOUND);
                return response;
            }

            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Handle non-HTTP exceptions
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
