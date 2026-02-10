package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity(City city);
    }
    private AddCityDialogListener listener;

    public static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);

        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        // Check if we are EDITING
        City existingCity = null;
        if (getArguments() != null) {
            existingCity = (City) getArguments().getSerializable("city");
            editCityName.setText(existingCity.getName()); // Pre-fill name [cite: 109]
            editProvinceName.setText(existingCity.getProvince()); // Pre-fill province [cite: 110]
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view) // [cite: 217]
                .setTitle(existingCity == null ? "Add a city" : "Edit City") // Dynamic Title [cite: 218]
                .setNegativeButton("Cancel", null) // [cite: 219]
                .setPositiveButton(existingCity == null ? "Add" : "Save", (dialog, which) -> {
                    String cityName = editCityName.getText().toString(); // [cite: 221]
                    String provinceName = editProvinceName.getText().toString(); // [cite: 222]
                    listener.addCity(new City(cityName, provinceName)); // [cite: 223]
                })
                .create();
    }
}
