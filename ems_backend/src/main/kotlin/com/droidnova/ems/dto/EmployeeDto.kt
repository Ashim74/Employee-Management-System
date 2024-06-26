package com.droidnova.ems.dto

import jakarta.persistence.Column
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class EmployeeDto (
    var id:Long,
    var firstName:String,
    var lastName:String,
    var email:String
)