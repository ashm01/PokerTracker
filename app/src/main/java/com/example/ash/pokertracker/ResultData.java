package com.example.ash.pokertracker;

public class ResultData {

    private int _id;
    private String _name;
    private String _type;
    private int _buyIn;
    private int _return;

    public ResultData(String name, String type, int buyIn, int returnAmount) {
        this._name = name;
        this._type = type;
        this._buyIn = buyIn;
        this._return = returnAmount;
    }

    public ResultData(){

    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_buyIn(int _buyIn) {
        this._buyIn = _buyIn;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public void set_return(int _return) {
        this._return = _return;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public int get_buyIn() {
        return _buyIn;
    }

    public String get_type() {
        return _type;
    }

    public int get_return() {
        return _return;
    }
}
