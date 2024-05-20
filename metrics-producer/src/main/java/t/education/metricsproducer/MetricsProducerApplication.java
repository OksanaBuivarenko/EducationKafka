package t.education.metricsproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MetricsProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsProducerApplication.class, args);
    }

}
