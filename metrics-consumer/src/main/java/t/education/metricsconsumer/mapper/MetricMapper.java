package t.education.metricsconsumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import t.education.metricsconsumer.dto.kafka.Metric;
import t.education.metricsconsumer.dto.responce.MetricsRs;
import t.education.metricsconsumer.entity.MetricEntity;

import java.time.LocalDateTime;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MetricMapper {

    MetricMapper INSTANCE = getMapper(MetricMapper.class);

    MetricsRs toDto(MetricEntity metricEntity);

    @Mapping(target = "id", ignore = true)
    MetricEntity toEntity(Metric metric, LocalDateTime dateTime);
}
