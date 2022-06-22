package com.unla.aulas.repository;

import com.unla.aulas.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    public abstract SubjectEntity findBySubject(String subject);
    public abstract SubjectEntity findBySubjectCode(int subjectCode);

}
