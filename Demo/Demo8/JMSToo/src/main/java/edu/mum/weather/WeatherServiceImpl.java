package edu.mum.weather;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class WeatherServiceImpl implements WeatherService {

    public List<TemperatureInfo> getTemperatures(String city, List<Date> dates) {
        List<TemperatureInfo> temperatures = new ArrayList<TemperatureInfo>();
        for (Date date : dates) {
            temperatures.add(new TemperatureInfo(city, date, 100.0, 22.0, -38.0));
        }
        return temperatures;
    }
}
