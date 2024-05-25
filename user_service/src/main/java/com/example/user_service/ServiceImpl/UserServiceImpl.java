package com.example.user_service.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.user_service.Models.User;
import com.example.user_service.Repo.UserRepo;
import com.example.user_service.Services.UserService;
import com.example.user_service.dto.NonRegisteredRequestUser;
import com.example.user_service.dto.RequestUser;
import com.example.user_service.dto.ResponseUser;
import com.example.user_service.dto.UpdatingUserRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    // Constructor injection of userRepo....
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private Function<RequestUser, User> userReqToUser = (userReq) -> {

        User u = new User();
        u.setFirstName(userReq.firstName());
        u.setLastName(userReq.lastName());
        u.setMiddleName(userReq.middleName());
        u.setAddress((userReq.address()));
        u.setEmail(userReq.email());
        u.setPassword(userReq.password());
        u.setPhone(userReq.phone());
        u.setRegistered(true);
        return u;

    };

    private Function<User, ResponseUser> userToUserRes = (user) -> {

        ResponseUser u = new ResponseUser.ResponseUserBuilder(user.getFirstName(),
                user.getLastName(), user.getPhone(), user.getEmail())
                .address(user.getAddress())
                .middleName(user.getMiddleName())
                .build();

        return u;
    };

    @Override
    public List<ResponseUser> getAllUsers() {

        return userRepo.findAll().stream().map(
                (user) -> userToUserRes.apply(user)).toList();
    }

    @Override
    public ResponseUser saveUser(RequestUser requestUser) {
        return userToUserRes.apply(userRepo.save(userReqToUser.apply(requestUser)));
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

}
