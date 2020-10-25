package com.doomhopes.saveinfoonphone;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLData;

public class DatabaseActivity extends AppCompatActivity {

    TextView textViewLog;
    private SQLiteDatabase database; // ресурс базы данных

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        textViewLog = findViewById(R.id.db_log_txt);

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
}