package pro.chemodurov.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.chemodurov.blog.entity.Post;
import pro.chemodurov.blog.exception.PostNotFoundException;
import pro.chemodurov.blog.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;


    @Override
    public Page<Post> showAllPageable(Pageable pageable) {
        return postRepository.findAllByOrderByDateCreatedDesc(pageable);
    }

    @Override
    public Post getPost(Long id) throws PostNotFoundException {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException(String.format("Post with id: %d not found!", id)));
    }
}
