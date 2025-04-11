package com.example.data.weather.remote.response

data class City(
    val name: String

){
    companion object{
        fun getTestObject():City{
            return City("city")
        }
    }
}