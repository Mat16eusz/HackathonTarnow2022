package com.example.hackathon2022.domain

data class DomainDevice(
    var deviceName: String = "",
    var deviceType: String = "",
    var normalPower: Double = -1.00,
    var sleepPower: Double = -1.00,
    var workTime: Double = -1.00,
)