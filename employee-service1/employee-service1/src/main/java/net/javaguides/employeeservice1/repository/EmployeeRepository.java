package net.javaguides.employeeservice1.repository;

import net.javaguides.employeeservice1.dto.EmployeeDto;
import net.javaguides.employeeservice1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
