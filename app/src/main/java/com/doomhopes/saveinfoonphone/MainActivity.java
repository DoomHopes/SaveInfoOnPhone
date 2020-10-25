package com.doomhopes.saveinfoonphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickBtnDB(View view) {
        Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
        startActivity(intent);
    }

    public void OnClickBtnFile(View view) {
        Intent intent = new Intent(MainActivity.this, FileActivity.class);
        startActivity(intent);
    }
}