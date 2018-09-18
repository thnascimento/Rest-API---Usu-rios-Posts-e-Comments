package meuprojeto.meuprojeto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import meuprojeto.meuprojeto.domain.Post;
import meuprojeto.meuprojeto.dto.UserDTO;
import meuprojeto.meuprojeto.mapper.UserMapper;
import meuprojeto.meuprojeto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static meuprojeto.meuprojeto.mapper.UserMapper.mapDomainToDTO;

@Api(value = "CRUD Usuários")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Retorna uma lista de todos os usuários.")
    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll()
                .stream()
                .map(UserMapper::mapDomainToDTO)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Retorna um usuário procurando pelo ID.")
    @GetMapping(value = "/{id}")
    public UserDTO findById(@PathVariable String id) {
        return mapDomainToDTO(userService.findById(id));
    }

    @ApiOperation(value = "Cadastra um novo usuário.")
    @PostMapping
    public void insert(@Valid @RequestBody UserDTO objDto) {
        userService.insert(UserMapper.mapDtoToDomain(objDto));
    }

    @ApiOperation(value = "Deleta um usuário através do ID.")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @ApiOperation(value = "Atualiza os dados de um usuário através do ID.")
    @PutMapping(value = "/{id}")
    public void update(@Valid @RequestBody UserDTO objDto, @PathVariable String id) {
        objDto.setId(id);
        userService.update(UserMapper.mapDtoToDomain(objDto));
    }

    @ApiOperation(value = "Retorna todos os posts de um usuário procurando pelo ID.")
    @GetMapping(value="/{id}/posts")
    public List<Post> findPosts(@PathVariable String id) {
        return userService.findById(id).getPosts();
    }
}
