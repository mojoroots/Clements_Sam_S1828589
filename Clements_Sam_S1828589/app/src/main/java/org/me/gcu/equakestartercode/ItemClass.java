//Clements_Sam_S1828589
package org.me.gcu.equakestartercode;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.lang.Float.floatToIntBits;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static org.me.gcu.equakestartercode.ItemKeeper.itemData;
import static org.me.gcu.equakestartercode.ItemKeeper.itemDates3;
import static org.me.gcu.equakestartercode.ItemKeeper.itemDepth;
import static org.me.gcu.equakestartercode.ItemKeeper.itemDescription;
import static org.me.gcu.equakestartercode.ItemKeeper.itemGeoLat;
import static org.me.gcu.equakestartercode.ItemKeeper.itemGeoLong;
import static org.me.gcu.equakestartercode.ItemKeeper.itemLink;
import static org.me.gcu.equakestartercode.ItemKeeper.itemLong;
import static org.me.gcu.equakestartercode.ItemKeeper.itemMag;
import static org.me.gcu.equakestartercode.ItemKeeper.itemPubDate;
import static org.me.gcu.equakestartercode.ItemKeeper.itemShort;
import static org.me.gcu.equakestartercode.ItemKeeper.itemTitles;

public class ItemClass {
    public static Object magA;
    private String title;
    private String description;
    private String link;
    private String pubDate;
    public String date;
    private String category;
    private String geoLat;
    private String geoLong;
    private Float Mag;
    public Date quakeDate;
    public Date quakeDateS;
    public int depthNum;

    ArrayList<Float> arrayMag = new ArrayList<Float>();
    ArrayList<Float> arrayMag2 = new ArrayList<Float>();
    ArrayList<Float> north = new ArrayList<Float>();


    public ItemClass() {
        title = "";
        description = "";
        link = "";
        pubDate = "";
        category = "";
        geoLat = "";
        geoLong = "";
        date = "";
        Mag = 0.0f;
    }

    public ItemClass(String aTitle, String aDescription, String aLink, String aPubDate, String aCategory, String aGeoLat, String aGeoLong, Float aMag) {
        title = aTitle;
        description = aDescription;
        link = aLink;
        pubDate = aPubDate;
        category = aCategory;
        geoLat = aGeoLat;
        geoLong = aGeoLong;
        Mag = aMag;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String aTitle) {
        title = aTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String aLink) {
        link = aLink;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String aPubDate) {
        pubDate = aPubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String aCategory) {
        category = aCategory;
    }

    public String getLat() {
        return geoLat;
    }

    public void setGeoLat(String aGeoLat) {
        geoLat = aGeoLat;
    }

    public String getLong() {
        return geoLong;
    }

    public void setGeoLong(String aGeoLong) {
        geoLong = aGeoLong;
    }

    public float getMag() {
        return Mag;
    }

    public void setMag(Float aMag) {
        Mag = aMag;
    }

    public String toString() {
        String temp;
        // String longGeo;


        temp = title + " " + description + " " + link + " " + pubDate + " " + category + " " + geoLat + " " + geoLong;


        return temp;
    }

    public String onlyTitle() {
        String temp;

        temp = title;

        return temp;
    }


    public String locationStrength() {
        String temp;


        temp = category + " " + geoLat + " " + geoLong;


        return temp;
    }


    public String DisTitle() {
        String temp;
        String part1;
        temp = description;
        String[] values = temp.split(";");


        part1 = values[1];
        return part1;
    }

    public Date Displayinfo() {
        quakeDate();
        String temp;
        temp = description;
        String[] values = temp.split(";");
        String[] magFloat = values[4].split(":");
        String depth = values[3].replaceAll("\\D+", "");
        String mag = values[4].replaceAll("\\D", "");
        String allInfo;
        allInfo = title + " " + description + " " + " " + link + " " + pubDate + " " + category + " " + geoLat + " " + geoLong;
        String importantStuff;
        importantStuff = "\n" + values[1] + "\n" + values[4] + "\nLat: " + geoLat + "\nLong: " + geoLong + "\npubDate: " + pubDate ;


        String shortInfo;
        shortInfo = "\n" + values[4];
        String shortStack;
        shortStack = "\n" + title + "\n" + quakeDate ;
        Date shortDate;
        shortDate = quakeDate;
        float magTemp = parseFloat(magFloat[1]);
        depthNum = parseInt(depth);

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            Date dQS = sdf.parse(pubDate);


            quakeDateS = dQS;
            System.out.println("QuakeDate"+quakeDate);
        } catch (ParseException e) {

            Log.e("READ", "Quake date missing");
        }




        itemMag.add(Mag);
        itemTitles.add(title);
        itemDescription.add(description);
        itemLink.add(link);
        itemPubDate.add(pubDate);
        itemGeoLat.add(geoLat);
        itemGeoLong.add(geoLong);
        itemData.add(importantStuff);
        itemShort.add(shortStack);
        itemDepth.add(depthNum);






        return shortDate;
       // return importantStuff;





    }

    public Date quakeDate ()
    {
        DateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
        try {
            Date dQ = sdf.parse(pubDate);

            itemDates3.add(dQ);
            quakeDate = dQ;
            System.out.println("QuakeDate"+quakeDate);
        } catch (ParseException e) {

            Log.e("READ", "Quake date missing");
        }
        return quakeDate;
    }
/*
    public String DateInfo()
    {
        String dateSearchInfo;
        dateSearchInfo =  "\n Mag" +Mag + "\nLat: " + geoLat + "\nLong: " + geoLong +"\nDepth: " + depthNum + "\nDate: " + quakeDateS;
        if (quakeDate().after(MainActivity.firstEntryDate) || quakeDate().equals(MainActivity.firstEntryDate) && quakeDate().before(MainActivity.secondEntryDate)||quakeDate().equals(MainActivity.secondEntryDate) ) {
            return dateSearchInfo;
        }
        else
            return "Data could not be found";

    }

*/

    public ArrayList<Float> getArrayMag() {
        return arrayMag;
    }




    }






