package com.example.hackathon2022.domain

data class DomainDevice(
    var deviceName: String = "",
    var deviceType: String = "",
    var sleepConsumption: Int = -1,
    var normalConsumption: Int = -1
)