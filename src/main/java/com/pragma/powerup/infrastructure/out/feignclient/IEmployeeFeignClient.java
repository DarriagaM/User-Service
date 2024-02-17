package com.pragma.powerup.infrastructure.out.feignclient;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "restaurantEmployee",name = "plazoleta-Service", url = "localhost:8092/employee")
public interface IEmployeeFeignClient {
    @PostMapping("/")
    void saveEmployee(EmployeeRequestDto employeeRequestDto);
}
