package com.boardgames.hub.gamecolumn;

import com.boardgames.hub.game.Game;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_columns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    private String name;
    private ColumnType type;
}
