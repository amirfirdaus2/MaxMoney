package com.example.devspeks.maxmoney.Activity.Verification;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devspeks.maxmoney.Activity.LoginActivity;
import com.example.devspeks.maxmoney.Activity.LoginActivity_old;
import com.example.devspeks.maxmoney.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CddActivity extends AppCompatActivity {
    private int GALLERY_DOCUMENT_FRONT = 11;
    private int CAMERA_DOCUMENT_FRONT = 12;
    private int GALLERY_DOCUMENT_BACK = 21;
    private int CAMERA_DOCUMENT_BACK = 22;
    private Uri outPutfileUri;
    ImageView imageView_documentFront,imageView_documentBack,imageView_back,imageView_cancel;
    private Bitmap imageFirst,imageSecond;
    String filePATH1,filePATH2;
    TextView textView_v1,textView_v2,textView_v3,textView_name,textView_passport,textView_cdd;
    Button button_submit;
    Intent intent_next,intent_back,intent_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdd);

        ActivityCompat.requestPermissions(CddActivity.this,
                new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.RECORD_AUDIO},
                1);

        //declare
        textView_v1 = findViewById(R.id.textView_v1);
        textView_v2 = findViewById(R.id.textView_v2);
        textView_v3 = findViewById(R.id.textView_v3);
        textView_cdd = findViewById(R.id.textView_cdd);
        textView_name = findViewById(R.id.textView_name);
        textView_passport = findViewById(R.id.textView_passport);

        imageView_documentFront = findViewById(R.id.imageView_documentFront);
        imageView_documentBack = findViewById(R.id.imageView_documentBack);
        imageView_back = findViewById(R.id.imageView_back);
        imageView_cancel = findViewById(R.id.imageView_cancel);

        button_submit = findViewById(R.id.button_submit);

        intent_back = new Intent(getApplicationContext(), AccountVerificationActivity.class);
        intent_cancel = new Intent(getApplicationContext(), LoginActivity.class);
        intent_next = new Intent(getApplicationContext(), VideoVerificationActivity.class);

        //font type
        ChangeFontType();

        //on click image
        imageView_documentFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog("F");
            }
        });
        imageView_documentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog("B");
            }
        });
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_back);
            }
        });
        imageView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_cancel.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_cancel);
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_next);
            }
        });

    }

    private void ChangeFontType() {
        textView_v1.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_v2.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        textView_v3.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_cdd.setTypeface(Typeface.createFromAsset(getAssets(),"Avenir-Roman-12.ttf"));
        textView_name.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        textView_passport.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
        button_submit.setTypeface(Typeface.createFromAsset(getAssets(), "Avenir-Roman-12.ttf"));
    }

    private void showPictureDialog(final String s){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" ,
                "View Image"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                String ss = s+"G";
                                choosePhotoFromGallery(ss);
                                break;
                            case 1:
                                String sss = s+"C";
                                takePhotoFromCamera(sss);
                                break;
                            case 2:
                                if(imageFirst!= null || imageSecond!= null){
                                    if(s.equals("F")){
                                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CddActivity.this);
                                        View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                                        PhotoView photoView = mView.findViewById(R.id.imageView);
                                        photoView.setImageBitmap(imageFirst);
                                        mBuilder.setView(mView);
                                        AlertDialog mDialog = mBuilder.create();
                                        mDialog.show();
                                    }else if(s.equals("B")){
                                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CddActivity.this);
                                        View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                                        PhotoView photoView = mView.findViewById(R.id.imageView);
                                        photoView.setImageBitmap(imageSecond);
                                        mBuilder.setView(mView);
                                        AlertDialog mDialog = mBuilder.create();
                                        mDialog.show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"choose image first",Toast.LENGTH_LONG).show();
                                }
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery(String s) {
        int SEND = 0;
        if(s.equals("FG")){
            SEND = GALLERY_DOCUMENT_FRONT;
        } else if(s.equals("BG")){
            SEND = GALLERY_DOCUMENT_BACK;
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, SEND);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void takePhotoFromCamera(String s){
        Intent intent = null;
        int SEND = 0;
        if(s.equals("FC")){
            intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "documentFront.jpg");
            outPutfileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            SEND = CAMERA_DOCUMENT_FRONT;
        }else if(s.equals("BC")){
            intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "documentBack.jpg");
            outPutfileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            SEND = CAMERA_DOCUMENT_BACK;
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, SEND);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_DOCUMENT_FRONT){
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    imageFirst = bitmap;
                    imageView_documentFront.setImageBitmap(bitmap);
                    filePATH1 = getStringImage(imageFirst);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(requestCode == CAMERA_DOCUMENT_FRONT){
            try {
                Bitmap thumbnail  = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri);
                Bitmap scaled = Bitmap.createScaledBitmap(thumbnail, 920, 576, true);
                imageFirst = scaled;
                Drawable d = new BitmapDrawable(getResources(), scaled);
                imageView_documentFront.setImageDrawable(d);
                filePATH1 = getStringImage(imageFirst);
                File file = new File(getFilePath(outPutfileUri));
                file.delete();
                this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(getFilePath(outPutfileUri)))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode == GALLERY_DOCUMENT_BACK){
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    imageSecond = bitmap;
                    imageView_documentBack.setImageBitmap(bitmap);
                    filePATH2 = getStringImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(requestCode == CAMERA_DOCUMENT_BACK){
            try {
                Bitmap thumbnail  = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri);
                Bitmap scaled = Bitmap.createScaledBitmap(thumbnail, 920, 576, true);
                imageSecond = scaled;
                Drawable d = new BitmapDrawable(getResources(), scaled);
                imageView_documentBack.setImageDrawable(d);
                filePATH2 = getStringImage(imageSecond);
                File file = new File(getFilePath(outPutfileUri));
                file.delete();
                this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(getFilePath(outPutfileUri)))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFilePath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(projection[0]);
            String picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();
            return picturePath;
        }
        return null;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageString;
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}
