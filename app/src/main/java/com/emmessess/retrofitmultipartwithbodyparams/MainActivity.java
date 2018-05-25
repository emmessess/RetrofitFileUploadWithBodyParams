package com.emmessess.retrofitmultipartwithbodyparams;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btSelectFile,btUplaod;
    Uri uri;
    private static final int REQUEST_CODE = 6384;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btSelectFile = (Button) findViewById(R.id.bt_select);
        btUplaod     = (Button) findViewById(R.id.bt_upload);


        btSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showChooser();

            }
        });

        btUplaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri!=null) {
                    uploadAssignment(uri);
                }else{
                    Toast.makeText(MainActivity.this, "Select File", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, "Select File");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    private void uploadAssignment(Uri fileUri ) {

        File file = FileUtils.getFile(this, fileUri);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(fileUri)),
                        file
                );
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("assignment", file.getName(), requestFile);

        APIService apiService = RestAdapter.getInstance().create(APIService.class);
        Call<AssignmentUpload> call = apiService.uploadMultipleFiles(RestAdapter.createPartFromString("60"),
                RestAdapter.createPartFromString("95932447ea57bac9f374ed4127c8febc"),RestAdapter.createPartFromString("1"),
                RestAdapter.createPartFromString("6"),RestAdapter.createPartFromString("05/25/18"),
                RestAdapter.createPartFromString("06/25/18"),RestAdapter.createPartFromString("Sadiq : Test Android"),
                RestAdapter.createPartFromString("Test Assignment"),body);

        call.enqueue(new Callback<AssignmentUpload>() {
            @Override
            public void onResponse(Call<AssignmentUpload> call, Response<AssignmentUpload> response) {
                Log.e("Uplaod",response.body().getMsg());
            }

            @Override
            public void onFailure(Call<AssignmentUpload> call, Throwable t) {
                Log.e("Uplaod","Failed");

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        uri = data.getData();

                        Log.i("MainActivity", "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI
                            final String path = FileUtils.getPath(this, uri);
                            Toast.makeText(MainActivity.this,
                                    "File Selected: " + path, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Log.e("MainActivity", "File select error", e);
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
