package com.sumit.assignmentfive.service;


import com.sumit.assignmentfive.dao.EmployeeRepository;
import com.sumit.assignmentfive.dto.EmployeeDTO;
import com.sumit.assignmentfive.exception.RecordNotFoundException;
import com.sumit.assignmentfive.model.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
	EmployeeRepository repository;

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> result = (List<EmployeeEntity>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
    @Transactional
    @Modifying
    public EmployeeEntity createOrUpdateEmployee(EmployeeDTO entity) {
        log.info(entity.getEmail()+" "+ entity.getLocation());
        if (entity.getEmail() != null) {
            EmployeeEntity employeeEntity=new  EmployeeEntity();
            employeeEntity.setFirstName(entity.getFirstName());
            employeeEntity.setLastName(entity.getLastName());
            employeeEntity.setEmail(entity.getEmail());
            employeeEntity.setLocation(entity.getLocation());
            return repository.save(employeeEntity);
        } else {
            EmployeeEntity newEntity = repository.getEmployeeByEmail(entity.getEmail());

            if (Objects.nonNull(newEntity)) {
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                return null;
            }
        }
    }

    public Boolean deleteEmployeeById(Long id) {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}