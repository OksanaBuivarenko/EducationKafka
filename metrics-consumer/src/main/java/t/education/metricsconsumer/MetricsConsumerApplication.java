package t.education.metricsconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MetricsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsConsumerApplication.class, args);
	}

}
