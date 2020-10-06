package com.study.springBoot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 인터페이스 생성 후, JpaRepository<entity클래스, pk타입>을 상속하면 기본적인 crud 메소드가 자동 생성.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa에서 제공하지 않는 메소드는 @Query를 사용하여 직접 쿼리를 작성할 수 있다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
