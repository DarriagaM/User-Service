package com.pragma.powerup.infrastructure.out.feignclient.adapter;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.mapper.IEmployeeRequestMapper;
import com.pragma.powerup.domain.model.EmployeeModel;
import com.pragma.powerup.domain.spi.feignclient.IEmployeeFeignPort;
import com.pragma.powerup.infrastructure.out.feignclient.IEmployeeFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeFeignAdapter implements IEmployeeFeignPort {
    private final IEmployeeFeignClient employeeFeignClient;
    private final IEmployeeRequestMapper employeeRequestMapper;

    @Override
    public void saveEmployee(EmployeeModel employeeModel) {
        EmployeeRequestDto employeeRequestDto = employeeRequestMapper.toEmployeeRequestDto(employeeModel);
        employeeFeignClient.saveEmployee(employeeRequestDto);
    }
}
