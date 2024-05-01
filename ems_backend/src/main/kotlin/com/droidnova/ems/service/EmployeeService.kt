package com.droidnova.ems.service

import com.droidnova.ems.dto.EmployeeDto
import com.droidnova.ems.entitity.Employee

interface EmployeeService {

    fun createEmployee(employeeDto: EmployeeDto):EmployeeDto?

    fun getEmployeeById(employeeId:Long):EmployeeDto?

    fun getAllEmployee():List<EmployeeDto>

    fun updateEmployee(id:Long,updatedEmployee: EmployeeDto):EmployeeDto?

    fun deleteEmployee(id: Long)
}