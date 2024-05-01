package com.droidnova.ems

import com.droidnova.ems.controller.EmployeeController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmsApplication

fun main(args: Array<String>) {
	runApplication<EmsApplication>(*args)

}
