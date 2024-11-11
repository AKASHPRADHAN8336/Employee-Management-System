package net.javaguides.employeeservice1.service;

import net.javaguides.employeeservice1.dto.APIResponseDto;
import net.javaguides.employeeservice1.dto.EmployeeDto;



public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
