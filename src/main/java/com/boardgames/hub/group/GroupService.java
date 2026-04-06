package com.boardgames.hub.group;

import com.boardgames.hub.exception.DuplicateResourceException;
import com.boardgames.hub.exception.ResourceNotFoundException;
import com.boardgames.hub.group.dto.GroupCreateRequest;
import com.boardgames.hub.group.dto.GroupResponse;
import com.boardgames.hub.group.dto.GroupUpdateRequest;
import com.boardgames.hub.user.User;
import com.boardgames.hub.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<GroupResponse> getUserGroups(Long userId, Pageable pageable) {
        log.info("Attempting to get all Groups for User {}", userId);

        if (!userRepository.existsById(userId)) {
            log.warn("User {} does not exist", userId);
            throw new ResourceNotFoundException("This User does not exist");
        }

        Page<Group> groupPage = groupRepository.findAllByUserId(userId, pageable);

        return groupPage.map(group -> new GroupResponse(group.getId(), group.getName()));
    }

    @Transactional
    public GroupResponse createGroup(Long userId, GroupCreateRequest request) {
        log.info("Attempting to create Group for User {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("User {} does not exist", userId);
                    return new ResourceNotFoundException("This User does not exist");
                });

        if (groupRepository.existsByNameAndUserId(request.getName(), userId)) {
            log.warn("Group name '{}' for User {} already exists", request.getName(), userId);
            throw new DuplicateResourceException("Group with this name already exists");
        }

        Group newGroup = Group.builder()
                .user(user)
                .name(request.getName())
                .build();

        Group savedGroup = groupRepository.save(newGroup);

        return new GroupResponse(savedGroup.getId(), savedGroup.getName());
    }

    @Transactional
    public GroupResponse updateGroup(Long groupId, GroupUpdateRequest request) {
        log.info("Attempting to update Group {}", groupId);

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> {
                    log.warn("Group {} does not exist", groupId);
                    return new ResourceNotFoundException("This Group does not exist");
                });

        if (request.getName() != null && !request.getName().equals(group.getName())) {
            Long ownerId = group.getUser().getId();
            if (groupRepository.existsByNameAndUserId(request.getName(), ownerId)) {
                log.warn("Group name '{}' for User {} already exists", request.getName(), ownerId);
                throw new DuplicateResourceException("Group with this name already exists");
            }

            group.setName(request.getName());

            log.info("Group {} name updated to {}", groupId, request.getName());
        }
        // New variables in future
        return new GroupResponse(group.getId(), group.getName());
    }

    @Transactional
    public void deleteGroup(Long groupId) {
        log.info("Attempting to delete Group {}", groupId);

        if (!groupRepository.existsById(groupId)) {
            log.warn("Group {} does not exist", groupId);
            throw new ResourceNotFoundException("This group does not exist");
        }

        groupRepository.deleteById(groupId);

        log.info("Group {} has been deleted", groupId);
    }
}
