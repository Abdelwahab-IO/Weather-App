package com.example.icon_formatter

class IconFormatter {
    companion object {
        fun formatIconLink(icon: String): String {
            return "http://openweathermap.org/img/wn/$icon@4x.png"
        }
    }
}