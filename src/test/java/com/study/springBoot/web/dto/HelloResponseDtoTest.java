package com.study.springBoot.web.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Junit에서 자동으로 라이브러리 등록 해줌.
 * Junit의 기본 assertThat이 아닌 assertj의 assertThat 사용.
 * coreMatchers와 달리 추가적으로 라이브러리가 필요하지 않다. (junit의 assertThatdms is()와 같이 CoreMatchers와 같은 라이브러리가 필요.)
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloResponseDtoTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

    @Test
    public void hell_dto가_리턴된다() throws Exception {
        String name = "김소미";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
