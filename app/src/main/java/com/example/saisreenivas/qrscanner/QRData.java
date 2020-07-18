package com.example.saisreenivas.qrscanner;

/**
 * Created by Sai sreenivas on 10/13/2016.
 */

public class QRData {
    private int _id;
    private String _codeformat;
    private String _data;
    public QRData()
    {
    }

    public QRData(String codeformat,String data){
        this._codeformat = codeformat;
        this._data = data;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_codeformat(String _codeformat) {
        this._codeformat = _codeformat;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public int get_id() {
        return _id;
    }

    public String get_codeformat() {
        return _codeformat;
    }

    public String get_data() {
        return _data;
    }
}

