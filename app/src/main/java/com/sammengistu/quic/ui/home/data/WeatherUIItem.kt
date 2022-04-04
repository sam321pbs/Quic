package com.sammengistu.quic.ui.home.data

import com.sammengistu.quic.data.models.CurrentWeather
import com.sammengistu.quic.ui.home.CardViewType

data class WeatherUIItem(
    val cityName: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val humidity: String,
    val weatherGroup: String,
    val weatherDescription: String,
    val icon: String,
    override val cardViewType: CardViewType = CardViewType.WEATHER
) : CardViewAdapterItem {
    companion object {
        fun transform(currentWeather: CurrentWeather): WeatherUIItem =
            WeatherUIItem(
                currentWeather.name,
                currentWeather.main.temp.toString(),
                currentWeather.main.temp_max.toString(),
                currentWeather.main.temp_min.toString(),
                currentWeather.main.humidity.toString(),
                currentWeather.weather[0]?.main ?: "",
                currentWeather.weather[0]?.description ?: "",
                createIconUrl(currentWeather.weather[0]?.icon),
            )

        private fun createIconUrl(iconId: String?) = iconId?.let {
            "https://openweathermap.org/img/wn/${iconId}@2x.png"
        } ?: run { "" }
    }
}
