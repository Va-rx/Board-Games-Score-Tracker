package com.boardgames.hub.group;

import com.boardgames.hub.group.dto.GroupResponse;
import com.boardgames.hub.group.dto.GroupUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private GroupService groupService;

//    @GetMapping("/{id}")
//    public ResponseEntity<GroupResponse> getUserGroups(Long id) {
//        //groupService.
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupUpdateRequest request) {
        GroupResponse response = groupService.updateGroup(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
