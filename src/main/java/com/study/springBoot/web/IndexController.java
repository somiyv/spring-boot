package com.study.springBoot.web;

import com.study.springBoot.config.auth.LoginUser;
import com.study.springBoot.config.auth.dto.SessionUser;
import com.study.springBoot.service.PostsService;
import com.study.springBoot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공시 유저 정보를 세션에 저장하도록함.
        // 따라서, 로그인 성공시 세션에서 값을 추출해 낼 수 있음.
        // 반복되는 코드를 추출하기 위해 @LonginUser 어노테이션 작성.
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 세션에 저장된 값이 있을때만 userName을 저장할 수 있도록 조건 추가.
        Optional.ofNullable(user).ifPresent(u -> model.addAttribute("userName", u.getName()));

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