package com.doomhopes.saveinfoonphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
        textViewLog.setMovementMethod( new ScrollingMovementMethod());

        editText = findViewById(R.id.editText);
    }

    public void OnClickAdd(View view) {

        String str = String.valueOf(editText.getText());
        if(str.contentEquals("")){
            Toast.makeText(this,"Please, enter text", Toast.LENGTH_SHORT).show();
            return;
        }

        FileOutputStream f;
        try{
            f = openFileOutput(FILE_NAME, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            textViewLog.setText(e.getMessage());
            return;
        }

        byte[] buf = str.getBytes();
        try{
            f.write(buf);
            f.flush();
            f.close();
        } catch (IOException e) {
            textViewLog.setText(e.getMessage());
            return;
        }
        textViewLog.setText("Open Ok");

        FileInputStream r;
        try{
            r = openFileInput(FILE_NAME);
        }catch (FileNotFoundException ex){
            textViewLog.setText(ex.getMessage());
            return;
        }

        buf = new byte[1024];
        try{
            r.read(buf);
            r.close();
        }catch (IOException ex){
            textViewLog.setText(ex.getMessage());
            return;
        }
        str = new String(buf);
        textViewRes.setText(str);
    }
}