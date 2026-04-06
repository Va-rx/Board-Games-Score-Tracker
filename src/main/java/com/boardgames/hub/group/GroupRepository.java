package com.boardgames.hub.group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    boolean existsByNameAndUserId(String name, Long userId);
    Page<Group> findAllByUserId(Long userId, Pageable pageable);
}
