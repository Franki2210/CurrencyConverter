package com.example.user.cbrinfo.model;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class ValCurs {

    @Attribute
    private String Date;
    @Attribute
    private String name;
    @ElementList(entry = "Valute", inline = true, type = Valute.class)
    private List<Valute> Valute;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Valute> getValute() {
        return Valute;
    }

    public void setValute(List<Valute> valute) {
        Valute = valute;
    }


    @Override
    public String toString() {
        return "ClassPojo [Date = " + Date + ", name = " + name + ", Valute = ]";
    }
}