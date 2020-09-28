package com.study.springBoot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter // entity 클래스 에서는 setter를 만들지 않는다. -> 언제 어떻게 변경되는지 구분할 수 없어 복잡해 질 수 있음.
@NoArgsConstructor
@Entity // 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭한다.
public class Posts {

    @Id // 해당 테이블의 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk생성규칙. 이 옵션을 추가해야만 auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false) // 굳이 선언하지 않아도 해당 클라스의 필드는 모두 칼럼이 된다. 기본값 외 추가할 옵션이 있을때 사용.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
