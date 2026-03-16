package com.boardgames.hub.game;

import com.boardgames.hub.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isPublic;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
