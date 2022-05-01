package com.friendsit.hellorajbari;

public class HelloModel {
    private String Add;
    private String Cat;
    private String Det;
    private String Ema;
    private String Nam;
    private String Pho;
    private String Tit;

    public HelloModel() {
    }

    public HelloModel(String add, String cat, String det, String ema, String nam, String pho, String tit) {
        Add = add;
        Cat = cat;
        Det = det;
        Ema = ema;
        Nam = nam;
        Pho = pho;
        Tit = tit;
    }

    public String getAdd() {
        return Add;
    }

    public void setAdd(String add) {
        Add = add;
    }

    public String getCat() {
        return Cat;
    }

    public void setCat(String cat) {
        Cat = cat;
    }

    public String getDet() {
        return Det;
    }

    public void setDet(String det) {
        Det = det;
    }

    public String getEma() {
        return Ema;
    }

    public void setEma(String ema) {
        Ema = ema;
    }

    public String getNam() {
        return Nam;
    }

    public void setNam(String nam) {
        Nam = nam;
    }

    public String getPho() {
        return Pho;
    }

    public void setPho(String pho) {
        Pho = pho;
    }

    public String getTit() {
        return Tit;
    }

    public void setTit(String tit) {
        Tit = tit;
    }
}
