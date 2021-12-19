package me.chan.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    @FastTest
    @DisplayName("스터디 만들기")
    void create_new_study() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            new Study(1);
            Thread.sleep(300);
        });
        // TODO ThreadLocal

        Study study = new Study(1);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
                () -> assertTrue(study.getLimit() > 0, () -> "스터디 최대 참석 인원은 0명 이상이여야 한다.")
        );
        System.out.println("create");
    }

    @FastTest
    @DisplayName("조건에 따라 테스트 실행하기")
    void condition() {
        assumeTrue(1>2);
        Study study = new Study(1);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
                () -> assertTrue(study.getLimit() > 0, () -> "스터디 최대 참석 인원은 0명 이상이여야 한다.")
        );
    }

    @FastTest
    @DisplayName("조건에 따라 테스트 실행하기2")
    @EnabledOnOs(OS.WINDOWS)
    @EnabledOnJre(JRE.JAVA_11)
    void condition2() {
        assumingThat(true, () -> {
            System.out.println("condition2");
        });
        assumingThat(false, () -> {
            System.out.println("condition2 false");
        });
    }

    @FastTest
    @DisplayName("스터디 만들기 fast")
    void tagFast() {
        System.out.println("fast test");
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void tagSlow() {
        // edit configure -> tags 수정
        System.out.println("fast slow");
    }




    @Test
    @Disabled
    void create_new_study_again() {
        System.out.println("create1");
    }



    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }




}