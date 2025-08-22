package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                .minusDays(2L).atTime(23, 59, 59);
        System.out.println("today : " + today);
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
                .atTime(23, 59, 59);
        System.out.println("start : " + start);
        System.out.println("end : " + end);
        usersRepository.findByCreatedAtBetween(start, end)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("6. 여러가지 좋아하는 색상 검색하기")
    void findByLikeColorIn() {
        // 검색하고자 하는 색상의 리스트 만들기
        usersRepository.findByLikeColorIn(Lists.newArrayList("Red", "Pink"))
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("7. id가 91이상인 자료 검색하기")
    void findByIdGreaterThanEqual() {
        usersRepository.findByIdGreaterThanEqual(91L)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("8.1. 이름이 D로 시작하는 데이터 전체 출력")
    void findByNameStartingWith() {
        usersRepository.findByNameStartingWith("D")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("8.2. 이름이 s로 끝나는 데이터 전체 출력")
    void findByNameEndingWith() {
        usersRepository.findByNameEndingWith("s")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("8.3.1. (Contains) email에 org를 포함하는 데이터")
    void findByEmailContains() {
        usersRepository.findByEmailContains("org")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("8.3.2. (Like) email에 org를 포함하는 데이터")
    void findByEmailLike() {
        usersRepository.findByEmailLike("org")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("9. id 1~10까지를 이름의 내림차순으로 정렬")
    void findByIdBetweenOrderByNameDesc() {
        usersRepository.findByIdBetweenOrderByNameDesc(1L, 10L)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("퀴즈")
    void findTop10ByLikeColorOrderByGenderAscCreatedAtDesc() {
        usersRepository.findTop10ByLikeColorOrderByGenderAscCreatedAtDesc("Orange")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("10")
    void findByLikeColor() {
        usersRepository.findByLikeColor("Orange",
                                Sort.by(Sort.Order.asc("gender"),
                                            Sort.Order.desc("createdAt")))
                        .forEach(x -> System.out.println(x));
    }
}