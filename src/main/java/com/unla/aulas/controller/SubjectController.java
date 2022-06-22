package com.unla.aulas.controller;

import com.unla.aulas.dto.SubjectDto;
import com.unla.aulas.entity.SubjectEntity;
import com.unla.aulas.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/subject/")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping()
    public String saveSubject(@RequestBody SubjectEntity json){
        boolean resp = subjectService.saveSubject(json);
        if(resp){
            return "Se ha creado correctamente";
        }
        return "No se pudo crear la materia " + json.getSubject();
    }

    @GetMapping()
    public ArrayList<SubjectEntity> getSubjects(){
        return subjectService.getSubjectsEntity();
    }

    @GetMapping(path = "{id}")
    public SubjectDto getSubject(@PathVariable("id") int id){
        return this.subjectService.getSubjectById(id);
    }

    @DeleteMapping(path = "{id}")
    public String deleteSubject(@PathVariable("id") int id){
        boolean ok = this.subjectService.deleteSubject(id);
        if(ok){
            return "Se ha borrado con exito";
        }else {
            return "No existe el id " + id;
        }
    }

}
