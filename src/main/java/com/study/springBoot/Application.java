package com.study.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 스프링 부트의 자동설정. 항상 프로젝트의 최상단에 위치해야함.

/**
 * @ComponentScan
 * @EnableAutoConfiguration
 * @Configuration 3가지 성격의 어노테이션을 포함함.
 *
 */
@SpringBootApplication
@EnableJpaAuditing // Jpa Auditing 활성화
public class Application {
    public static void main(String[] args) {
        /**
         * 내장된 WAS 실행. 고로 스프링부트로 만들어진 jar파일만 실행하면 됨.
         * 외부 WAS도 사용 가능하나 내장 WAS사용을 권장 -> 언제 어디서나 같은 환경에서 스프링부트를 배포할 수 있으므로.
         */
        SpringApplication.run(Application.class, args);
    }
}
