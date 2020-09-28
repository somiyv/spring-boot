package com.study.springBoot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 인터페이스 생성 후, JpaRepository<entity클래스, pk타입>을 상속하면 기본적인 crud 메소드가 자동 생성.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
