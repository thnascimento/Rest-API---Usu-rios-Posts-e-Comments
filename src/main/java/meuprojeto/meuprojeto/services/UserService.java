package meuprojeto.meuprojeto.services;

import meuprojeto.meuprojeto.domain.User;
import meuprojeto.meuprojeto.repository.UserRepository;
import meuprojeto.meuprojeto.controllers.exception.CustomDefaultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomDefaultException(25, HttpStatus.NOT_FOUND, "Nenhum usuário com o id '"+ id + "' foi encontrado."));
    }

    public void insert(User obj) {
        userRepository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        return userRepository.save(newObj);
    }
}