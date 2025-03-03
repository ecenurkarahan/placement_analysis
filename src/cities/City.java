package cities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private String region;
    private int population;
    private boolean doesItHaveFlight;
    private double distancetoIstanbul;
    public City() {}
    public boolean getDoesItHaveFlight() {
        return this.doesItHaveFlight;
    }

    public void setDoesItHaveFlight(boolean doesItHaveFlight) {
        this.doesItHaveFlight = doesItHaveFlight;
    }

    public double getDistancetoIstanbul() {
        // Coordinates of Istanbul (latitude, longitude)
        double istanbulLatitude = 41.0053;
        double istanbulLongitude = 28.9770;

        // Radius of the Earth in kilometers
        final double R = 6371;

        // Converting latitude and longitude from degrees to radians
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(istanbulLatitude);
        double lon2 = Math.toRadians(istanbulLongitude);

        // Differences in coordinates
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        double distance = R * c;
        this.distancetoIstanbul= distance;
        return distance;
    }

    public void setDistancetoIstanbul(float distancetoIstanbul) {
        this.distancetoIstanbul = distancetoIstanbul;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = Float.parseFloat(latitude);
    }

    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = Float.parseFloat(longitude);
    }

    @JsonProperty("population")
    public void setPopulation(int population) {
        this.population = population;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public float getLatitude() {
        return this.latitude;
    }
    public float getLongitude() {
        return this.longitude;
    }
    public int getPopulation() {
        return this.population;
    }
    public String getRegion() {
        return this.region;
    }

}
