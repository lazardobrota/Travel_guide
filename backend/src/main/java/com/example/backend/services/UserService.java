package com.example.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entities.user.User;
import com.example.backend.entities.user.UserType;
import com.example.backend.repo.IUserRepository;
import com.example.backend.repo.IUserTypeRepository;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    IUserRepository userRepository;

    @Inject
    IUserTypeRepository userTypeRepository;

    private static final Algorithm algorithm = Algorithm.HMAC256("help");

    public String login(String email, String password) {

        User user = this.userRepository.getUserByEmail(email);
        String hashedPassword = DigestUtils.sha256Hex(password);
        if (user == null || !user.getPassword().equals(hashedPassword))
            return null;

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000);

        //subject must be unique
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .sign(algorithm);
    }

    public boolean isAuthorized(String token) throws Exception{
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token); //throws exception

        String email = decodedJWT.getSubject();

        return this.userRepository.getUserByEmail(email) != null;
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public UserType getUserTypeById(int id) {
        return userTypeRepository.getUserTypeById(id);
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public UserType addUserType(UserType userType) {
        return userTypeRepository.addUserType(userType);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
