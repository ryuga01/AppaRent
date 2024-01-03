package com.example.apparent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apparent.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddApartmentView extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private EditText landLordNameEdt, landLordDescription, landLordAddressEdt;
    private Button sendDatabtn;




    private Button uploadBtn;

    private ImageView imageView;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    // creating a variable for
    // our object class
    ApartmentUnit apartmentUnit;


    ActivityMainBinding binding;
    Uri filePath;
    ProgressDialog progressDialog;
    // request code
    private final int PICK_IMAGE_REQUEST = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apartment_view);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        // initializing our edittext and button
        landLordNameEdt = (EditText) findViewById(R.id.idEditApartmentName);
        landLordDescription = (EditText) findViewById(R.id.editTextDescription);
        landLordAddressEdt = (EditText) findViewById(R.id.idEditAddress);
        sendDatabtn = (Button) findViewById(R.id.idBtnSendData);
        uploadBtn =  (Button) findViewById(R.id.idBtnPickImage);
        imageView = (ImageView) findViewById(R.id.idBtnImage);


        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference();

        // initializing our object
        // class variable.
        apartmentUnit = new ApartmentUnit();

        sendDatabtn = (Button) findViewById(R.id.idBtnSendData);

        // choose item
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
            }


        });

        // Select Image method



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
                    uploadImage();
                    addDatatoFirebase(name, phone, address);
                }
            }
        });
    }
    private void SelectImage()
    {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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



    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            System.out.println("Files goes here: "+ );
                            Toast.makeText(AddApartmentView.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddApartmentView.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }


}