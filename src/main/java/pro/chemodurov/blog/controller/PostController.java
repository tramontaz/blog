package pro.chemodurov.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.chemodurov.blog.service.PostService;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping()
    public String sayHello(Model model) {
        model.addAttribute("posts", postService.showAllPageable(Pageable.unpaged()));
        return "/view/hello";
    }

    @GetMapping("/all")
    public String showAllPageable(Model model) {
        model.addAttribute("posts", postService.showAllPageable(Pageable.unpaged()));
        return "show-posts";
    }
}
