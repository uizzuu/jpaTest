package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("1. findByName 테스트")
    void findByNameTest() {
        String name = "Vilhelmina Valder";
        usersRepository
                .findByName(name)
                .stream()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("2. pink 색상 상위 3개 찾기 테스트")
    void findByColor() {
        String color = "Pink";
        usersRepository
                .findTop3ByLikeColor(color)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("3. 성별이 여자이고 좋아하는 색상이 Red인 자료 찾기 테스트")
    void findByGenderAndLikeColor() {
        usersRepository
                .findByGenderAndLikeColor(Gender.Female, "Red")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("4. 어제 이후 모든 자료 검색 테스트")
    void findByCreatedAtAfter() {
        LocalDateTime today = LocalDate.now()
                .minusDays(2L).atTime(23,59,59);
        System.out.println("today : "+ today);
        usersRepository
                .findByCreatedAtAfter(today)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("5. 최근 한 달 자료 찾기(오늘 포함)")
    void findByCreatedAtBetween() {
        // 기준일 설정 (한 달 이전의)
        LocalDateTime start = LocalDate.now()
                .minusMonths(1L)
                .atStartOfDay();
        LocalDateTime end = LocalDate.now()
                .plusDays(1L)
                .atStartOfDay();
        System.out.println("start : " + start);
        System.out.println("end : " + end);
        usersRepository.findByCreatedAtBetween(start, end)
                .forEach(x -> System.out.println(x));
    }
}