package pro.chemodurov.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.chemodurov.blog.entity.Post;
import pro.chemodurov.blog.exception.PostNotFoundException;


public interface PostService {
    Page<Post> showAllPageable(Pageable pageable);
    Post getPost(Long id) throws PostNotFoundException;
}
