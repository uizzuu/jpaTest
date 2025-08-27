package com.my.jpaTest.service;

import com.my.jpaTest.entity.Girlgroup;
import com.my.jpaTest.entity.IdolMember;
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
class EntertainmentServiceTest {
    @Autowired
    EntertainmentService entertainmentService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("initData 실행")
    public void initData() {
        // entertainmentService.initData();
    }

    @Test
    @DisplayName("1. 지수가 속한 걸그룹 이름과 엔터테인먼트 회사 이름 출력하기")
    public void print1() {
        IdolMember jisu = em.find(IdolMember.class, "지수");
        System.out.println(jisu.getGirlgroup().getG_name());
        System.out.println(jisu.getGirlgroup().getEntertainment().getE_name());
    }

    @Test
    @DisplayName("2. blackPink 멤버 리스트 출력하기.")
    public void print2() {
        Girlgroup bp = em.find(Girlgroup.class, "blackPink");
        bp.getIdolMemberList().forEach(
                x -> System.out.println(x.getM_id())
        );
    }
}