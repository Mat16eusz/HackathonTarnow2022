package com.example.hackathon2022.domain

data class DomainDevice(
    var deviceName: String = "",
    var deviceType: String = "",
    var power: Double = 0.00,
    var workTime: Double = 0.00,
    var powerUsage: Double = 0.00,
)