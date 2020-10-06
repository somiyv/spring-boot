package com.study.springBoot.web;

import com.study.springBoot.service.PostsService;
import com.study.springBoot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때
        // 앞의 경로(src/main/resources/templates)와 뒤의 파일 확장자(.mustache)는 자동으로 지정됨.
        // 그리고 이것을 View Resolver가 처리
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}