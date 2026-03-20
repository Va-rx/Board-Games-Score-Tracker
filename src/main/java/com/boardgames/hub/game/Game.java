package com.boardgames.hub.game;

import com.boardgames.hub.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @Column(nullable = false, unique = true, length = 100)
    private String name;
    @Column(nullable = false)
    private LocalDateTime createdAt;
//    @Column(nullable = false)
//    private boolean isPublic;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
