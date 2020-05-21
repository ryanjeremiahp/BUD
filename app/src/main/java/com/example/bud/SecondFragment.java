package com.example.bud;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.io.File;
import java.io.IOException;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class SecondFragment extends Fragment {
    private final static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE= 40;
    private Button captureImageBtn, detectTextBtn;
    private ImageView imageView;
    private TextView textView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;
    private Bitmap myBit;
    private FirebaseVisionImage image;
    File output = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        View v = inflater.inflate(R.layout.fragment_second, container, false);


        captureImageBtn = v.findViewById(R.id.capture_image_btn);
        detectTextBtn = v.findViewById(R.id.detect_text_image_btn);
        imageView = v.findViewById(R.id.image_view);
        textView = v.findViewById(R.id.text_display);

        captureImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("");
                dispatchTakePictureIntent();

            }
        });

        detectTextBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detectTextFromImage();
            }


        }));
        return v;

    }

    /*public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }*/


    private void dispatchTakePictureIntent() {



        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }



        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        output = new File (dir, "Test.jpeg");

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));


        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            //Uri imageUri = FileProvider.getUriForFile(getContext(), "com.example.bud.fileprovider", output);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                try {
                    image = FirebaseVisionImage.fromFilePath(getContext(), android.net.Uri.parse(output.getAbsolutePath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageView.setImageURI(android.net.Uri.parse(output.getAbsolutePath()));
            try {
                image = FirebaseVisionImage.fromFilePath(getContext(), android.net.Uri.parse(String.valueOf(output.toURI())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void detectTextFromImage() {

        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        Task<FirebaseVisionText> result = detector.processImage(image).addOnSuccessListener(
                new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                // Task completed successfully
                                // ...
                                displayTextFromImage(firebaseVisionText);
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                        System.out.println("Yes");
                                        Log.d("Error: ", e.getMessage());
                                    }
                                });
        // FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = FirebaseVision.getInstance().getCloudTextRecognizer();   cloud based model.

    }

    public void displayTextFromImage(FirebaseVisionText result) {
        String resultText = result.getText();
        textView.setText(resultText);
        for (FirebaseVisionText.TextBlock block : result.getTextBlocks()) {
            String blockText = block.getText();
            Float blockConfidence = block.getConfidence();
            List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();
            Point[] blockCornerPoints = block.getCornerPoints();
            Rect blockFrame = block.getBoundingBox();
            for (FirebaseVisionText.Line line : block.getLines()) {
                String lineText = line.getText();
                Float lineConfidence = line.getConfidence();
                List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                Point[] lineCornerPoints = line.getCornerPoints();
                Rect lineFrame = line.getBoundingBox();
                for (FirebaseVisionText.Element element : line.getElements()) {
                    String elementText = element.getText();
                    Float elementConfidence = element.getConfidence();
                    List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                    Point[] elementCornerPoints = element.getCornerPoints();
                    Rect elementFrame = element.getBoundingBox();
                }
            }
        }
    }
}