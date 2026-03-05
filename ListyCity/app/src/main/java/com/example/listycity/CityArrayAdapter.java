package com.example.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Custom ArrayAdapter for displaying {@link City} objects in a ListView.
 * It maps the city name and province to the corresponding TextViews in the list item layout.
 */
public class CityArrayAdapter extends ArrayAdapter<City> {

    /**
     * Constructs a new CityArrayAdapter.
     *
     * @param context The current context.
     * @param cities  The list of cities to represent in the ListView.
     */
    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    /**
     * Provides a view for an AdapterView (ListView).
     *
     * @param position    The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content,
                    parent, false);
        } else {
            view = convertView;
        }

        City city = getItem(position);
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);

        if (city != null) {
            cityName.setText(city.getName());
            provinceName.setText(city.getProvince());
        }

        return view;
    }
}
