package com.boardgames.hub.preset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresetRepository extends JpaRepository<Preset, Long> {
}
