package com.example.user.service;
import com.example.user.entity.User;
import java.util.List;
public interface UserService {
    User addUser(User user);
    User updateUser(Long id, User user);
    User getUser(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
