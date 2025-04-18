package net.javaguides.departmentservice1.repository;

import net.javaguides.departmentservice1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Department findByDepartmentCode(String departmentCode);
}
