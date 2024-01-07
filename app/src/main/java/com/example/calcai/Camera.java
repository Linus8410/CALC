package com.example.calcai;

import static android.nfc.NdefRecord.createUri;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.calcai.databinding.CameraBinding;

import java.io.File;

public class Camera extends Activity {
    CameraBinding mainBinding;
    ActivityResultLauncher<Uri>takePictureLauncher;
    Uri imageUri;
    private static final int CAMERA_PERMISSION_CODE=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        mainBinding=CameraBinding.inflate(getLayoutInflater());
        setContentView( mainBinding.getRoot());
        imageUri=createUri();
        registerPictureLauncher();

        mainBinding.btncam.setOnClickListener(View->{

        });
        CheckCameraPermissionAndOpenCamera();


    }
    private Uri createUri() {

        File imageFile=new File(getApplicationContext().getFilesDir(),"camera_photo.jpg");
        return FileProvider.getUriForFile(
                getApplicationContext(),
                "com.example.CameraPermission.fileProvider",
                imageFile
        );
    }
    private void registerPictureLauncher(){
        takePictureLauncher=registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {

                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if (result) {
                                mainBinding.user.setImageURI(null);
                                mainBinding.user.setImageURI(imageUri);
                            }
                        } catch (Exception exception) {
                            exception.getStackTrace();
                        }

                    }
                });
    }

    private ActivityResultLauncher<Uri> registerForActivityResult(ActivityResultContracts.TakePicture takePicture, ActivityResultCallback<Boolean> booleanActivityResultCallback) {
        return null;
    }


    private void CheckCameraPermissionAndOpenCamera() {
        if(ActivityCompat.checkSelfPermission(Camera.this,android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Camera.this,new String[]{android.Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }else {
            takePictureLauncher.launch(imageUri);
        }

    }
    public void onRequestPermissionResult(int requestcode,String[]permissions,int[]grantResults){
        super.onRequestPermissionsResult(requestcode,permissions,grantResults);
        if(requestcode==CAMERA_PERMISSION_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                takePictureLauncher.launch(imageUri);
            }else{
                Toast.makeText(this,"Camera Permission Denied please allow",Toast.LENGTH_SHORT).show();
            }
        }
    }







}







