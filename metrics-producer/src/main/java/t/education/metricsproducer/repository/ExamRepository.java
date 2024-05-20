package t.education.metricsproducer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t.education.metricsproducer.entity.Exam;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Optional<Exam> findByTitle(String russianLanguage);
}
