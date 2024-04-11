package com.example.springgradle.service;

import com.example.springgradle.repository.EmployeeEntity;
import com.example.springgradle.repository.UserRepository;
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
        String employee = userRepository.findById(id).toString();
        return employee;
    }
    public String saveUser(EmployeeEntity employee) {
        userRepository.save(employee);
        return  "Saved";
    }
    public String deleteUser(Long Id) {
        userRepository.deleteById(Id);
        return "Deleted";
    }
    public String updateUser(Long id, EmployeeEntity employee) {
        Optional<EmployeeEntity> employeeOptional = userRepository.findById(id);
        if (employeeOptional.isPresent()) {
           EmployeeEntity presentEmployee = employeeOptional.get();

           if(Objects.nonNull(employee.getName()) && !"".equalsIgnoreCase(employee.getName())){
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
