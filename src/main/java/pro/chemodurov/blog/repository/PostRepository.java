package pro.chemodurov.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.chemodurov.blog.entity.Post;

import java.util.List;

@Repository()
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleIsLikeIgnoreCase(String partOfTitle);
    Page<Post> findAllByOrderByDateCreatedDesc(Pageable pageable);
}
