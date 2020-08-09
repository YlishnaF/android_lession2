package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static int requestCode = 1;
    private TextView textView;
    private Button changeLctBtn;
    private final String choosenLct = "choosenLct";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        changeLocation();

    }

    private void changeLocation() {
        changeLctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, requestCode);

            }
        });
    }

    private void findViews(){
        changeLctBtn = findViewById(R.id.changeLctBtn);
        textView = findViewById(R.id.location);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MainActivity.requestCode && resultCode == RESULT_OK && data != null){
            String strData = data.getStringExtra(SecondActivity.dataKey);
            textView.setText(strData);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        Toast.makeText(this,"onSaveInstanceState", Toast.LENGTH_SHORT).show();
        String text = textView.getText().toString();
        saveInstanceState.putString(choosenLct, text);
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String text = savedInstanceState.getString(choosenLct);
        textView.setText(text);
    }
}