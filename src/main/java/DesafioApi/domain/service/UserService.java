package DesafioApi.domain.service;

import DesafioApi.Repository.UserRepository;
import DesafioApi.domain.model.User;
import DesafioApi.exception.ResourceAlreadyExistsException;
import DesafioApi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final String USER_ALREADY_EXISTS = "User already exists";
    private static final String USER_NOT_FOUND = "User Not Found";
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new ResourceAlreadyExistsException(USER_ALREADY_EXISTS);
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User findUserWithId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    public User deleteUserByID(Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                })
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    public User updateUserByID(Long id, User user) {
        return userRepository.findById(id)
                .map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    user1.setEmail(user.getEmail());
                    user1.setBirthday(user.getBirthday());
                    user1.setLogin(user.getLogin());
                    user1.setPassword(user.getPassword());
                    user1.setPhone(user.getPhone());
                    user1.setCars(user.getCars());
                    return userRepository.save(user1);
                })
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

}
