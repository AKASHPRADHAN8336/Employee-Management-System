package net.javaguides.employeeservice1.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice1.dto.DepartmentDto;
import net.javaguides.employeeservice1.dto.APIResponseDto;
import net.javaguides.employeeservice1.dto.EmployeeDto;
import net.javaguides.employeeservice1.dto.OrganisationDto;
import net.javaguides.employeeservice1.entity.Employee;
import net.javaguides.employeeservice1.mapper.EmployeeMapper;
import net.javaguides.employeeservice1.repository.EmployeeRepository;
import net.javaguides.employeeservice1.service.APIClient;
import net.javaguides.employeeservice1.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class  );



    private WebClient webClient;

//    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
//        Employee employee = new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail(),
//                employeeDto.getDepartmentCode()
//        );
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee employee1=employeeRepository.save(employee);

//        EmployeeDto employeeDto1 = new EmployeeDto(
//                employee1.getId(),
//                employee1.getFirstName(),
//                employee1.getLastName(),
//                employee1.getEmail(),
//                employee1.getDepartmentCode()
//        );

        EmployeeDto employeeDto1 = EmployeeMapper.mapToEmployeeDto(employee1);
        return employeeDto1;
    }
//    @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "GetDefaultDepartment")/
    @Retry(name ="${spring.application.name}",fallbackMethod = "GetDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("inside getEmployeeBydId method");
        Employee employee = employeeRepository.findById(employeeId).get();
//       ResponseEntity<DepartmentDto> departmentDtoResponseEntity= restTemplate.getForEntity("http://localhost:8083/api/departments/   "+employee.getDepartmentCode(), DepartmentDto.class);
//       DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();

      DepartmentDto departmentDto= webClient.get().uri("http://localhost:8083/api/departments/"+employee.getDepartmentCode())
               .retrieve()
               .bodyToMono(DepartmentDto.class)
               .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        OrganisationDto organisationDto = webClient.get().uri("http://localhost:8086/api/organisations/"+employee.getOrganisationCode())
                .retrieve()
                .bodyToMono(OrganisationDto.class)
                .block();

//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartmentCode()
//        );



        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganisationDto(organisationDto);

        return apiResponseDto;
    }

    public APIResponseDto GetDefaultDepartment(Long employeeId,Exception exception) {
        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and development department");


//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartmentCode()
//        );
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
