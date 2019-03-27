package pro.chemodurov.blog.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import pro.chemodurov.blog.AbstractIntegrationTest;
import pro.chemodurov.blog.entity.Post;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Sql(scripts = "classpath:db/scripts/create_table_post.sql")
    public void saveAndGetPost() {
        Post post = new Post("Test title!", "Test content!");
        postRepository.save(post);

        List<Post> postsFromDb = postRepository.findAll();

        assertNotEquals(null, postsFromDb.get(0));
        assertEquals(post.getTitle(), postsFromDb.get(0).getTitle());
        assertEquals(post.getContent(), postsFromDb.get(0).getContent());
    }

    @Test
    @Sql(scripts = "classpath:db/scripts/create_table_post.sql")
    @Sql(scripts = "classpath:db/scripts/find_post_with_title_like.sql")
    public void findByTitleIsLike() {
        List<Post> result = postRepository.findByTitleIsLikeIgnoreCase("%лошадь%");
        assertEquals(2, result.size());
    }

    @Test
    @Sql(scripts = "classpath:db/scripts/create_table_post.sql")
    @Sql(scripts = "classpath:db/scripts/find_post_with_title_like.sql")
    public void getAllPageable() {
        assertEquals(10L, postRepository.findAll(PageRequest.of(0, 10)).stream().count());
    }
}