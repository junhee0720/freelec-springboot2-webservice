package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void testPostsSave_Load(){
        // given
        String title = "안녕하세요";
        String content = "테스트 본문 테스트";

        Posts savePosts = Posts.builder()
                .title(title)
                .content(content)
                .auther("junhee0720@gmail.com")
                .build();

        postsRepository.save(savePosts);
        
        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts post = postsList.get(0);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }


}
