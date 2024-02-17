package com.pragma.powerup.domain.spi.feignclient;

import com.pragma.powerup.domain.model.EmployeeModel;

public interface IEmployeeFeignPort {
    void saveEmployee(EmployeeModel employeeModel);
}
