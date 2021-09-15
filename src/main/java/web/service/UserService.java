package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    User findUserByLogin(String login);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(long id);
}
