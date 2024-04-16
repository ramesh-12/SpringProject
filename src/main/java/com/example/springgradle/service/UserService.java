package com.example.springgradle.service;

import com.example.springgradle.GlobalExceptionHandler.*;
import com.example.springgradle.repository.EmployeeEntity;
import com.example.springgradle.repository.UserRepository;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String getUsernameById(Long id) {
        Optional<EmployeeEntity> employeeOptional = userRepository.findById(id);
        if (employeeOptional.isPresent()) {
            EmployeeEntity employee = employeeOptional.get();
            return employee.toString();
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }
    public String saveUser(EmployeeEntity employee) {
        userRepository.save(employee);
        return  "Saved";
    }
    public String deleteUser(Long Id) {
        try {
            userRepository.deleteById(Id);
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Deleted";
    }
    public String updateUser(Long id, EmployeeEntity employee) {
        Optional<EmployeeEntity> employeeOptional = userRepository.findById(id);
        if (employeeOptional.isPresent()) {
           EmployeeEntity presentEmployee = employeeOptional.get();

           if(StringUtils.isNullOrEmpty(employee.getName())){
               presentEmployee.setName(employee.getName());
           }
           if(Objects.nonNull(employee.getSalary())){
               presentEmployee.setSalary(employee.getSalary());
           }
           if(Objects.nonNull(employee.getPosition()) && !"".equalsIgnoreCase(employee.getPosition())){
               presentEmployee.setPosition(employee.getPosition());
           }

           return userRepository.save(presentEmployee).toString();
        }
        return "Updated Successfully";
    }


}
