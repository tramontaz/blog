package pro.chemodurov.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.chemodurov.blog.entity.Post;
import pro.chemodurov.blog.exception.PostNotFoundException;
import pro.chemodurov.blog.service.PostService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/all")
    public String getAllPostsPageable(
            Model model,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        int currentPage = (page == null) ? 1 : page;
        int pageSize = (size == null) ? 5 : size;

        Page<Post> posts = postService.showAllPageable(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("posts", posts);

        int totalPages = posts.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "/view/all-posts";
    }

    @GetMapping("/post")
    public String getPostById(@RequestParam("id") Long postId, Model model) throws PostNotFoundException {
        final Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "/view/post";
    }
}
