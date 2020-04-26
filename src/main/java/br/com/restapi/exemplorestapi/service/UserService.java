package br.com.restapi.exemplorestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restapi.exemplorestapi.models.User;
import br.com.restapi.exemplorestapi.repository.UserRepository;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    public List<User> getAll() {
        return usuarioRepository.findAll();
    }

    public User getById(long id) {
        return usuarioRepository.findById(id).get();
    }

    public User createOrUpdate(User usuario) {
        return usuarioRepository.save(usuario);
    }

    public void remove(User user) {
        usuarioRepository.delete(user);
    }
}
