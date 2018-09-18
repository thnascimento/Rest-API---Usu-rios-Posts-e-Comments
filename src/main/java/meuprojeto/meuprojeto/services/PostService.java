package meuprojeto.meuprojeto.services;

import meuprojeto.meuprojeto.controllers.exception.CustomDefaultException;
import meuprojeto.meuprojeto.domain.Post;
import meuprojeto.meuprojeto.repository.PostRepository;
//import meuprojeto.meuprojeto.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id){
        return postRepository.findById(id).orElseThrow(null);
    }
}