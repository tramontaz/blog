package pro.chemodurov.blog.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import pro.chemodurov.blog.entity.Post;
import pro.chemodurov.blog.exception.PostNotFoundException;
import pro.chemodurov.blog.repository.PostRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PostServiceImplTest {

    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @Before
    public void init() {
        Post firstPost = new Post(
                1L,
                "Title of first post",
                "content first post",
                new Date(), null,
                "preview");
        Post secondPost = new Post("Title of second post", "content second post");
        Post thirdPost = new Post("Title of third post", "content third post");
        Post fourthPost = new Post("Title of fourth post", "content fourth post");
        Post fifthPost = new Post("Title of fifth post", "content fifth post");
        Post sixthPost = new Post("Title of sixth post", "content sixth post");
        Post seventhPost = new Post("Title of seventh post", "content seventh post");
        Post eightPost = new Post("Title of eight post", "content eight post");
        Post ninthPost = new Post("Title of ninth post", "content ninth post");
        Post tenthPost = new Post("Title of tenth post", "content tenth post");
        Post eleventhPost = new Post("Title of eleventh post", "content eleventh post");
        Post twelfthPost = new Post("Title of twelfth post", "content twelfth post");

        Optional<Post> firstPostOptional = Optional.of(firstPost);

        List<Post> posts = new ArrayList<>();
        posts.add(firstPost);
        posts.add(secondPost);
        posts.add(thirdPost);
        posts.add(fourthPost);
        posts.add(fifthPost);
        posts.add(sixthPost);
        posts.add(seventhPost);
        posts.add(eightPost);
        posts.add(ninthPost);
        posts.add(tenthPost);
        posts.add(eleventhPost);
        posts.add(twelfthPost);

        when(postRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(posts));
        when(postRepository.findById(1L)).thenReturn(firstPostOptional);
        postService = new PostServiceImpl(postRepository);
    }

    @Test
    public void showAllPageable() {
        assertNotNull(postService.showAllPageable(Pageable.unpaged()));
        assertEquals(12L, postService.showAllPageable(Pageable.unpaged()).getTotalElements());
    }

    @Test
    public void getPost() throws PostNotFoundException {
        assertNotNull(postService.getPost(1L).getTitle());
        assertEquals("Title of first post" , postService.getPost(1L).getTitle());
    }

    @Test(expected = PostNotFoundException.class)
    public void getPost_ShouldThrowPostNotFoundException() throws PostNotFoundException {
        postService.getPost(99L);
    }
}