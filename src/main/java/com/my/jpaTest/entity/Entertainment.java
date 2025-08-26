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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entertainment {
    @Id
    private String e_id;
    private String e_name;

    @OneToMany(mappedBy = "entertainment"
            , cascade = CascadeType.PERSIST
            , fetch = FetchType.EAGER)
    @Builder.Default
    private List<Girlgroup> girlgroupList = new ArrayList<>();
}
