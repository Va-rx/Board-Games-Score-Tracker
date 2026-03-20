package com.boardgames.hub.group;

import com.boardgames.hub.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "groups", uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_group_name_per_user",
                columnNames = {"user_id", "name"}
        )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, length = 50)
    private String name;
}
