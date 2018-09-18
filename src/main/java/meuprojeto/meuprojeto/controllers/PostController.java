package meuprojeto.meuprojeto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import meuprojeto.meuprojeto.domain.Post;
import meuprojeto.meuprojeto.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Posts")
@RestController
@RequestMapping(value = "/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @ApiOperation(value = "Retorna um post procurando pelo ID")
    @GetMapping(value= "/{id}")
    public Post findById(@PathVariable String id) {
        return postService.findById(id);
    }
}