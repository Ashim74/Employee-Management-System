package com.droidnova.ems.mapper

import com.droidnova.ems.dto.EmployeeDto
import com.droidnova.ems.entitity.Employee
import org.springframework.stereotype.Component

@Component
class EmployeeMapper {

    fun mapToEmployeeDto(employee: Employee):EmployeeDto{
        return EmployeeDto(employee.id,employee.firstName,employee.lastName,employee.email)
    }

    fun mapToEmployee(employeeDto: EmployeeDto):Employee{
        return Employee(employeeDto.id,employeeDto.firstName,employeeDto.lastName,employeeDto.email)
    }
}