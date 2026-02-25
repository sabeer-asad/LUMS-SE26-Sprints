package com.example.listycity;

import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
//import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    ListView cityList;
    CityArrayAdapter cityAdapter;
    ArrayList<City> dataList;
    int selectedCityPosition = -1;
    private FirebaseFirestore db;
    private CollectionReference citiesRef;

    @Override
    public void addCity(City city) {
        dataList.add(city);
        cityAdapter.notifyDataSetChanged();

        DocumentReference docRef = citiesRef.document(city.getName());
        docRef.set(city);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        // 1. Initialize UI elements
        cityList = findViewById(R.id.city_list);
//        final EditText cityInput = findViewById(R.id.city_input);
//        final Button btnAdd = findViewById(R.id.button_add);
//        final Button btnConfirm = findViewById(R.id.button_confirm);
//        final Button btnDelete = findViewById(R.id.button_delete);

        // 2. Initialize data and view
//        String[] cities = {"Islamabad", "Karachi", "Lahore", "Multan", "Sialkot", "Faisalabad", "Gujarat", "Quetta", "Bahawalpur", "Peshawar"};
//        String[] provinces = {"CT", "SD", "PJ", "PJ", "PJ", "PJ", "PJ", "PJ", "BL", "PJ", "KPK"};

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
//        for (int i = 0; i < cities.length; i++) {
//            dataList.add(new City(cities[i], provinces[i]));
//        }

        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            selectedCityPosition = -1; // Reset to add mode
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCityPosition = position;
            City selectedCity = dataList.get(position); // [cite: 118]
            // Open fragment in "Edit Mode"
            AddCityFragment.newInstance(selectedCity).show(getSupportFragmentManager(), "Edit City");
        });

        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            City selectedCity = dataList.get(position);
            citiesRef.document(selectedCity.getName()).delete();
            return true;
        });

//        // 3. ADD logic
//        // Show & Hide relevant buttons and fields
//        btnAdd.setOnClickListener(v -> {
//            btnConfirm.setVisibility(View.VISIBLE);
//            cityInput.setVisibility(View.VISIBLE);
//            btnAdd.setVisibility(View.GONE);
//        });
//
//        // Confirm
//        btnConfirm.setOnClickListener(v -> {
//            String cityName = cityInput.getText().toString();
//            if (!cityName.isEmpty()) {
//                dataList.add(new City(cityName, "<province>")); // Add to dynamic array
//                cityAdapter.notifyDataSetChanged(); // Refresh List UI
//                cityInput.setText(""); // Clear input field
//
//                // Switch buttons back
//                btnConfirm.setVisibility(View.GONE);
//                cityInput.setVisibility(View.GONE);
//                btnAdd.setVisibility(View.VISIBLE);
//            }
//        });
//
//        // 4. DELETE Logic
//        // City selection logic
//        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                selectedCityPosition = position;
//                view.setSelected(true);
//            }
//        });
//
//        btnDelete.setOnClickListener(v -> {
//            if (selectedCityPosition != -1) {
//                dataList.remove(selectedCityPosition);
//                cityAdapter.notifyDataSetChanged();
//                selectedCityPosition = -1;
//            }
//        });
    }
}
