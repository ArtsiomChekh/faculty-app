package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;

import java.util.*;
import java.util.stream.Collectors;

import com.chekh.artsiom.repository.DepartmentStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentStudentRepository departmentStudentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Map<Department, Long> getDepartmentsStudentCount() {
        List<Object[]> departmentStudentCountList = departmentRepository.getDepartmentStudentCount();
        List<Department> departments = new ArrayList<>();
        List<Long> studentCounts = new ArrayList<>();

        for (Object[] departmentStudentCount : departmentStudentCountList) {
            Department department = (Department) departmentStudentCount[0];
            Long count = (Long) departmentStudentCount[1];

            departments.add(department);
            studentCounts.add(count);
        }

        Map<Department, Long> departmentStudentCountMap = new HashMap<>();
        for (int i = 0; i < departments.size(); i++) {
            departmentStudentCountMap.put(departments.get(i), studentCounts.get(i));
        }

        return departmentStudentCountMap;
    }

    @Override
    public void sortDepartmentsByStudentCount(Map<Department, Long> depAndStudCount) {
        Map<Department, Long> departmentStudentCountMap = departmentService.getDepartmentsStudentCount();
        Map<Department, Long> sortedMap = departmentStudentCountMap.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    @Override
    public List<Department> getAllDepartmentsSortedByTeachers() {
        return null;
    }


}



