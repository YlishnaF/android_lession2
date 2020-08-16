package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerDataAdapter adapter;
    private ArrayList<String> listData = new ArrayList<>(Arrays.asList("Night", "Morning", "Day", "Evening", "15°", "18°", "23°", "19°"));
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
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 4);
        adapter = new RecyclerDataAdapter(listData);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
        recyclerView = findViewById(R.id.recyclerView);
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