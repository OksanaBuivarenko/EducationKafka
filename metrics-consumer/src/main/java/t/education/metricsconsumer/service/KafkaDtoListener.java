package t.education.metricsconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import t.education.metricsconsumer.dto.kafka.KafkaDto;
import t.education.metricsconsumer.mapper.MetricMapper;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaDtoListener {

    private final MetricService metricService;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload KafkaDto kafkaDto) {
        metricService.saveAllMetric(kafkaDto.getMetrics().stream().map(metric ->
                MetricMapper.INSTANCE.toEntity(metric, kafkaDto.getDateTime())).collect(Collectors.toList()));
        log.info("KafkaListener got a message");
    }
}
