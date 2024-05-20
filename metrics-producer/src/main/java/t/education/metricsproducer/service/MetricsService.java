package t.education.metricsproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import t.education.metricsproducer.dto.kafka.Metric;
import t.education.metricsproducer.dto.kafka.KafkaDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "scheduler.enable", matchIfMissing = true)
public class MetricsService {

    private final MetricsEndpoint metricsEndpoint;

    private final KafkaTemplate<Long, KafkaDto> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String kafkaTopic;

    @Value("${metrics.observation}")
    private List<String> observationMetrics;


    public KafkaDto getMetrics() {
        KafkaDto metrics = new KafkaDto();
        metrics.setDateTime(LocalDateTime.now());
        observationMetrics.forEach(metric -> metrics.getMetrics().add(
                new Metric(metric, metricsEndpoint.metric(metric, Collections.emptyList()).getMeasurements().get(0).getValue())
        ));
        return metrics;
    }

    @Scheduled(cron = "${scheduler.metric-send}")
    public void sendMetric() {
        kafkaTemplate.send(kafkaTopic, getMetrics());
        log.info("Message send to Kafka");
    }
}