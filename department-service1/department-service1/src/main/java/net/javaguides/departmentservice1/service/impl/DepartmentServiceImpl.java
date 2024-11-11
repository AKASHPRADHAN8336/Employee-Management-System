package net.javaguides.departmentservice1.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice1.dto.DepartmentDto;
import net.javaguides.departmentservice1.entity.Department;
import net.javaguides.departmentservice1.repository.DepartmentRepository;
import net.javaguides.departmentservice1.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // CONVERT DEPARTMENTDTO TO DEPARTMENT JPA ENTITY
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

        Department savedDepartment=departmentRepository.save(department);

        DepartmentDto departmentDto1 = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );
        return departmentDto1;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}
