package t.education.metricsconsumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.education.metricsconsumer.dto.responce.MetricsRs;
import t.education.metricsconsumer.entity.MetricEntity;
import t.education.metricsconsumer.exception.NotFoundException;
import t.education.metricsconsumer.mapper.MetricMapper;
import t.education.metricsconsumer.repository.MetricRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetricService {

    private final MetricRepository metricRepository;

    public List<MetricEntity> saveAllMetric(List<MetricEntity> metrics) {
        return metricRepository.saveAll(metrics);
    }

    public List<MetricsRs> getMetrics() {
        return metricRepository.findAll().stream().map(MetricMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public MetricsRs getMetricById(Long id) {
        return MetricMapper.INSTANCE.toDto(metricRepository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    public List<MetricsRs> getMetricsByTag(String tag) {
        return metricRepository.findByTag(tag).stream().map(MetricMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public List<MetricsRs> getMetricsByDate(String from, String to) {
        return metricRepository.findByDateTimeBetweenOrderByDateTimeDesc(LocalDateTime.parse(from),
                        LocalDateTime.parse(to)).stream().map(MetricMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public List<MetricsRs> getMetricsByTagAndDate(String tag, String from, String to) {
        return metricRepository.findByTagAndDateTimeBetweenOrderByDateTimeDesc(tag, LocalDateTime.parse(from),
                        LocalDateTime.parse(to)).stream().map(MetricMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
