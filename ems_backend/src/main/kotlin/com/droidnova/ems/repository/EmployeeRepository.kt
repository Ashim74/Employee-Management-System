package com.droidnova.ems.repository

import com.droidnova.ems.entitity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EmployeeRepository :JpaRepository<Employee,Long>{

    fun existsByEmail(email:String):Boolean

}