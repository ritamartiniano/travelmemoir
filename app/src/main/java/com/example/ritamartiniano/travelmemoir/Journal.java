package com.example.ritamartiniano.travelmemoir;

public class Journal {


    private String name;
    private String location;
    private String dateOfTravel;
    private String description;

    public Journal(){}

    public Journal(String name,String location, String dateOftravel, String description)
    {
        this.name = name;
        this.location = location;
        this.dateOfTravel = dateOftravel;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateoftravel() {
        return dateOfTravel;
    }

    public void setDateoftravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
    public String getLocation()
    {
        return location;
    }
}
