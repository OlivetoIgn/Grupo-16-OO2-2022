package com.unla.aulas.controller;

import com.unla.aulas.entity.RoleEntity;
import com.unla.aulas.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role/")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @PostMapping()
    public RoleEntity saveRole(@RequestBody RoleEntity json){
        return roleRepository.save(json);
    }

    @GetMapping()
    public List<RoleEntity> getAllRoles(){
        return roleRepository.findAll();
    }

    @DeleteMapping(path = "{id}")
    public String deleteRol(@PathVariable("id") int id) {
        String message;
        try{
            roleRepository.deleteById(id);
            return "Se ha eliminado el rol";
        }catch (Exception e) {
            message = "No existe el id " + id;
        }
        return message;
    }
}
