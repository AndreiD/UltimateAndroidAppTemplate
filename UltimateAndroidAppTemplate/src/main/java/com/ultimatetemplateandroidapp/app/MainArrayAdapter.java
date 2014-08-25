package com.ultimatetemplateandroidapp.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;


public class MainArrayAdapter extends ArrayAdapter<Track> {





    public MainArrayAdapter(Context context, List<Track> tweets) {
        super(context, 0, tweets);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_list_rowlayout, null);
        }


        TextView textView_name = (TextView) view.findViewById(R.id.textView_main_song_name);
        TextView textView_genere = (TextView) view.findViewById(R.id.textView_genere);
        TextView textView_duration = (TextView) view.findViewById(R.id.textView_duration);
        TextView textView_bitrate = (TextView) view.findViewById(R.id.textView_bitrate);

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", 0);
        int old_font_size = prefs.getInt("font_size", 24);
        String old_font_color = prefs.getString("font_color","#000000");
        String old_background_color = prefs.getString("background_color","#FFFFFF");


        int[] colors = new int[]{Color.parseColor(old_background_color), Color.parseColor("#99"+old_background_color.replace("#",""))};

        int colorPos = position % colors.length;
        view.setBackgroundColor(colors[colorPos]);

        Track track = getItem(position);
        String title = " ";
        try {
            title = track.getArtist() + " " + track.getTitle();
        }catch(Exception ex){

        }

        if (title.contains("null")) {
            title = track.getFilename();
        }



        textView_name.setText(title);
        textView_name.setTextColor(Color.parseColor(old_font_color));
        textView_name.setTextSize(TypedValue.COMPLEX_UNIT_DIP,old_font_size);
        textView_genere.setText(track.getGenre());

        Long Millis = Long.valueOf(track.getDuration());

        try {
            textView_duration.setText(String.format(Locale.US, "%d:%d", (Millis % (1000 * 60 * 60)) / (1000 * 60), ((Millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000));
        } catch (Exception ex) {

        }
        textView_bitrate.setText(track.getBitrate().replace("000", "kbps"));


        return view;
    }


}