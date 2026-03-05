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

/**
 * A DialogFragment used to add a new city or edit an existing one.
 * It provides a form with fields for city name and province.
 */
public class AddCityFragment extends DialogFragment {

    /**
     * Interface to be implemented by the activity or fragment that hosts this dialog.
     * It allows the dialog to communicate back to the host when a city is added or updated.
     */
    interface AddCityDialogListener {
        /**
         * Callback method triggered when the user adds or updates a city.
         *
         * @param city The city object that was added or updated.
         */
        void addCity(City city);
    }

    private AddCityDialogListener listener;

    /**
     * Creates a new instance of AddCityFragment with an existing city object for editing.
     *
     * @param city The city object to be edited.
     * @return A new instance of AddCityFragment.
     */
    public static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);

        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called when the fragment is first attached to its context.
     * Ensures that the host activity implements {@link AddCityDialogListener}.
     *
     * @param context The context the fragment is being attached to.
     * @throws RuntimeException if the context does not implement {@link AddCityDialogListener}.
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    /**
     * Build the dialog for the fragment. Sets up the layout, populates fields if editing,
     * and handles button clicks.
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     *                           or null if this is a new instance.
     * @return A new Dialog instance to be displayed by the Fragment.
     */
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
            editCityName.setText(existingCity.getName()); // Pre-fill name
            editProvinceName.setText(existingCity.getProvince()); // Pre-fill province
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle(existingCity == null ? "Add a city" : "Edit City") // Dynamic Title
                .setNegativeButton("Cancel", null)
                .setPositiveButton(existingCity == null ? "Add" : "Save", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    listener.addCity(new City(cityName, provinceName));
                })
                .create();
    }
}
