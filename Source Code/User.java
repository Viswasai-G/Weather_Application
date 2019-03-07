package Weather_App;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class User {

    private String name;

    //Validation conditions for the attribute 'zip'.
    @NotNull
    @Min(value = 10000, message = "Zipcode must be valid.")
    @Max(value = 99999, message = "Zipcode must be valid.")
    private int zip;

    //Validation conditions for the attribute 'emailid'.
    @NotNull(message = "Must not be null.")
    @Email(message = "Must be a valid email address.")
    private String emailid;


    private weatherData weather;


    //Setters and Getters.
    
    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public weatherData getWeather() {
        return weather;
    }

    public void setWeather(weatherData weather) {
        this.weather = weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public int getZip() {
        return zip;
    }

    public String toString(){
        return "Name: "+name +" ZIP:" +zip+" Email:"+emailid+" weather details:"+weather.toString();
    }
}

