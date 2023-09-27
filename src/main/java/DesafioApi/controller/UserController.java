package DesafioApi.controller;

import DesafioApi.domain.model.User;
import DesafioApi.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.Optional;

import static DesafioApi.controller.validator.UserValidator.validate;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        validate(user);
        User userSaved = userService.save(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        Optional<User> user =
                Optional.ofNullable(userService.findUserWithId(id));
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> deleteUserById(@PathVariable Long id) {
        userService.deleteUserByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User updateUser) {
        validate(updateUser);
        User userUpdated = userService.updateUserByID(id, updateUser);

         return ResponseEntity.ok().body(userUpdated);

    }
}
