package t.education.metricsconsumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import t.education.metricsconsumer.dto.kafka.KafkaDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.kafkaMessageGroupId}")
    private String kafkaMessageGroupId;

    @Bean
    public ConsumerFactory<Long, KafkaDto> kafkaMessageConsumerFactory (ObjectMapper objectMapper) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaMessageGroupId);
        config.put(JsonDeserializer.TYPE_MAPPINGS,
                "t.education.metricsproducer.dto.kafka.KafkaDto:t.education.metricsconsumer.dto.kafka.KafkaDto");
        config.put(JsonDeserializer.TRUSTED_PACKAGES , "*");
        return new DefaultKafkaConsumerFactory<>(config, new LongDeserializer(), new JsonDeserializer<>(objectMapper));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, KafkaDto> kafkaMessageConcurrentKafkaListenerContainerFactory(
            ConsumerFactory<Long, KafkaDto> kafkaMessageConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Long, KafkaDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaMessageConsumerFactory);
        return factory;
    }

}