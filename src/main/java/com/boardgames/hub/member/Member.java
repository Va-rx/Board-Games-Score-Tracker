package com.boardgames.hub.member;

import com.boardgames.hub.group.Group;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members", uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_member_name_per_group",
                columnNames = {"group_id", "name"}
        )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    @Column(length = 20, nullable = false)
    private String name;
}
