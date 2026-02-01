package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedCityPosition = -1;

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
        final EditText cityInput = findViewById(R.id.city_input);
        final Button btnAdd = findViewById(R.id.button_add);
        final Button btnConfirm = findViewById(R.id.button_confirm);
        final Button btnDelete = findViewById(R.id.button_delete);

        // 2. Initialize data and view
        String[] cities = {"Islamabad", "Karachi", "Lahore", "Multan", "Sialkot", "Faisalabad", "Gujrat", "Quetta", "Bahwalpur", "Peshawar"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // 3. ADD logic
        // Show & Hide relevant buttons and fields
        btnAdd.setOnClickListener(v -> {
            btnConfirm.setVisibility(View.VISIBLE);
            cityInput.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
        });

        // Confirm
        btnConfirm.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString();
            if (!cityName.isEmpty()) {
                dataList.add(cityName); // Add to dynamic array
                cityAdapter.notifyDataSetChanged(); // Refrest List UI
                cityInput.setText(""); // Clear input field

                // Switch buttons back
                btnConfirm.setVisibility(View.GONE);
                cityInput.setVisibility(View.GONE);
                btnAdd.setVisibility(View.VISIBLE);
            }
        });

        // 4. DELETE Logic
        // City selection logic
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedCityPosition = position;
                view.setSelected(true);
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (selectedCityPosition != -1) {
                dataList.remove(selectedCityPosition);
                cityAdapter.notifyDataSetChanged();
                selectedCityPosition = -1;
            }
        });
    }
}