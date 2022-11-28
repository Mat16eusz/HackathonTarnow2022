package com.example.hackathon2022.domain

data class DomainHome(
    var id: Int? = null,
    var homeName: String = "",
    var usagePower: Double? = 0.0,
    var devices: MutableList<DomainDevice> = mutableListOf(),
)