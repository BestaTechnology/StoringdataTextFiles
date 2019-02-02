package com.example.storingdatatextfiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_read, btn_write;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_write= (Button)findViewById(R.id.btn_save);
        btn_read =(Button)findViewById(R.id.btn_read);
        editText =(EditText)findViewById(R.id.editText);

        btn_read.setOnClickListener(this);
        btn_write.setOnClickListener(this);

    }

    private void savedata() {

        try {

            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext().openFileOutput("myfile.txt",MODE_APPEND));
            writer.write(editText.getText().toString());
            writer.close();
            editText.setText(" ");
            Toast.makeText(getApplicationContext(),"Your data is saved in files",Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }private void readdata() {

        StringBuilder data =null;

        try {
            String receive;
            InputStream inputStream = getApplicationContext().openFileInput("myfile.txt");

           if (inputStream!=null) {
               data = new StringBuilder();
               InputStreamReader read = new InputStreamReader(inputStream);
               BufferedReader buff = new BufferedReader(read);
               while ((receive = buff.readLine()) != null) {
                   data.append(receive);
               }

           }

            Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
editText.setText(data  );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.btn_save :
                savedata();

                break;

            case R.id.btn_read :
                readdata();
                break;
        }
    }
}
