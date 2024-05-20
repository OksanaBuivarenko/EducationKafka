package t.education.metricsproducer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import t.education.metricsproducer.dto.responce.SpecialityRs;
import t.education.metricsproducer.entity.Speciality;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecialityMapper {

    SpecialityMapper INSTANCE = getMapper(SpecialityMapper.class);

    SpecialityRs toDto(Speciality speciality);
}
