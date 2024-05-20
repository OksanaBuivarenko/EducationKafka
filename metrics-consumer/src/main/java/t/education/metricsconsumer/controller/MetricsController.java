package t.education.metricsconsumer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import t.education.metricsconsumer.dto.responce.MetricsRs;
import t.education.metricsconsumer.service.MetricService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumer")
public class MetricsController {

    private final MetricService metricService;

    @GetMapping("/metrics")
    public List<MetricsRs> getMetrics() {
        return metricService.getMetrics();
    }

    @GetMapping("/metrics/{id}")
    public MetricsRs getMetricById(@PathVariable Long id) {
        return metricService.getMetricById(id);
    }

    @GetMapping("/metrics/tag/{tag}")
    public List<MetricsRs> getMetricByTag(@PathVariable String tag) {
        return metricService.getMetricsByTag(tag);
    }

    @GetMapping("/metrics/date")
    public List<MetricsRs> getMetricsByDate(@RequestParam("from") String from,
                                            @RequestParam("to") String to) {
        return metricService.getMetricsByDate(from, to);
    }

    @GetMapping("/metrics/tag&date/{tag}")
    public List<MetricsRs> getMetricByTag(@PathVariable String tag,
                                          @RequestParam("from") String from,
                                          @RequestParam("to") String to) {
        return metricService.getMetricsByTagAndDate(tag, from, to);
    }
}
