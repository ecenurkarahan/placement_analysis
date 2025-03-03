package main;

import cities.City;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read city names with direct flights to Istanbul
            List<String> cityNamesWithFlights = objectMapper.readValue(new File("src/city_names.json"), new TypeReference<List<String>>() {});
            Set<String> cityNamesWithFlightsSet = new HashSet<>();
            for (String cityName : cityNamesWithFlights) {
                cityNamesWithFlightsSet.add(cityName.trim().toLowerCase());
            }

            // Read the list of cities
            List<City> cities = objectMapper.readValue(new File("src/data.json"), new TypeReference<List<City>>() {});
            for (City city : cities) {
                String cityName = city.getName();
                if (cityName != null && !cityName.isEmpty()) {
                    city.setDoesItHaveFlight(cityNamesWithFlightsSet.contains(cityName.trim().toLowerCase()));
                }
            }

            // Read the atamalar_prev.json file
            List<Map<String, Object>> atamalar = objectMapper.readValue(new File("src/atamalar.json"), new TypeReference<List<Map<String, Object>>>() {});

            // Merge the information
            List<Map<String, Object>> mergedData = new ArrayList<>();
            for (Map<String, Object> atama : atamalar) {
                String cityName = ((String) atama.get("CityName")).trim().toLowerCase();
                for (City city : cities) {
                    if (city.getName().trim().toLowerCase().equals(cityName)) {
                        Map<String, Object> mergedCity = new HashMap<>(atama);
                        mergedCity.put("DistanceToIstanbul", city.getDistancetoIstanbul());
                        mergedCity.put("DoesItHaveFlight", city.getDoesItHaveFlight());
                        mergedData.add(mergedCity);
                        break;
                    }
                }
            }

            // Sort the merged data by NumOfDentists in descending order
            mergedData.sort((o1, o2) -> (Integer) o2.get("NumOfDentists") - (Integer) o1.get("NumOfDentists"));

            // Write the merged and sorted data to a new JSON file
            objectMapper.writeValue(new File("src/merged_cities.json"), mergedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}