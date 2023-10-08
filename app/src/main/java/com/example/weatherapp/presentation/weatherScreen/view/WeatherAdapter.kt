import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeatherListItemBinding
import com.example.weatherapp.presentation.weatherScreen.view.ForecastData

class WeatherAdapter(private val forecastData: List<ForecastData>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(private val binding: WeatherListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dailyForecast: ForecastData) {
            binding.dayNameTextView.text = dailyForecast.dayName
            binding.weatherIconImageView.setImageResource(dailyForecast.weatherIcon)
            binding.temperatureMaxTextView.text = dailyForecast.temp_max
            binding.temperatureMinTextView.text = dailyForecast.temp_min
            binding.descriptionTextView.text = dailyForecast.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val binding =
            WeatherListItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val forecast = forecastData[position]
        holder.bind(forecast)
    }

    override fun getItemCount(): Int {
        return forecastData.size
    }
}
