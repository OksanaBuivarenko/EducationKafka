package t.education.metricsconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t.education.metricsconsumer.entity.MetricEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricRepository extends JpaRepository<MetricEntity, Long> {

    List<MetricEntity> findByTag(String tag);

    List<MetricEntity> findByDateTimeBetweenOrderByDateTimeDesc(LocalDateTime from, LocalDateTime to);

    List<MetricEntity> findByTagAndDateTimeBetweenOrderByDateTimeDesc(String tag, LocalDateTime from, LocalDateTime to);
}
