package t.education.metricsproducer.dto.kafka;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class KafkaDto {

    private LocalDateTime dateTime;

    private List<Metric> metrics = new ArrayList<>();
}
