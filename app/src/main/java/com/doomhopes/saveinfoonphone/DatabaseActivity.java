package com.doomhopes.saveinfoonphone;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

public class DatabaseActivity extends AppCompatActivity {

    TextView textViewLog;
    TextView textViewRes;
    EditText editText;
    private SQLiteDatabase database; // ресурс базы данных

    private Runnable showDB;
    private Runnable showLog;
    private Runnable showRes;
    private Runnable installDB;
    private Runnable insertDB;
    private String logText;
    private String resText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        textViewLog = findViewById(R.id.db_log_txt);
        editText = findViewById(R.id.editTextNewSTR);
        textViewRes = findViewById(R.id.textViewRes);
        textViewRes.setMovementMethod(new ScrollingMovementMethod());

        (new Thread(installDB)).start();
    }

    public void OnClickAddStr(View view) {
        String str = String.valueOf(editText.getText());
        if(str.contentEquals(""))
        {
            //textViewLog.setText("Please, Enter text");
            Toast.makeText(this,"Enter text",Toast.LENGTH_SHORT).show();
            return;
        }

        //Insert
        Inserter inserter = new Inserter(str);
        (new Thread(inserter)).start();

        //select
        (new Thread(showDB)).start();

        editText.setText(" ");
    }

    public DatabaseActivity() {
        super();
        showDB = () ->{
            if(database == null) database = openOrCreateDatabase("storage.db", MODE_PRIVATE,null);

            String txt = "";
            String query = "SELECT * FROM Strings";
            Cursor res = null;

            try{
                res = database.rawQuery(query,null);
            }catch (SQLException ex)
            {
                textViewLog.setText(ex.getMessage());
                return;
            }
            boolean hasNext = res.moveToFirst();
            while(hasNext)
            {
                txt+= res.getInt(res.getColumnIndex("id")) + " " +
                        res.getString(res.getColumnIndex("str")) + "\n";
                hasNext = res.moveToNext();
            }
            //textViewRes.setText(txt);
            //textViewLog.setText("Select OK");
            resText = txt;
            logText = "Select OK";
            runOnUiThread(showLog);
            runOnUiThread(showRes);
        };

        showLog = ()->{
            textViewLog.setText(logText);
        };

        showRes = () ->{
            textViewRes.setText(resText);
        };

        installDB = () ->{
            if(database == null) database = openOrCreateDatabase("storage.db", MODE_PRIVATE,null);
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
            (new Thread(showDB)).start();
        };
    }

    class Inserter implements Runnable{
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        public Inserter(String value) {
            this.value = value;
        }

        @Override
        public void run() {
            String query = "INSERT INTO Strings(str) VALUES('"+ value +"')";

            try{
                if(database == null) database = openOrCreateDatabase("storage.db", MODE_PRIVATE,null);
                database.execSQL(query);
            }catch (SQLException ex)
            {
                // textViewLog.setText(ex.getMessage());
                logText = ex.getMessage();
                runOnUiThread(showLog);
                return;
            }
            logText = "Insert OK";
            runOnUiThread(showLog);
        }
    }
}







