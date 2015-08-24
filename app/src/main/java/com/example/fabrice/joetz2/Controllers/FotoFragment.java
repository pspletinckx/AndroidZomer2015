package com.example.fabrice.joetz2.Controllers;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fabrice.joetz2.MainActivity;
import com.example.fabrice.joetz2.R;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.MetadataChangeSet;

//import android.support.v4.app.Fragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class FotoFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ImageButton fromGallery;
    private ImageButton fromCamera;
    private ImageView uploadVoorbeeld;
    private EditText uploadTitel, uploadBeschrijving;
    private LinearLayout uploadScherm;

    public static FotoFragment newInstance(int sectionNumber) {
        FotoFragment fragment = new FotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FotoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_foto, container, false);
        fromGallery = (ImageButton)rootView.findViewById(R.id.fromGallery);
        fromCamera = (ImageButton) rootView.findViewById(R.id.fromCamera);
        uploadVoorbeeld = (ImageView) rootView.findViewById(R.id.uploadVoorbeeld);
        uploadTitel = (EditText) rootView.findViewById(R.id.uploadTitel);
        uploadBeschrijving = (EditText) rootView.findViewById(R.id.uploadBeschrijving);
        uploadScherm = (LinearLayout)rootView.findViewById(R.id.uploadScherm);


        fromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        fromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFromGallery();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE = 2;
    static final int RESULT_OK = -1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void selectFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            uploadScherm.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            uploadVoorbeeld.setImageBitmap(imageBitmap);
            uploadVoorbeeld.setX(30);
        }

    }
}
