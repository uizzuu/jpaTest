package com.my.jpaTest.quiz;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import com.my.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuizTest {
    @Autowired
    UsersRepository repository;

    @Test
    @DisplayName("Given/When/Then으로 테스트하기")
    @Transactional
    void assertThatTest() {
        // 신규 데이터 추가 테스트
        // Given
        Users jin = Users.builder()
                .name("안유진")
                .email("jin@korea.com")
                .gender(Gender.Female)
                .likeColor("Red")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        // When
        repository.save(jin);
        // Then
        Users result = repository.findByName("안유진").get(0);
        // 검사
        Assertions.assertThat(result.getEmail()).isEqualTo(jin.getEmail());
    }

    // 과제
    @Test
    @DisplayName("Quiz1")
    // 문제 1. 여성의 이름 중 "w"또는 "m"을 포함하는 자료를 검색하시오.
    // select * from users where name like '%w%' and name like '%m%';
    void Quiz1() {
        repository
                .findByGenderAndNameContainsOrGenderAndNameContains
                        (Gender.Female, "w", Gender.Female, "m")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("Quiz2")
        // 문제 2. 이메일에 net을 포함하는 데이터 건수를 출력하시오.
    void Quiz2() {
        long count = repository.findByEmailContains("net")
                .stream()
                .count();
        System.out.println("데이터 수 : " + count);
    }

    @Test
    @DisplayName("Quiz3")
    // 문제 3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 "J"인 자료를 출력하시오.
    void Quiz3() {
        LocalDateTime lastMonth = LocalDate.now()
                .minusMonths(1)
                .atStartOfDay();
        repository.findByUpdatedAtAfterAndNameStartingWith
                        (lastMonth, "J")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("Quiz4")
    // 문제 4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력하시오.
    void Quiz4() {
        repository.findTop10ByOrderByCreatedAtDesc()
                .forEach( x -> System.out.println(
                        "[ID] " + x.getId() +
                        " [이름] " + x.getName() +
                        " [성별] " + x.getGender() +
                        " [생성일] " + x.getCreatedAt()));
    }
    // 문제 5. "Red"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력하시오.
    // (예, apenley2@tripod.com  → apenley2)
    // 문제 6. 갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.
    // 문제 7. 이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력하시오.
    // 문제 8. 좋아하는 색상(Pink)별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력하시오.
    // 문제 9. 전체 자료를 가장 최근 입력한 자료 순으로 정렬 및 페이징 처리하고 한 페이지당 10건씩 출력하되,
    // 그 중 1번째 페이지를 출력하시오.
    // 문제10. 남성 자료를 ID의 내림차순으로 정렬한 후 한페이당 3건을 출력하되 그 중 2번째 페이지 자료를  출력하시오.
    // 문제11. 지난달의 모든 자료를 검색하여 출력하시오.
}
