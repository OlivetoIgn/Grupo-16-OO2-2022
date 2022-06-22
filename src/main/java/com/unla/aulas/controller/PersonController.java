package com.unla.aulas.controller;


import com.unla.aulas.entity.ClassroomEntity;
import com.unla.aulas.entity.RoleEntity;
import com.unla.aulas.entity.UserEntity;
import com.unla.aulas.repository.RoleRepository;
import com.unla.aulas.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/person/")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public List<UserEntity> findAll()  {
        List<UserEntity> users = personService.getAll();
        return users;
    }

    @PostMapping()
    public UserEntity saveUser(@RequestBody UserEntity json){
                return personService.saveUser(json);
    }

    @GetMapping(path = "{id}")
    public Optional<UserEntity> getById(@PathVariable("id") int id) {
        return personService.getUserById(id);
    }

    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable("id") int id) {
        String message;
        boolean response = personService.deleteUserById(id);
        if (response)
            message = "Se ha eliminado el usuario";
        else
            message = "No existe el id " + id;
        return message;
    }

    @GetMapping("userName/")
    public UserEntity getUserByUsername(@RequestBody UserEntity json){
        return personService.getUserByUsername(json.getUserName());
    }

    @PutMapping(path = "{id}")
    public UserEntity updateUser(@PathVariable("id") int id, @RequestBody UserEntity json){
        Optional<UserEntity> user = personService.getUserById(id);
        Set<RoleEntity> roles = null;
        roles.add(roleRepository.getById(json.getRoles().stream().findFirst().get().getId()));
        user.get().setDocument(json.getDocument());
        user.get().setDocumentType(json.getDocumentType());
        user.get().setEmail(json.getEmail());
        user.get().setName(json.getName());
        user.get().setSurname(json.getSurname());
        user.get().setPassword(json.getPassword());
        user.get().setRoles(roles);
        return personService.saveUser(user.get());
    }
}
