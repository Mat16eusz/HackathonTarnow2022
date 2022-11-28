package com.example.hackathon2022.domain

data class DomainHome(
    var id: Int? = null,
    var homeName: String = "",
    var usagePower: Int? = null,
    var devices: MutableList<DomainDevice> = mutableListOf(),
)