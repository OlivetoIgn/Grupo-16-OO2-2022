package com.unla.aulas.service;

import com.unla.aulas.dto.CarrerDto;
import com.unla.aulas.dto.SubjectDto;
import com.unla.aulas.entity.CarrerEntity;
import com.unla.aulas.entity.SubjectEntity;
import com.unla.aulas.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CarrerService carrerService = new CarrerService();

    public boolean saveSubject(SubjectEntity subject){
        ArrayList<SubjectEntity> lstSubjects = getSubjectsEntity();
        for (SubjectEntity subjectCreated:lstSubjects) {
            if(subject.equals(subjectCreated)){
                return false;
            }
        }
        CarrerEntity carrerEntity = carrerService.getCarrerEntity(subject.getCarrer().getCarrer());
        if(carrerEntity == null){
            return false;
        }
        subject.setCarrer(carrerEntity);
        subjectRepository.save(subject);
        return true;
    }

    public ArrayList<SubjectEntity> getSubjectsEntity(){
         return (ArrayList<SubjectEntity>) subjectRepository.findAll();
    }

    public ArrayList<SubjectDto> getSubjects(){
        ArrayList<SubjectDto> lstSubjectsDto = new ArrayList<>();
        ArrayList<SubjectEntity> lstSubjects = (ArrayList<SubjectEntity>) subjectRepository.findAll();
        for (SubjectEntity subject: lstSubjects) {
            Optional<CarrerEntity> carrerDto = carrerService.getCarrer(subject.getId());
            if(carrerDto != null) {
                SubjectDto subjectDto = null;
                subjectDto.setId(subject.getId());
                subjectDto.setSubject(subject.getSubject());
                subjectDto.setSubjectCode(subject.getSubjectCode());
                subjectDto.setCarrer(carrerService.getCarrer(subject.getCarrer().getCarrer()));
                lstSubjectsDto.add(subjectDto);
            }
        }
        return lstSubjectsDto;
    }

    public Optional<SubjectEntity> getSubjectByIdEntity(int id){
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);

        return subjectEntity.isEmpty() ? null : subjectEntity;
    }

    public SubjectDto getSubjectById(int id){
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
        CarrerDto carrerDto = carrerService.getCarrer(subjectEntity.get().getCarrer().getCarrer());
        return new SubjectDto(subjectEntity.get().getId(), subjectEntity.get().getSubjectCode(), subjectEntity.get().getSubject(), carrerDto);
    }

    public Optional<SubjectEntity> getSubjectEntityById(int id){
        return subjectRepository.findById(id);
    }

    public SubjectEntity getSubject(String subject){
        return subjectRepository.findBySubject(subject);
    }

    public SubjectEntity getSubjectBySubjectCode(int subjectCode){
        return subjectRepository.findBySubjectCode(subjectCode);
    }

    public boolean deleteSubject(int id){
        try{
            subjectRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
