package com.droidnova.ems.service.impl

import com.droidnova.ems.dto.EmployeeDto
import com.droidnova.ems.entitity.Employee
import com.droidnova.ems.exception.ResourceNotFoundException
import com.droidnova.ems.mapper.EmployeeMapper
import com.droidnova.ems.repository.EmployeeRepository
import com.droidnova.ems.service.EmployeeService
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class EmployeeServiceImpl @Autowired constructor(
    private val employeeRepository: EmployeeRepository,
    private val employeeMapper: EmployeeMapper // Inject EmployeeMapper
) : EmployeeService {

    override fun createEmployee(employeeDto: EmployeeDto): EmployeeDto? {
        if (employeeRepository.existsByEmail(employeeDto.email)) {
            return null
        }
        val employee = employeeMapper.mapToEmployee(employeeDto)
        val savedEmployee = employeeRepository.save(employee)
        return employeeMapper.mapToEmployeeDto(savedEmployee)
    }

    override fun getEmployeeById(employeeId: Long): EmployeeDto {
        val employee = employeeRepository.findById(employeeId)
            .orElseThrow { ResourceNotFoundException("Employee not found for this id: $employeeId") }
        return employeeMapper.mapToEmployeeDto(employee)
    }


    override fun getAllEmployee(): List<EmployeeDto> {
        return employeeRepository.findAll().map { employee -> employeeMapper.mapToEmployeeDto(employee) }
    }

    override fun updateEmployee(id: Long, updatedEmployee: EmployeeDto): EmployeeDto? {
        val existingEmployeeOptional = employeeRepository.findById(id)

        if (existingEmployeeOptional.isEmpty) {
            return null
        }

        val existingEmployee = existingEmployeeOptional.get()

        existingEmployee.apply {
            firstName = updatedEmployee.firstName
            lastName = updatedEmployee.lastName
            email = updatedEmployee.email
        }

        val updatedEmployeeEntity = employeeRepository.save(existingEmployee)
        return employeeMapper.mapToEmployeeDto(updatedEmployeeEntity)
    }


    override fun deleteEmployee(id: Long) {
        val employee = employeeRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Employee not found for this id: $id") }

        employeeRepository.deleteById(id)
    }


}
