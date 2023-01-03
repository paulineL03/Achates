package com.example.achates1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageHelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

import java.io.IOException;
import java.util.List;

public class faceDetection extends AppCompatActivity {
private int REQUEST_PICK_IMAGE=1000;


    private ImageView imageBox;
    private TextView output;
    private ImageLabeler imageLabeler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facedetection);
        imageBox=findViewById(R.id.imageBox);
        output=findViewById(R.id.output);
        imageLabeler= ImageLabeling.getClient(new ImageLabelerOptions.Builder()
                .setConfidenceThreshold(0.7f)
                .build()
        );

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d(faceDetection.class.getSimpleName(), "grant result for " +permissions[0] + " is " + grantResults[0]);
    }

    public void onPickImage(View view){
        Intent i= new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");

        startActivityForResult(i, REQUEST_PICK_IMAGE);
    }
    public void onStartCamera(View view){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK ){
            if (requestCode== REQUEST_PICK_IMAGE){
                Uri uri= data.getData();
                Bitmap bitmap=loadUri(uri);
                imageBox.setImageBitmap(bitmap);
                classifyImage(bitmap);
            }
        }
    }
    private Bitmap loadUri(Uri uri){
        Bitmap bitmap=null;

        try {
            if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O_MR1){
                ImageDecoder.Source source= ImageDecoder.createSource(getContentResolver(), uri);
                bitmap=ImageDecoder.decodeBitmap(source);
            }else{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }
    private void classifyImage(Bitmap bitmap){
        InputImage inputImage=InputImage.fromBitmap(bitmap, 0);
        imageLabeler.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
            @Override
            public void onSuccess(List<ImageLabel> imageLabels) {
                if(imageLabels.size()>0){
                    StringBuilder builder= new StringBuilder();
                    for(ImageLabel label : imageLabels){
                        builder.append(label.getText()).append(".").append(label.getConfidence()).append("\n");
                    }
                    output.setText(builder.toString());
                }else{
                    output.setText("An alien maybe.");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }
}