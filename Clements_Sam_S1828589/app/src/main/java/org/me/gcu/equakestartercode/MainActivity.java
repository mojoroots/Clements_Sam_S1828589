//Clements_Sam_S1828589
package org.me.gcu.equakestartercode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.graphics.Color;
import android.icu.text.IDNA;

import android.util.Log;
import android.util.Range;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import android.view.Menu;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import android.graphics.Color;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.text.ParseException;

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

import static java.lang.Float.max;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.DayOfWeek;
import java.time.MonthDay;

import java.text.DateFormatSymbols;

public class MainActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener
{
    private TextView rawDataDisplay;
    private Button startButton;
    private String result = "";
    private String url1="";
    private String urlSource="http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    public float test;
    private Button MapButton;
    private Button DateButton;
    private Button DateButton2;
    private Button Direction;
    private Button Show;
    private CalendarView CalendarView;
    private DatePicker DateSelector;
    public boolean detail;
    public ListView list;

    ArrayList<Date> arrayList = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    public ArrayList<String> listViewArray;
    public TextView textView;
    public TextView StartDate;
    public TextView EndDate;
    public TextView summary;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter3;
    private ArrayAdapter<Date> arrayAdapter2;
    String[] quakes;
    public static ArrayList<Float> mags = new ArrayList<Float>();
    public static ArrayList<Float> itemMags = new ArrayList<Float>();
    boolean dataGot = false;
  //  private EditText dateEntry1;
   // private EditText dateEntry2;
    public static ArrayList<Date> storedDates = new ArrayList<Date>();
    public DatePickerDialog datePicked;
    public DatePickerDialog datePicked2;
    public DatePicker datepicker;
    public Calendar cal;
    public String dateEntry1;
    public String dateEntry2;
    public Date entryDate;
    public Date entryDate2;
    public Boolean dateCheckTrigger = false;
    public String North;
    public String South;
    public String East;
    public String West;
    public Float Strongest;

    public Date NewDate2;
    public int deepest;
    public int shallowest;
    public static Date firstEntryDate;
    public static Date secondEntryDate;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MyTag","in onCreate");
        // Set up the raw links to the graphical components
        //rawDataDisplay = (TextView)findViewById(R.id.rawDataDisplay);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        MapButton = (Button) findViewById(R.id.MapButton);
        MapButton.setOnClickListener(this);
        MapButton.setEnabled(false);



        //   CalendarView = (CalendarView) findViewById(R.id.Calendar);

       // Calendar.setOnDateChangeListener(CalendarListener);

      //  CalendarView.setEnabled(false);
      //  CalendarView.setVisibility(View.INVISIBLE);
       // DateSelector = (DatePicker) findViewById(R.id.DatePicker);
        //DateSelector.setEnabled(false);
      //  DateSelector.setVisibility(View.INVISIBLE);


        DateButton = (Button)findViewById(R.id.DateButton);
        DateButton.setOnClickListener(this);
        DateButton2 = (Button)findViewById(R.id.DateButton2);
        DateButton2.setOnClickListener(this);
        Show = (Button)findViewById(R.id.DateFind);
        Show.setOnClickListener(this);
        list = findViewById(R.id.List);
        textView = findViewById(R.id.listText);
        StartDate = findViewById(R.id.StartDate);
        summary = findViewById(R.id.summaryText);
        EndDate = findViewById(R.id.EndDate);
        list = (ListView)findViewById(R.id.List);

        arrayList = new ArrayList<Date>();
        storedDates = new ArrayList<Date>();
        arrayList2 = new ArrayList<String>();



        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, ItemKeeper.itemShort);
        arrayAdapter2 = new ArrayAdapter<Date>(getApplicationContext(), R.layout.listview, ItemKeeper.itemDates2);
        arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, ItemKeeper.itemGeoLat);
      //  dateEntry1 = (EditText)findViewById(R.id.dateEntry1);
      //  dateEntry2 = (EditText)findViewById(R.id.dateEntry2);

list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(MainActivity.this,"EarthQuake" + ItemKeeper.itemData.get(position),Toast.LENGTH_LONG).show();

    }
});



        DateButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_WEEK);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                datePicked = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StartDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);















                    }
                }, year, month, day);
                datePicked.show();

                System.out.println("1st Date "+ StartDate.getText().toString() );

            }


        });

        DateButton2.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_WEEK);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                datePicked2 = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        EndDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);




                    }
                }, year, month, day);
                datePicked2.show();
                System.out.println("2nd Date");

            }


        });






        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, ItemKeeper.itemTitles);
//list.setBackgroundColor(Color.parseColor());
        Log.e("MyTag","after startButton");

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View aview) {

        if (aview == startButton) {
            clear();
            dateCheckTrigger = false;

            Log.e("MyTag", "in onClick");
            startProgress();
            Log.e("MyTag", "after startProgress");
            MapButton.setEnabled(true);




        } else if (aview == MapButton) {
            Intent mapIntent = new Intent(this, MapClass.class);
            startActivity(mapIntent);


        }
        else if (aview == Show)
        {
           clear();
            arrayList.clear();
            MapButton.setEnabled(true);
            dateEntry1 = StartDate.getText().toString();
            dateEntry2 = EndDate.getText().toString();
            System.out.println(dateEntry1 +" " + dateEntry2);
            SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);

            try {
                entryDate = SDF.parse(dateEntry1);
                entryDate2 = SDF.parse(dateEntry2);
                System.out.println("Date entry" + dateEntry1 + dateEntry2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            firstEntryDate = entryDate;
            secondEntryDate = entryDate2;
            System.out.println(entryDate + " " + entryDate2);
            dateCheckTrigger = true;
            startProgress();


        }






    }

    public void Search()
    {

    }

    public void DatePicker()
    {


        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        String selectedDate = sdf.format(new Date(CalendarView.getDate()));
        System.out.println(selectedDate);
    }

    public void startProgress()
    {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource)).start();
    } //


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(list.getSelectedItem().equals(1))
        {
            list.setActivated(false);
        }




    }

    public void clear()
    {
        ItemKeeper.itemDates2.clear();
        ItemKeeper.itemDates.clear();
        ItemKeeper.itemDates3.clear();
        ItemKeeper.itemDepth.clear();
        ItemKeeper.itemData.clear();
        ItemKeeper.itemMag.clear();
        ItemKeeper.itemGeoLat.clear();
        ItemKeeper.itemGeoLong.clear();
        ItemKeeper.itemPubDate.clear();
        ItemKeeper.itemDescription.clear();
        ItemKeeper.itemLink.clear();
        ItemKeeper.itemTitles.clear();
        ItemKeeper.itemLong.clear();
        ItemKeeper.itemShort.clear();
    }






    // Need separate thread to access the internet resource over network
    // Other neater solutions should be adopted in later iterations.
    private class Task implements Runnable
    {

        private String url;

        public Task(String aurl)
        {
            url = aurl;
        }
        @Override
        public void run()
        {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";



            Log.e("MyTag","in run");

            try
            {
                Log.e("MyTag","in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                Log.e("MyTag","after ready");

                while ((inputLine = in.readLine()) != null)
                {
                    result = result + inputLine;
                    Log.e("MyTag",inputLine);

                }
                in.close();
            }
            catch (IOException ae)
            {
                Log.e("MyTag", "ioexception in run");
            }



            MainActivity.this.runOnUiThread(new Runnable()
            {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    parseData(result);


                }
/*

*/
            });



            }



        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(MainActivity.this,R.layout.listview,ItemKeeper.itemShort)
        {
            @NonNull
            @Override

            public View getView(int position, @Nullable View changeview, @NonNull ViewGroup parent)
            {

                View myView = super.getView(position, changeview,parent);
                for (int i = 0; i< ItemKeeper.itemData.size();i++)
                {


                    if(ItemKeeper.itemMag.get(position) >= 0 && ItemKeeper.itemMag.get(position)<1)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.LightBlue));
                    }
                    else if (ItemKeeper.itemMag.get(position) >= 1 && ItemKeeper.itemMag.get(position)<2)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                    }

                    else if (ItemKeeper.itemMag.get(position) >= 2 && ItemKeeper.itemMag.get(position)<3)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.Blue));
                    }

                    else if (ItemKeeper.itemMag.get(position) >= 3 && ItemKeeper.itemMag.get(position)<4)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.DarkBlue));
                    }

                }
                return myView;
            }


        };

        ArrayAdapter<Date>arrayAdapter2=new ArrayAdapter<Date>(MainActivity.this,R.layout.listview,ItemKeeper.itemDates2)
        {
            @NonNull
            @Override

            public View getView(int position, @Nullable View changeview, @NonNull ViewGroup parent)
            {

                View myView = super.getView(position, changeview,parent);
                for (int i = 0; i< ItemKeeper.itemData.size();i++)
                {

                    if(ItemKeeper.itemMag.get(position) >= 0 && ItemKeeper.itemMag.get(position)<1)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.LightBlue));
                    }
                    else if (ItemKeeper.itemMag.get(position) >= 1 && ItemKeeper.itemMag.get(position)<2)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                    }

                    else if (ItemKeeper.itemMag.get(position) >= 2 && ItemKeeper.itemMag.get(position)<3)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.Blue));
                    }

                    else if (ItemKeeper.itemMag.get(position) >= 3 && ItemKeeper.itemMag.get(position)<4)
                    {
                        myView.setBackgroundColor(getResources().getColor(R.color.DarkBlue));
                    }

                }
                return myView;
            }


        };

public void depth()
{
    deepest = Collections.max(ItemKeeper.itemDepth);
    shallowest = Collections.min(ItemKeeper.itemDepth);

}



        private void searchDate()
        {



        }

        private void parseData(String dataToParse)
        {
            arrayList.clear();
            ItemClass item = new ItemClass();
            try
            {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput( new StringReader( dataToParse ) );
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    // Found a start tag
                    if(eventType == XmlPullParser.START_TAG)
                    {
                        if(xpp.getName().equalsIgnoreCase("item"))
                        {
                            item = new ItemClass();
                        }
                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("title"))
                        {
                            String text = xpp.nextText();
                            Log.d("MyTag", text);
                            item.setTitle(text);


                        }
                        else
                            // Check which Tag we have
                            if (xpp.getName().equalsIgnoreCase("description"))
                            {
                                String text = xpp.nextText();
                                Log.d("MyTag", text);
                                item.setDescription(text);




                            }
                            else

                                // Check which Tag we have
                                if (xpp.getName().equalsIgnoreCase("link"))
                                {
                                    String text = xpp.nextText();
                                    Log.d("MyTag", text);
                                    item.setLink(text);

                                }
                                else
                                    // Check which Tag we have
                                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                                    {
                                        String text = xpp.nextText();
                                        Log.d("MyTag", text);
                                        item.setPubDate(text);
                                       //String Dates = item.getPubDate();





                                     try {
                                          /*
                                          String dateInString = item.getPubDate();
                                          SimpleDateFormat formatter = new SimpleDateFormat(("dd-MMM-yyy"));
                                          Date date = formatter.parse(dateInString);
                                          System.out.println("Black magic date"+date);
                                          System.out.println(formatter.format(date));
                                          storedDates.add(date);

                                           */
                                         String sDate1=item.getPubDate();
                                         System.out.println(item.getPubDate());
                                         System.out.println("This worked");


                                         SimpleDateFormat df =new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);


                                         Date Dates = df.parse(sDate1);





                                       System.out.println("Parsed Dates" + Dates);
                                         System.out.println(Dates.getClass().getSimpleName());
                                         ItemKeeper.itemDates.add(Dates);




                                         SimpleDateFormat destDf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
                                         String NewDate = destDf.format(Dates);
                                         Date NewDate2 = destDf.parse(NewDate);















                                         }

                                      catch (ParseException e) {
                                          e.printStackTrace();
                                      }






                                    }
                                    else
                                    if(xpp.getName().equalsIgnoreCase("category"))
                                    {
                                        String text = xpp.nextText();
                                        Log.d("MyTag", text);
                                        item.setCategory(text);

                                    }
                                    else
                                    if(xpp.getName().equalsIgnoreCase("Lat"))
                                    {
                                        String text = xpp.nextText();
                                        Log.d("MyTag", "geoLat saved as: " + text);
                                        item.setGeoLat(text);



                                    }
                                    else
                                    if(xpp.getName().equalsIgnoreCase("Long"))
                                    {
                                        String text = xpp.nextText();
                                        Log.d("MyTag", "geoLat saved as: " + text);
                                        item.setGeoLong(text);
                                    }
                    }

                    else if(eventType == XmlPullParser.END_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {

                                String floatmag = item.getDescription();

                                String[] values = floatmag.split(";");
                                String[] magFloat = values[4].split(":");
                                float magTemp = parseFloat(magFloat[1]);
                                item.setMag(magTemp);


                            Log.d("MyTag", "Item: " + item.toString());


                            if (!dateCheckTrigger) {
                                arrayList.add(item.Displayinfo());

                                list.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
                            }
                            else if (dateCheckTrigger && item.quakeDate().after(entryDate) && item.quakeDate().before(entryDate2) || item.quakeDate().after(entryDate) && item.quakeDate().equals(entryDate2) || item.quakeDate().equals(entryDate) && item.quakeDate().before(entryDate2) || item.quakeDate().equals(entryDate) && item.quakeDate().equals(entryDate2) )
                            {


                                arrayList.add(item.Displayinfo());
                                list.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
                                System.out.println("This did work" + entryDate +" " +entryDate2);







                            }








                        }

                    }

                    // Get the next event
                    eventType = xpp.next();

                } // End of while
            }
            catch (XmlPullParserException ae1)
            {
                Log.e("MyTag","Parsing error" + ae1.toString());
            }

            catch (IOException ae1)
            {
                Log.e("MyTag","IO error during parsing");
            }



           System.out.println(itemDepth);

            deepest = Collections.max(itemDepth);
            shallowest = Collections.min(itemDepth);
            Strongest = Collections.max(itemMag);
            System.out.println("Dates" + ItemKeeper.itemDates3);
            System.out.println("Dates2" + ItemKeeper.itemDates2);
            System.out.println("Deep" + deepest +"Shallow" + shallowest);
            Collections.sort(itemGeoLat);
            North = Collections.max(itemGeoLat);
            System.out.println("North" + North);
            South = Collections.min(itemGeoLat);
            System.out.println("South" + South);
            Collections.sort(itemGeoLong);
            East = Collections.max(itemGeoLong);
            West = Collections.min(itemGeoLong);


            summary.setText("Deepest earth quake: "+deepest + "\nShallowest earth quake: "+shallowest+"\nStrongest earth quake: "+Strongest+"\nMost Northen earth quake: "+North
            +"\nMost Southern earth quake: " + South +"\nMost Eastern earth quake: " + East + "\nMost Western earth quake: " + West );







        }

    }

}