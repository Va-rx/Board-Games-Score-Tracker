package com.boardgames.hub.user;

import com.boardgames.hub.exception.DuplicateResourceException;
import com.boardgames.hub.user.dto.UserCreateRequest;
import com.boardgames.hub.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        log.info("Attempting to create a new user with email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("User not created - email already exists: {}", request.getEmail());
            throw new DuplicateResourceException("User with this email already exists");
        }
        if (userRepository.existsByNickname(request.getNickname())) {
            log.warn("User not created - nickname already exists: {}", request.getNickname());
            throw new DuplicateResourceException("User with this nickname already exists");
        }

        //TODO: Add hashing password
        User newUser = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();

        User savedUser = userRepository.save(newUser);

        log.info("User created with ID: {}", savedUser.getId());

        return mapToResponse(savedUser);
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with an ID: " + id));

        return mapToResponse(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
