package com.doomhopes.saveinfoonphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileActivity extends AppCompatActivity {

    private TextView textViewLog;
    private TextView textViewRes;
    private EditText editText;

    private final String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        textViewLog = findViewById(R.id.textViewFileLog);
        textViewRes = findViewById(R.id.textViewResFile);
        textViewRes.setMovementMethod( new ScrollingMovementMethod());

        editText = findViewById(R.id.editText);
    }

    public void OnClickAdd(View view) {
        FileOutputStream f;
        try{
            f = openFileOutput(FILE_NAME, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            textViewLog.setText(e.getMessage());
            return;
        }
        textViewLog.setText("Open Ok");
    }
}