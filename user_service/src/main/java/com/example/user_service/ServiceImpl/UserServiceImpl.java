package com.example.user_service.ServiceImpl;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.user_service.Models.User;
import com.example.user_service.Repo.UserRepo;
import com.example.user_service.Services.UserService;
import com.example.user_service.dto.RequestUser;
import com.example.user_service.dto.ResponseUser;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

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
                user.getLastName(), user.getPhone(), user.getEmail()).address(user.getAddress())
                .middleName(user.getMiddleName()).build();
        return u;
    };

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public ResponseUser saveUser(RequestUser requestUser) {
        return userToUserRes.apply(userRepo.save(userReqToUser.apply(requestUser)));
    }

}
