package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

/**
 * Main activity for the ListyCity application.
 * This activity displays a list of cities retrieved from Firestore and allows users
 * to add, edit, and delete cities.
 */
public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    ListView cityList;
    CityArrayAdapter cityAdapter;
    ArrayList<City> dataList;
    int selectedCityPosition = -1;
    private FirebaseFirestore db;
    private CollectionReference citiesRef;

    /**
     * Callback method from {@link AddCityFragment} to add a new city or update an existing one.
     * It updates the local data list and synchronizes the change with Firestore.
     *
     * @param city The city object to be added or updated.
     */
    @Override
    public void addCity(City city) {
        dataList.add(city);
        cityAdapter.notifyDataSetChanged();

        DocumentReference docRef = citiesRef.document(city.getName());
        docRef.set(city);
    }

    /**
     * Initializes the activity, sets up the UI components, and connects to Firestore
     * to listen for real-time updates to the city collection.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}. Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize UI elements
        cityList = findViewById(R.id.city_list);

        db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("cities");

        citiesRef.addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.e("Firestore: ", error.toString());
            }

            if (value != null && !value.isEmpty()) {
                dataList.clear();
                for (QueryDocumentSnapshot snapshot : value) {
                    String name = snapshot.getString("name");
                    String province = snapshot.getString("province");
                    dataList.add(new City(name, province));
                }
                cityAdapter.notifyDataSetChanged();
            }
        });

        dataList = new ArrayList<>();

        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            selectedCityPosition = -1; // Reset to add mode
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCityPosition = position;
            City selectedCity = dataList.get(position);
            // Open fragment in "Edit Mode"
            AddCityFragment.newInstance(selectedCity).show(getSupportFragmentManager(), "Edit City");
        });

        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            City selectedCity = dataList.get(position);
            citiesRef.document(selectedCity.getName()).delete();
            return true;
        });
    }
}
