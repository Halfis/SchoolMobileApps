package com.example.files;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import android.Manifest;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private String filename;
    private String externalPublicPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filename = getResources().getString(R.string.app_name) + ".txt";
        String externalPrivatePath = getExternalFilesDir("subdirectory").getPath() + "/" + filename;
        externalPublicPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath();

        write("internal message", null);
        write("external private message", externalPrivatePath);
        requestPermission();
    }

    private void write(String txt, String path){
        try{
            FileOutputStream fos;
            if(path == null) fos = openFileOutput(filename, MODE_PRIVATE);
            else fos = new FileOutputStream(path);

            fos.write(txt.getBytes());
            fos.close();
            showToast("done" + txt);
        }catch(Exception e){
            showToast(e.getMessage());
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123){
            write("external public message", externalPublicPath);
            finish();
        }
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}