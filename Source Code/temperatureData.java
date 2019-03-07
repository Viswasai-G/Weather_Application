package Weather_App;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class temperatureData {
    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;

    //Setters and Getters
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString(){
        return " Current Temperature is:"+temp+ " F. \n Maximum Temperature of the day:"+temp_max+" F. \n Minimum Temperature of the day:" +temp_min+ " F. \n Current Pressure reading:" +pressure+ " mb. \n Humidity Index:" +humidity+".";
    }
}
