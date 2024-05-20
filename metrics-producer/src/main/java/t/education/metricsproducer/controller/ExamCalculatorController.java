package t.education.metricsproducer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import t.education.metricsproducer.dto.request.ExamRq;
import t.education.metricsproducer.dto.responce.SpecialityRs;
import t.education.metricsproducer.service.ExamCalculatorService;
import t.education.metricsproducer.service.MetricsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
public class ExamCalculatorController {

    private final ExamCalculatorService examCalculatorService;

    private final MetricsService metricsService;

    @GetMapping("/calculator")
    public List<SpecialityRs> getSpecialities(@RequestBody ExamRq examRq) {
        return examCalculatorService.getSpeciality(examRq);
    }

    @PostMapping("/metrics")
    public void sendMetrics() {
        metricsService.sendMetric();
    }
}
