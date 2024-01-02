package com.example.apparent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddApartmentView extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private EditText landLordNameEdt, landLordDescription, landLordAddressEdt;
    private Button sendDatabtn;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    ApartmentUnit apartmentUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apartment_view);

        // initializing our edittext and button
        landLordNameEdt = (EditText) findViewById(R.id.idEditApartmentName);
        landLordDescription = (EditText) findViewById(R.id.editTextDescription);
        landLordAddressEdt = (EditText) findViewById(R.id.idEditAddress);
        sendDatabtn = (Button) findViewById(R.id.idBtnSendData);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference();

        // initializing our object
        // class variable.
        apartmentUnit = new ApartmentUnit();

        sendDatabtn = (Button) findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String name = landLordNameEdt.getText().toString();
                String phone = landLordAddressEdt.getText().toString();
                String address = landLordAddressEdt.getText().toString();
                // below line is for checking whether the
                // edittext fields are empty or not.
                if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {

                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(AddApartmentView.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(name, phone, address);
                }
            }
        });
    }

    public void addDatatoFirebase(String name, String phone, String address) {
        // below 3 lines of code is used to set
        // data in our object class.
        apartmentUnit.setName(name);
        apartmentUnit.setDescription(phone);
        apartmentUnit.setAddress(address);
        System.out.println("DATA ADDED");
        // this will create a table specifically only for landLord
        DatabaseReference landLord = databaseReference.child("ApartmentUnit");
        DatabaseReference newPostRef = landLord.push();
        // add data
        newPostRef.setValue(apartmentUnit);
        System.out.println("oh it addssss");
    }
}