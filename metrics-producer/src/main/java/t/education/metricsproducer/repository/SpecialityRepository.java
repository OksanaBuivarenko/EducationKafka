package t.education.metricsproducer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t.education.metricsproducer.entity.ExamGroup;
import t.education.metricsproducer.entity.Speciality;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    List<Speciality> findByExamGroup(ExamGroup examGroup);
}
