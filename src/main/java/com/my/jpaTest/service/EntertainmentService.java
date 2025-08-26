package com.my.jpaTest.service;

import com.my.jpaTest.entity.Entertainment;
import com.my.jpaTest.entity.Girlgroup;
import com.my.jpaTest.entity.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EntertainmentService {
    @Autowired
    EntityManager em;

    public void initData() {
        // 엔터테인먼트
        Entertainment starship = Entertainment.builder()
                .e_id("starship")
                .e_name("스타쉽")
                .build();
        Entertainment yg = Entertainment.builder()
                .e_id("yg")
                .e_name("와이지")
                .build();
        // 걸그룹
        Girlgroup ive = Girlgroup.builder()
                .g_id("ive")
                .g_name("아이브")
                .build();
        Girlgroup blackPink = Girlgroup.builder()
                .g_id("blackPink")
                .g_name("블랙핑크")
                .build();
        // 걸그룹에 엔터테인먼트 할당
        ive.setEntertainment(starship);
        blackPink.setEntertainment(yg);
        // 걸그룹리스트에 추가
        starship.getGirlgroupList().add(ive);
        yg.getGirlgroupList().add(blackPink);
        // 멤버
        IdolMember ahn = IdolMember.builder()
                .m_id("안유진")
                .m_name("유진")
                .build();
        IdolMember jang = IdolMember.builder()
                .m_id("장원영")
                .m_name("원영")
                .build();
        IdolMember jenny = IdolMember.builder()
                .m_id("제니")
                .m_name("째니")
                .build();
        IdolMember jisu = IdolMember.builder()
                .m_id("지수")
                .m_name("지수다")
                .build();
        // 멤버에 걸그룹 할당
        ahn.setGirlgroup(ive);
        jang.setGirlgroup(ive);
        jenny.setGirlgroup(blackPink);
        jisu.setGirlgroup(blackPink);
        // 멤버리스트에 추가
        ive.getIdolMemberList().add(ahn);
        ive.getIdolMemberList().add(jang);
        blackPink.getIdolMemberList().add(jenny);
        blackPink.getIdolMemberList().add(jisu);
        // 퍼시스트
        em.persist(starship);
        em.persist(yg);
    }
}
