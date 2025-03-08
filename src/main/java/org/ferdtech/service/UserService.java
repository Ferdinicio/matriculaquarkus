package org.ferdtech.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.ferdtech.exception.UserNotFoundException;
import org.ferdtech.repository.UserRepository;
import org.ferdtech.entity.UserEntity;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userRepository.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return userRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(UUID userId) {
        return (UserEntity) userRepository.findByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID userId, UserEntity userEntity) {
        var user = findById(userId);

        user.setUsername(userEntity.getUsername());

        user.setUsertype(userEntity.getUsertype());

        userRepository.persist(user);

        return user;
    }

    public void deleteById(UUID userId) {
        var user = findById(userId);
        userRepository.deleteById(user.getUserId());
    }
}