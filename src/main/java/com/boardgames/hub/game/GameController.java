package com.boardgames.hub.game;


import com.boardgames.hub.game.dto.GameCreateRequest;
import com.boardgames.hub.game.dto.GameResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@Valid @RequestBody GameCreateRequest request) {
        GameResponse game = gameService.createGame(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @GetMapping
    public ResponseEntity<Page<GameResponse>> getGames(Pageable pageable) {
        Page<GameResponse> pageGame = gameService.getGames(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(pageGame);
    }
}
