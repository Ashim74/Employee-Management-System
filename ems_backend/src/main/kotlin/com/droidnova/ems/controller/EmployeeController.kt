package com.droidnova.ems.controller

import com.droidnova.ems.dto.EmployeeDto
import com.droidnova.ems.entitity.Employee
import com.droidnova.ems.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
class EmployeeController {

    @Autowired
    private lateinit var employeeService:EmployeeService

    //add employee REST API
    @PostMapping
    fun createEmployee(@RequestBody employeeDto: EmployeeDto):ResponseEntity<Any>{
        val savedEmployee = employeeService.createEmployee(employeeDto)
        return if (savedEmployee==null){
            val errorMessage = "Email ${employeeDto.email} already exists."
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage)
        }else{
            ResponseEntity(savedEmployee,HttpStatus.CREATED)

        }
    }

    //get employee REST API
    @GetMapping("{id}")
    fun getEmployeeById(@PathVariable id:Long):ResponseEntity<EmployeeDto>{
        val employee = employeeService.getEmployeeById(id)
        return ResponseEntity.ok(employee)
    }

    //get all employee
    @GetMapping
    fun getAllEmployee():ResponseEntity<List<EmployeeDto>>{
        return ResponseEntity.ok(employeeService.getAllEmployee())
    }

    //update employee
    @PutMapping("{id}")
    fun updateEmployee(@PathVariable id: Long,@RequestBody updatedEmployee: EmployeeDto):ResponseEntity<Any>{
        val employee = employeeService.updateEmployee(id,updatedEmployee)
        employee?.let {
            return ResponseEntity.ok(employee)
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not update employee")
    }

    //delete employee
    @DeleteMapping("{id}")
    fun deleteEmployee(@PathVariable id: Long):ResponseEntity<String>{
        employeeService.deleteEmployee(id)
        return ResponseEntity.ok("Deleted Successfully")
    }
}