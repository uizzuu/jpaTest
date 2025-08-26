package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Parent;
import com.my.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RelationTestServiceTest {
    @Autowired
    RelationTestService relationTestService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("MemberAndTeam Insert Test")
    void insertTest() {
        // relationTestService.insertMemberAndTeam();
    }

    // 장원영(key: jang)의 팀이름 검색
    // 연관관계가 없으면 한 없이 찾아들어가서 원하는 자료를 찾아야 함.
    // 따라서 외래키 걸어줘야함 (연관관계 매핑)
    @Test
    @DisplayName("멤버 아이디로 팀 이름 찾기 테스트")
    void findTeamName() {
        // 멤버에서 memberId 찾기
        Member jang = em.find(Member.class, "jang");
        // 찾아온 멤버의 teamId team 에서 검색
        Team team = em.find(Team.class, jang.getTeam().getTeamName());
        System.out.println("Team Name : " + team);
    }

    @Test
    @DisplayName("단방향 연관관계 설정 후 팀 이름  찾기")
    void findTeam() {
        // 저장 서비스 호출
        // relationTestService.insertMemberAndTeam();
        // memberId 정보 가져오기
        Member jang = em.find(Member.class, "jang");
        // 팀 정보 출력
        System.out.println(jang.getTeam().getTeamName());
    }

    @Test
    @DisplayName("양방향 연관관계 설정 후 저장하기")
    void saveBothData() {
        // relationTestService.insertBothRelation();
    }

    @Test
    @DisplayName("씨름팀 멤버 출력")
    void 씨름팀멤버출력() {
        Team team = em.find(Team.class, "ssirum");
        List<Member> members = team.getMemberList();
        for (Member m : members) {
            System.out.println(m.getName());
        }
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    void persistTest() {
        relationTestService.saveChildren();
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() {
        relationTestService.deleteParent();
    }
}