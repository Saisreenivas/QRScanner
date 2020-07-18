package com.example.saisreenivas.qrscanner;

/**
 * Created by Sai sreenivas on 11/5/2016.
 */

public class Feed{
    private int _id;
    private String _date;
    private String _firstName;
    private String _lastName;
    private String _stars;

    public Feed(){

    }
    public Feed(String date, String firstName, String lastName, String stars) {
        this._date = date;
        this._firstName = firstName;
        this._lastName = lastName;
        this._stars = stars;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public void set_stars(String _stars) {
        this._stars = _stars;
    }

    public int get_id() {
        return _id;
    }

    public String get_date() {
        return _date;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public String get_stars() {
        return _stars;
    }
}