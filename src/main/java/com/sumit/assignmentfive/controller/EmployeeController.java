package com.sumit.assignmentfive.controller;

import com.sumit.assignmentfive.dto.EmployeeDTO;
import com.sumit.assignmentfive.exception.RecordNotFoundException;
import com.sumit.assignmentfive.model.EmployeeEntity;
import com.sumit.assignmentfive.response.ResponseHandler;
import com.sumit.assignmentfive.service.EmployeeService;
import com.sumit.assignmentfive.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    /* The application should store in a ‘Map’ the employee details like Employee ID’ , ‘Employee Name’ , ‘Employee Email’ and ‘Location’. (Hint: Map’s key can be ‘Employee ID’ and Value can be the Employee Object)*/
    @GetMapping(path = {"/get-employee-by-id-kv/{id}"})
    public Map<String, Object> getemployeeByIdInMap(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        EmployeeEntity entityEmployeeEntity = service.getEmployeeById(id.get());
        if (id.isPresent() && Objects.nonNull(entityEmployeeEntity)) {
            Map<Integer, EmployeeEntity> map = new HashMap();
            map.put(id.get().intValue(), entityEmployeeEntity);
            return ResponseHandler.generateResponse(MessageResult.EMPLOYEE_BY_ID, HttpStatus.ACCEPTED,
                    false, map);
        } else {
            return ResponseHandler.generateResponse(MessageResult.EMPLOYEE_BY_ID_NOT_FOUND,
                    HttpStatus.NOT_FOUND, true, id);
        }
    }

    @GetMapping(path = "/get-all-employees")
    public Map<String, Object> getAllEmployeesData(Model model) {
        List<EmployeeEntity> allEmployeeslist = service.getAllEmployees();
        if (Objects.nonNull(allEmployeeslist)) {
            return ResponseHandler.generateResponse(MessageResult.GET_ALL_EMPLOYEE, HttpStatus.ACCEPTED,
                    false, allEmployeeslist);
        } else {
            return ResponseHandler.generateResponse(MessageResult.DATA_NOT_FOUND,
                    HttpStatus.NOT_FOUND, true, null);
        }
    }

    /*PUT – using this method the client should be able to update/modify the Employees details except for Employee ID*/
    @PutMapping(path = {"/edit/{id}"})
    public Map<String, Object> editEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            EmployeeEntity entityEmployeeEntity = service.getEmployeeById(id.get());
            return ResponseHandler.generateResponse(MessageResult.EDIT_EMPLOYEE_BY_ID, HttpStatus.ACCEPTED,
                    false, entityEmployeeEntity);
        } else {
            return ResponseHandler.generateResponse(MessageResult.EDITE_MPLOYEE_BY_ID_NOT_FOUND,
                    HttpStatus.NOT_FOUND, true, null);
        }

    }

    /*GET – using this the client should be able to get details of all registered employees and also be able to get employee details based on Employee ID which can be passed as a Path Parameter*/
    @GetMapping(path = {"/get-employee-by-id/{id}"})
    public Map<String, Object> getemployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            EmployeeEntity entityEmployeeEntity = service.getEmployeeById(id.get());
            return ResponseHandler.generateResponse(MessageResult.EMPLOYEE_BY_ID, HttpStatus.ACCEPTED,
                    false, entityEmployeeEntity);
        } else {
            return ResponseHandler.generateResponse(MessageResult.EMPLOYEE_BY_ID_NOT_FOUND,
                    HttpStatus.NOT_FOUND, true, null);
        }

    }

    /*DELETE – using this method the client should be able to delete an unwanted Employee record*/
    @DeleteMapping(path = "/delete/{id}")
    public Map<String, Object> deleteEmployeeById(@PathVariable("id") Long id) {
        Boolean deleteEmployeeById = service.deleteEmployeeById(id);
        if (deleteEmployeeById) {
            return ResponseHandler.generateResponse(MessageResult.EMPLOYEE_DELETED, HttpStatus.ACCEPTED,
                    false, id);
        } else {
            return ResponseHandler.generateResponse(MessageResult.DATA_NOT_FOUND,
                    HttpStatus.BAD_REQUEST, true, null);
        }
    }

    /*POST – using this method the client should be able to save an Employees details into the Map*/
    @PostMapping(path = "/createEmployee")
    public Map<String, Object> createOrUpdateEmployee(@RequestBody EmployeeDTO employee) {
        EmployeeEntity entity = service.createOrUpdateEmployee(employee);
        if (Objects.nonNull(entity)) {
            return ResponseHandler.generateResponse(MessageResult.EMPLOYEE_CREATED, HttpStatus.ACCEPTED,
                    false, entity);
        } else {
            return ResponseHandler.generateResponse(MessageResult.DATA_NOT_FOUND,
                    HttpStatus.BAD_REQUEST, true, null);
        }
    }

}
