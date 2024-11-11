package net.javaguides.departmentservice1.controller;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice1.dto.DepartmentDto;
import net.javaguides.departmentservice1.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    // build save department REST APIs
@PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    //build get department REST APIs
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
    DepartmentDto departmentDto=departmentService.getDepartmentByCode(departmentCode);
    return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }

}
