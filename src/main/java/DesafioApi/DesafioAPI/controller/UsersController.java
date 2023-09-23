package DesafioApi.DesafioAPI.controller;

import com.DesafioApi.springswaggercodegen3.api.UsersApi;
import com.DesafioApi.springswaggercodegen3.model.UserRequest;
import com.DesafioApi.springswaggercodegen3.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController implements UsersApi {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApi.super.getRequest();
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        return UsersApi.super.createUser(userRequest);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return UsersApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return UsersApi.super.getAllUsers();
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        return UsersApi.super.getUserById(id);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(String id, UserRequest userRequest) {
        return UsersApi.super.updateUser(id, userRequest);
    }
}
