package com.boardgames.hub.game;

import com.boardgames.hub.exception.DuplicateResourceException;
import com.boardgames.hub.game.dto.GameCreateRequest;
import com.boardgames.hub.game.dto.GameResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    @Transactional
    public GameResponse createGame(GameCreateRequest request) {
        log.info("Attempting to create a new game '{}'", request.getName());

        if (gameRepository.existsByName(request.getName())) {
            log.warn("Game with name '{}' already exists", request.getName());
            throw new DuplicateResourceException("Game with this name already exists");
        }

        Game game = Game.builder()
                .name(request.getName())
                .build();

        Game savedGame = gameRepository.save(game);

        log.info("Game {} has been created", savedGame.getId());

        return mapToResponse(savedGame);
    }

    @Transactional(readOnly = true)
    public Page<GameResponse> getGames(Pageable pageable) {
        log.info("Attempting to get Page for Games: {}", pageable.getPageNumber());

        Page<Game> gamePage = gameRepository.findAll(pageable);

        return gamePage.map(this::mapToResponse);
    }

    private GameResponse mapToResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .createdAt(game.getCreatedAt())
                .build();
    }
}
