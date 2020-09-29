package com.study.springBoot.service;


import com.study.springBoot.domain.posts.PostsRepository;
import com.study.springBoot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성.
@Service
public class PostsService {

    /**
     * 스프링에서 Bean을 주입받는 방식 3가지
     *  1. @Autowired
     *  2. setter
     *  3. 생성자 -> 권장하는 방식.
     */
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
