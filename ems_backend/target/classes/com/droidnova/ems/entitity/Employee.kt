package com.droidnova.ems.entitity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,

    @Column(name = "first_name")
    var firstName:String,

    @Column(name = "last_name")
    var lastName:String,

    @Column(name = "email_id", nullable = false, unique = true)
    var email:String
)