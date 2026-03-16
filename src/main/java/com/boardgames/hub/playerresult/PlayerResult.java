package com.boardgames.hub.playerresult;

import com.boardgames.hub.game.Game;
import com.boardgames.hub.gamecolumn.GameColumn;
import com.boardgames.hub.session.Session;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
    private String playerName;
    @ManyToOne
    @JoinColumn(name = "game_column_id")
    private GameColumn column;
    private String value;
}
