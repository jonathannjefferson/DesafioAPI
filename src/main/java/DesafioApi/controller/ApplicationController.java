package DesafioApi.controller;

import DesafioApi.converter.ApplicationConverter;
import com.DesafioApi.springswaggercodegen3.api.ApiApi;

import com.DesafioApi.springswaggercodegen3.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationController implements ApiApi {

    private Map<String, UserResponse> users = new HashMap<>();
    private Map<String, CarResponse> cars = new HashMap<>();
    private ApplicationConverter converter;

    @Override
    public ResponseEntity<TokenResponse> generateToken(TokenRequest tokenRequest) {
        //TODO: criar logica de geracao do token
        return ApiApi.super.generateToken(tokenRequest);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> allUsers = this.users.values()
                .stream()
                .toList();
        return ResponseEntity.ok(allUsers);
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest user) {
        var newUser = converter.toUserResponse(user);
        users.put(newUser.getId(), newUser);
        return ResponseEntity.ok(newUser);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        var user = users.get(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String id) {
        UserResponse user = users.remove(id);
        return user != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UserResponse> updateUserById(String id, UserRequest userRequest) {
        var userById = users.get(id);

        if (userById != null) {
            var newUser = converter.toUserResponse(userRequest);
            newUser.setId(userById.getId());
            users.put(id, newUser);
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UserLogged> userInfoLogged() {
        //TODO: criar logica de usuario logado
        return ApiApi.super.userInfoLogged();
    }

    @Override
    public ResponseEntity<List<CarResponse>> getAllCarsByUserLogged() {
        return ApiApi.super.getAllCarsByUserLogged();
    }

    @Override
    public ResponseEntity<Void> registerNewCarForLoggedUser(CarRequest carRequest) {
        return ApiApi.super.registerNewCarForLoggedUser(carRequest);
    }

    @Override
    public ResponseEntity<CarResponse> getCarByUserLogged(String id) {
        return ApiApi.super.getCarByUserLogged(id);
    }

    @Override
    public ResponseEntity<Void> deleteCarByUserLogged(String id) {
        return ApiApi.super.deleteCarByUserLogged(id);
    }

    @Override
    public ResponseEntity<Void> updateCarByUserLogged(String id, CarRequest carRequest) {
        return ApiApi.super.updateCarByUserLogged(id, carRequest);
    }


}
