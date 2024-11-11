package net.javaguides.employeeservice1.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice1.dto.DepartmentDto;
import net.javaguides.employeeservice1.dto.APIResponseDto;
import net.javaguides.employeeservice1.dto.EmployeeDto;
import net.javaguides.employeeservice1.entity.Employee;
import net.javaguides.employeeservice1.repository.EmployeeRepository;
import net.javaguides.employeeservice1.service.APIClient;
import net.javaguides.employeeservice1.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

//    private WebClient webClient;

    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee employee1=employeeRepository.save(employee);

        EmployeeDto employeeDto1 = new EmployeeDto(
                employee1.getId(),
                employee1.getFirstName(),
                employee1.getLastName(),
                employee1.getEmail(),
                employee1.getDepartmentCode()
        );
        return employeeDto1;
    }
    @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "GetDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
       ResponseEntity<DepartmentDto> departmentDtoResponseEntity= restTemplate.getForEntity("http://localhost:8083/api/departments/   "+employee.getDepartmentCode(), DepartmentDto.class);
       DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();

//      DepartmentDto departmentDto= webClient.get().uri("http://localhost:8083/api/departments/  "+employee.getDepartmentCode())
//               .retrieve()
//               .bodyToMono(DepartmentDto.class)
//               .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());


        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }

//    public APIResponseDto GetDefaultDepartment(Long employeeId,Exception exception) {
//        Employee employee = employeeRepository.findById(employeeId).get();
//
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setDepartmentName("R&D Department");
//        departmentDto.setDepartmentCode("RD001");
//        departmentDto.setDepartmentDescription("Research and development department");
//
//
//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartmentCode()
//        );
//
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployeeDto(employeeDto);
//        apiResponseDto.setDepartmentDto(departmentDto);
//
//        return apiResponseDto;
//    }
}
