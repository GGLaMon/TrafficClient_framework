package com.mad.trafficclient.bean;

public class RoadLight {
    private int roadId;
    private int red;
    private int green;
    private int yellow;
    public RoadLight(int roadId, int red, int green, int yellow) {
        this.roadId = roadId;
        this.red = red;
        this.green = green;
        this.yellow = yellow;
    }
    public int getRoadId() {
        return roadId;
    }
    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }
    public int getRed() {
        return red;
    }
    public void setRed(int red) {
        this.red = red;
    }
    public int getGreen() {
        return green;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public int getYellow() {
        return yellow;
    }
    public void setYellow(int yellow) {
        this.yellow = yellow;
    }
}