package com.doomhopes.saveinfoonphone;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

public class DatabaseActivity extends AppCompatActivity {

    TextView textViewLog;
    EditText editText;
    private SQLiteDatabase database; // ресурс базы данных

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        textViewLog = findViewById(R.id.db_log_txt);
        editText = findViewById(R.id.editTextNewSTR);

        database = openOrCreateDatabase("storage.db", MODE_PRIVATE,null);
        String query = "CREATE TABLE IF NOT EXISTS Strings ("+
                "id INTEGER PRIMARY KEY," +
                "str VARCHAR(256)," +
                "moment DATETIME DEFAULT CURRENT_TIMESTAMP)";

        try{
            database.execSQL(query);
        }catch (SQLException ex)
        {
            textViewLog.setText(ex.getMessage());
            return;
        }
        textViewLog.setText("OK!");
    }

    public void OnClickAddStr(View view) {
        String str = String.valueOf(editText.getText());
        if(str.contentEquals(""))
        {
            textViewLog.setText("Please, Enter text");
           // Toast.makeText(this,"Enter text",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}