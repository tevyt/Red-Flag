package me.travisalexandersmith.redflag.global.dto

data class ValidationErrorMessage(val message: String, val details: Map<String, String?>)