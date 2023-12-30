package com.tolgaozgun.meettime.controller;

import com.tolgaozgun.meettime.dto.ReqChangePass;
import com.tolgaozgun.meettime.dto.UserDTO;
import com.tolgaozgun.meettime.dto.auth.ReqLogin;
import com.tolgaozgun.meettime.dto.auth.ReqRegister;
import com.tolgaozgun.meettime.entity.UserEntity;
import com.tolgaozgun.meettime.exception.BaseException;
import com.tolgaozgun.meettime.exception.ExceptionLogger;
import com.tolgaozgun.meettime.response.ResUserToken;
import com.tolgaozgun.meettime.response.Response;
import com.tolgaozgun.meettime.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path="login")
    public ResponseEntity<Object> login(@Valid @RequestBody ReqLogin reqLogin) {
        try {
            ResUserToken token = authService.login(reqLogin);
            return Response.create("Login is successful", HttpStatus.OK, token);
        } catch (BaseException baseException) {
            return Response.create(baseException);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register")
    public ResponseEntity<Object> register(@Valid @RequestBody ReqRegister reqRegister) {
        try {
            UserDTO user = authService.registerUser(reqRegister);
            return Response.create("Account is created", HttpStatus.OK, user);
        } catch (BaseException baseException) {
            return Response.create(baseException);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "change-password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ReqChangePass reqChangePass) {
        try {
            Boolean result = authService.changePassword(reqChangePass);
            return Response.create("Password is changed", HttpStatus.OK, result);
        } catch (BaseException baseException) {
            return Response.create(baseException);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/request-reset")
//    public ResponseEntity<Object> requestResetPassword(@Valid @RequestBody QResetPassword resetPasswordInfo) {
//        try {
//            ResetToken resetToken = accountService.generateEmailToken(resetPasswordInfo.getEmail());
//            emailService.sendPasswordResetEmail(resetPasswordInfo.getEmail(), resetToken);
//            return Response.create("Password reset email is sent", HttpStatus.OK);
//        } catch (Exception e) {
//            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/test-reset")
//    public ResponseEntity<Object> requestResetPasswordWithoutEmail(@Valid @RequestBody QResetPassword resetPasswordInfo) {
//        try {
//            ResetToken resetToken = accountService.generateEmailToken(resetPasswordInfo.getEmail());
//            return Response.create("Reset token is generated", HttpStatus.OK, resetToken);
//        } catch (Exception e) {
//            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/validate-reset")
//    public ResponseEntity<Object> validateResetPassword(@Valid @RequestBody QResetPasswordVerification verificationInfo) {
//        try {
//            boolean tokenValid = accountService.validateResetCode(verificationInfo.getEmail(), verificationInfo.getResetCode(), false);
//            if (!tokenValid) {
//                return Response.create("Invalid password reset token", HttpStatus.BAD_REQUEST);
//            }
//            return Response.create("Valid password reset token", HttpStatus.OK);
//        } catch (BaseException baseException) {
//            return Response.create(ExceptionLogger.log(baseException), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/reset-password")
//    public ResponseEntity<Object> saveResetPassword(@Valid @RequestBody QResetPasswordSave passwordInfo) {
//        try {
//            boolean tokenValid = authService.validateResetCode(passwordInfo.getEmail(), passwordInfo.getResetCode(), true);
//            if (!tokenValid) {
//                return Response.create("Invalid password reset token", HttpStatus.BAD_REQUEST);
//            }
//            boolean passwordChanged = authService.setPassword(passwordInfo.getEmail(), passwordInfo.getNewPassword());
//            if (!passwordChanged) {
//                return Response.create("Failed to change the password", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            return Response.create("Password is changed", HttpStatus.OK);
//        } catch (Exception e) {
//            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



}