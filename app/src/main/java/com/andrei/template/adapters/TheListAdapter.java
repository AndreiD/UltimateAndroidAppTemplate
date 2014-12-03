package com.andrei.template.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrei.template.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TheListAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    private JSONArray data;
    Context context;

    public TheListAdapter(Context ctx, JSONArray data) {
        this.data = data;
        this.context = ctx;


    }


    @Override
    public int getCount() {
        return data.length();
    }

    public void clearData() {
        data = new JSONArray();
    }

    @Override
    public JSONObject getItem(int i) {
        try {
            return data.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.row_the_list, null);
            holder = new ViewHolder();



            holder.textView_symbol = (TextView) convertView.findViewById(R.id.textView_symbol);
            holder.textView_bid = (TextView) convertView.findViewById(R.id.textView_bid);
            holder.textView_ask = (TextView) convertView.findViewById(R.id.textView_ask);
            holder.imageView_bid_direction = (ImageView) convertView.findViewById(R.id.imageView_bid_direction);
            holder.imageView_ask_direction = (ImageView) convertView.findViewById(R.id.imageView_ask_direction);
            holder.button_buy = (Button) convertView.findViewById(R.id.button_buy);
            holder.button_sell = (Button) convertView.findViewById(R.id.button_sell);


            convertView.setTag(holder);


            final ViewHolder finalHolder = holder;


        } else {
            holder = (ViewHolder) convertView.getTag();
            //holder.toggleButton_feed_twixxed.setOnCheckedChangeListener(null);
        }

        JSONObject jObj = new JSONObject();
        try {
            jObj = (JSONObject) data.get(position);
            String currency = jObj.getString("Currency");
            String bid = jObj.getString("Bid");
            String ask = jObj.getString("Ask");
            String high = jObj.getString("High");
            String low = jObj.getString("Low");
            String changeorientation = jObj.getString("ChangeOrientation");
            String change = jObj.getString("Change");
            String changepercentage = jObj.getString("ChangePercentage");
            String date = jObj.getString("Date");
            String displayname = jObj.getString("DisplayName");


            holder.textView_symbol.setText(currency);
            holder.textView_bid.setText(bid);
            holder.textView_ask.setText(ask);

            if(changeorientation.equals("1")){
                holder.imageView_ask_direction.setImageResource(R.drawable.ic_up);
                holder.imageView_bid_direction.setImageResource(R.drawable.ic_up);
                holder.textView_bid.setTextColor(Color.parseColor("#00CC00"));
                holder.textView_ask.setTextColor(Color.parseColor("#00CC00"));
            }
            if(changeorientation.equals("2")){
                holder.imageView_ask_direction.setImageResource(R.drawable.ic_down);
                holder.imageView_bid_direction.setImageResource(R.drawable.ic_down);
                holder.textView_bid.setTextColor(Color.parseColor("#CC0000"));
                holder.textView_ask.setTextColor(Color.parseColor("#CC0000"));
            }
            if(changeorientation.equals("3")){
                holder.imageView_ask_direction.setVisibility(View.INVISIBLE);
                holder.imageView_bid_direction.setVisibility(View.INVISIBLE);
                holder.textView_bid.setTextColor(Color.parseColor("#333333"));
                holder.textView_ask.setTextColor(Color.parseColor("#333333"));
            }




        } catch (JSONException e) {
            Log.e("OMG", "IS NOT JSON OBJECT !?" + e.getLocalizedMessage());
        }

        final ViewHolder finalHolder1 = holder;
        holder.button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Buy with "+ finalHolder1.textView_ask.getText().toString())
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setCanceledOnTouchOutside(true);
                alert.show();

            }
        });

        holder.button_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Sell with "+ finalHolder1.textView_bid.getText().toString())
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setCanceledOnTouchOutside(true);
                alert.show();
            }
        });



        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


    }


    public static class ViewHolder {


        public ImageView imageView_bid_direction;
        public ImageView imageView_ask_direction;
        public TextView textView_symbol;
        public TextView textView_bid;
        public TextView textView_ask;
        public Button button_buy;
        public Button button_sell;





    }


}
