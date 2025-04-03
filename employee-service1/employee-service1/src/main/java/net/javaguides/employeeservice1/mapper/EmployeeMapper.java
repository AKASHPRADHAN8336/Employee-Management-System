package net.javaguides.employeeservice1.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.employeeservice1.dto.EmployeeDto;
import net.javaguides.employeeservice1.entity.Employee;

import javax.persistence.Column;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganisationCode()
        );

        return employeeDto;
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganisationCode()
        );

        return employee;
    }
}
