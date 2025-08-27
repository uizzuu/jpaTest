package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Girlgroup {
    @Id
    private String g_id;
    private String g_name;

    @ManyToOne
    @JoinColumn(name = "e_id")
    Entertainment entertainment;

    @OneToMany(mappedBy = "girlgroup"
            , cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
            , fetch = FetchType.EAGER)
    @Builder.Default
    private List<IdolMember> idolMemberList = new ArrayList<>();
}
