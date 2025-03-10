package michal.malek.avsystemtask.mapper;

import michal.malek.avsystemtask.model.Intersection;
import michal.malek.avsystemtask.model.dto.IntersectionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IntersectionMapper {
    IntersectionResponse toDto(Intersection intersection);
}
