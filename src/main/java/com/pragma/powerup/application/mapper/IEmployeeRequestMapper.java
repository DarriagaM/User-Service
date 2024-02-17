package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.domain.model.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRequestMapper {
    EmployeeModel toEmployeeModel(EmployeeRequestDto employeeRequestDto);

    EmployeeRequestDto toEmployeeRequestDto(EmployeeModel employeeModel);
}
