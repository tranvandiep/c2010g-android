package com.gokisoft.c2010g.note;

import android.database.Cursor;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    int _id;
    String noidung;
    //quantrong = true -> 1, false -> 0 (sqlite)
    boolean quantrong;
    //Chuyen date -> string
    Date ngaytao;

    public Note() {
    }

    public Note(String noidung, boolean quantrong, Date ngaytao) {
        this.noidung = noidung;
        this.quantrong = quantrong;
        this.ngaytao = ngaytao;
    }

    public Note(int _id, String noidung, boolean quantrong, Date ngaytao) {
        this._id = _id;
        this.noidung = noidung;
        this.quantrong = quantrong;
        this.ngaytao = ngaytao;
    }

    public void setData(Cursor cursor) {
        this._id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        this.noidung = cursor.getString(cursor.getColumnIndexOrThrow("noidung"));
        this.quantrong = cursor.getInt(cursor.getColumnIndexOrThrow("quantrong")) == 1?true:false;
        String ngaytaoStr = cursor.getString(cursor.getColumnIndexOrThrow("ngaytao"));

        this.ngaytao = convertStringToDate(ngaytaoStr);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public boolean isQuantrong() {
        return quantrong;
    }

    public void setQuantrong(boolean quantrong) {
        this.quantrong = quantrong;
    }

    public int getQuantrong() {
        return quantrong?1:0;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getNgaytaoStr() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(ngaytao);

        return s;
    }

    public Date convertStringToDate(String s) {
        Date myDate= new Date();
        try {
            myDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return myDate;
    }
}
