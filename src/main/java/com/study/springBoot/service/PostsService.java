package com.study.springBoot.service;


import com.study.springBoot.domain.posts.Posts;
import com.study.springBoot.domain.posts.PostsRepository;
import com.study.springBoot.web.dto.PostsListResponseDto;
import com.study.springBoot.web.dto.PostsResponseDto;
import com.study.springBoot.web.dto.PostsSaveRequestDto;
import com.study.springBoot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성.
@Service
public class PostsService {

    /**
     * 스프링에서 Bean을 주입받는 방식 3가지
     * 1. @Autowired
     * 2. setter
     * 3. 생성자 -> 권장하는 방식.
     */
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }


}
