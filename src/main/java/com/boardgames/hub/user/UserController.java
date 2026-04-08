package com.boardgames.hub.user;

import com.boardgames.hub.group.GroupService;
import com.boardgames.hub.group.dto.GroupCreateRequest;
import com.boardgames.hub.group.dto.GroupResponse;
import com.boardgames.hub.user.dto.UserCreateRequest;
import com.boardgames.hub.user.dto.UserResponse;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse createdUser = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse user = userService.getUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/groups")
    public ResponseEntity<GroupResponse> createGroup(@PathVariable Long id, @Valid @RequestBody GroupCreateRequest request) {
        GroupResponse response = groupService.createGroup(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/groups")
    public ResponseEntity<Page<GroupResponse>> getUserGroups(@PathVariable Long id, Pageable pageable) {
        Page<GroupResponse> pageGroups = groupService.getUserGroups(id, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(pageGroups);
    }
}
