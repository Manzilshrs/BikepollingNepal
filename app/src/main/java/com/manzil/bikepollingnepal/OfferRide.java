package com.manzil.bikepollingnepal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Manjil on 2/6/2018.
 */

public class OfferRide {
  private  String date, bikename, bikenumber, time, cost, location, destination;
   private List<String> nearby;

    public OfferRide(String date, String bikename, String bikenumber, String time, String cost, String location, String destination, List<String> nearby) {
        this.date = date;
        this.bikename = bikename;
        this.bikenumber = bikenumber;
        this.time = time;
        this.cost = cost;
        this.location = location;
        this.destination = destination;
        this.nearby = nearby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBikename() {
        return bikename;
    }

    public void setBikename(String bikename) {
        this.bikename = bikename;
    }

    public String getBikenumber() {
        return bikenumber;
    }

    public void setBikenumber(String bikenumber) {
        this.bikenumber = bikenumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getNearby() {
        return nearby;
    }

    public void setNearby(List<String> nearby) {
        this.nearby = nearby;
    }
}
