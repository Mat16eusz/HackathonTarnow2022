package com.example.hackathon2022.domain

data class DomainDevice(
    var deviceName: String = "",
    var deviceType: String = "",
    var power: Double = -1.00,
    var workTime: Double = -1.00,
    var powerUsage: Double = -1.00,
)