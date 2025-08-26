package com.my.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    private String memberId;
    private String name;

    // 팀 정보를 갖는 연관관계 구성
    // java에서의 연관관계 핵심 : 서로 정보를 나눠갖는다
    // ManyToOne 에는 JoinColumn(name = "teamId")이 항상 따라다님
    // 반대로 OneToMany team에는 list로 member내용 알고있으니까 JoinColumn 필요없음
    // OneToMany(mappedBy = "member") 의 형식으로 씀
    @ManyToOne
    @JoinColumn(name = "teamId") // 외래키(foreign key)
    private Team team;
}
