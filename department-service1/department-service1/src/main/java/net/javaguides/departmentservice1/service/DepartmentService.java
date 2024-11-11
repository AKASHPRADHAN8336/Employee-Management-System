package net.javaguides.departmentservice1.service;

import net.javaguides.departmentservice1.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
