package com.example.restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.restaurante.modelo.Restaurante;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class registrarRestauranteActivity extends AppCompatActivity {
    ImageView imagen;
    TextInputEditText nombre;
    TextInputEditText descripcion;
    TextInputEditText ubicacion;
    Button guardar;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String currentPhotoPath; //Me recupera la url de la imagen
    static final int REQUEST_TAKE_PHOTO = 1;  //Determinar las respuestas
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private StorageReference mStorageRef; //referencia de almacenamiento en firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);
        imagen = findViewById(R.id.imagen);
        nombre = findViewById(R.id.txtnombre);
        descripcion = findViewById(R.id.txtdescripcion);
        ubicacion = findViewById(R.id.txtubicacion);
        guardar = findViewById(R.id.guardar);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {dispatchTakePictureIntent();
            }});

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });
    }

    public void guardarRestaurante(String imagen){

        Restaurante restaurtante = new Restaurante(myRef.push().getKey(), nombre.getText().toString(),descripcion.getText().toString(),0,0,0,imagen);
        myRef.child("restaurante").child(restaurtante.getId()).setValue(restaurtante);


    }

    private File createImageFile() throws IOException {// Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,
                /* prefix */".jpg",
                /* suffix */storageDir      /* directory */);
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }// Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.restaurante",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                Log.d("esta es la ruta",currentPhotoPath);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imagen.setImageURI(Uri.parse(currentPhotoPath));
        }
    }

    private void uploadPhoto() {
        imagen.setDrawingCacheEnabled(true);
        imagen.buildDrawingCache();
        Bitmap bitmap = imagen.getDrawingCache();
        //bitmap = redimensionarImagenMaximo(bitmap, 200, 200);   //redimensionar imagen
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] photoByte=baos.toByteArray();
        String photoName = currentPhotoPath.substring(currentPhotoPath.lastIndexOf("/")+1,currentPhotoPath.length());
        final StorageReference photoReference = mStorageRef.child("restaurantes/"+photoName); //almacena la imagen
        UploadTask uploadTask = photoReference.putBytes(photoByte);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {  //saber si hizo el proceso adecuadamente
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return photoReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    guardarRestaurante(downloadUri.toString());
                    //deleteCache(getApplicationContext());
                    finish();
                } else {
                    Toast.makeText(registrarRestauranteActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
