package com.sumit.assignmentfive.dao;
import com.sumit.assignmentfive.model.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends CrudRepository<EmployeeEntity, Long> {
    @Query(value = "select * from EMPLOYEE  where EMPLOYEE.email =?1" ,nativeQuery = true)
    EmployeeEntity getEmployeeByEmail(String emailId);
}
