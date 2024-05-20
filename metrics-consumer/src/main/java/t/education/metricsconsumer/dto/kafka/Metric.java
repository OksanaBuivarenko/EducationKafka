package t.education.metricsconsumer.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Metric {

    private String tag;

    private Double value;
}
