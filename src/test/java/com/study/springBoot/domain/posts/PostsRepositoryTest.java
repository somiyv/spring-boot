package com.study.springBoot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없이 이 어노테이션을 사용할 경우 h2데이터베이스를 자동으로 실행시켜줌.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    /**
     * 단위 테스트가 끝날때마다 수행되는 메소드 지정.
     * 보통 배포전, 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용.
     */
    @After
    public void clean() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts posts = Posts.builder().title(title).content(content).author("somi@test.com").build();
        postsRepository.save(posts);

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts result = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(result.getTitle());
        assertThat(posts.getContent()).isEqualTo(result.getContent());
    }

}
