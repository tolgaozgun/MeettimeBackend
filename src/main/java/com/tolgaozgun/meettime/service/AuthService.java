package com.tolgaozgun.meettime.service;

import java.util.Optional;

import com.tolgaozgun.meettime.dto.ReqChangePass;
import com.tolgaozgun.meettime.dto.UserDTO;
import com.tolgaozgun.meettime.dto.auth.ReqLogin;
import com.tolgaozgun.meettime.dto.auth.ReqRegister;
import com.tolgaozgun.meettime.entity.UserEntity;
import com.tolgaozgun.meettime.exception.BaseException;
import com.tolgaozgun.meettime.exception.UserAlreadyExistsException;
import com.tolgaozgun.meettime.exception.UserNotFoundException;
import com.tolgaozgun.meettime.exception.WrongPasswordException;
import com.tolgaozgun.meettime.repository.AuthRepository;
import com.tolgaozgun.meettime.response.ResRefreshToken;
import com.tolgaozgun.meettime.response.ResUserToken;
import com.tolgaozgun.meettime.security.JWTFilter;
import com.tolgaozgun.meettime.security.JWTUserService;
import com.tolgaozgun.meettime.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    public static int hashStrength = 10;

    @Autowired
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthRepository authRepository;
    private final JWTUserService jwtUserService;

    @Autowired
    private final JWTUtils jwtUtils;

    public ResUserToken login(ReqLogin user) throws BaseException {
        try {
            Optional<UserEntity> optionalUser = authRepository.findByEmail(user.getEmail());

            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException();
            }

            UserEntity dbUser = optionalUser.get();

            String hashedPassword = dbUser.getPassword();
            boolean passwordMatch = bCryptPasswordEncoder.matches(user.getPassword(), hashedPassword);

            if (!passwordMatch) {
                throw new WrongPasswordException();
            }

            final UserDetails userDetails = jwtUserService.loadUserByUsername(user.getEmail());
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            final String refreshToken = jwtUtils.createRefreshToken(userDetails);
            return new ResUserToken(dbUser, accessToken, refreshToken);
        } catch (BaseException exception) {
            throw exception;
        }
    }

    public ResRefreshToken refreshToken(String auth) throws Exception {
        try {
            String username = jwtUtils.extractRefreshUsername(JWTFilter.getTokenWithoutBearer(auth));

            final UserDetails userDetails = jwtUserService.loadUserByUsername(username);
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            return new ResRefreshToken(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserDTO registerUser(ReqRegister reqRegister) throws Exception {
        try {
            UserEntity userEntity = addUser(reqRegister);
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setId(userEntity.getId());
            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    protected UserEntity getCurrentUserEntity() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            Optional<UserEntity> optionalUserEntity = authRepository.findByEmail(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException();
            }

            return optionalUserEntity.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean changePassword(ReqChangePass reqChangePass) throws Exception {
        try {
            UserEntity dbUser = getCurrentUserEntity();
            String hashedPassword = dbUser.getPassword();
            String oldPassword = reqChangePass.getOldPassword();

            boolean passwordMatch = bCryptPasswordEncoder.matches(oldPassword, hashedPassword);

            if (!passwordMatch) {
                throw new WrongPasswordException();
            }

            String newPassword = reqChangePass.getNewPassword();
            String hashedNewPassword = bCryptPasswordEncoder.encode(newPassword);

            dbUser.setPassword(hashedNewPassword);
            boolean result = authRepository.updatePasswordByEmail(hashedNewPassword, dbUser.getEmail());

            return result;
        } catch (BaseException baseException) {
            throw baseException;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Adds user to the system
     * Do not return UserEntity directly, it contains password
     *
     * @param reqRegister request body
     * @return UserEntity
     * @throws Exception
     */
    public UserEntity addUser(ReqRegister reqRegister) throws Exception {
        try {
            String email = reqRegister.getEmail();
            String password = reqRegister.getPassword();

            boolean userExist = authRepository.existsByEmail(email);

            if (userExist) {
                throw new UserAlreadyExistsException("User with this email already exists");
            } else {
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(email);
                userEntity.setPassword(password);
                // generate uuid and hash password if user does not exist in the system

                return authRepository.save(userEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String encodePassword(String plainPassword) {
        try {
            return bCryptPasswordEncoder.encode(plainPassword);
        } catch (Exception e) {
            throw e;
        }
    }
}