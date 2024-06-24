package com.example.finalassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchResult extends AppCompatActivity {

    private LinearLayout java, ml, linux, unity;
    private TextView searchResultTextView, noBook;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        setTitle("검색 결과");

        String query = getIntent().getStringExtra("query");

        java = findViewById(R.id.java_search);
        ml = findViewById(R.id.ml_search);
        linux = findViewById(R.id.linux_search);
        unity = findViewById(R.id.unity_search);

        searchResultTextView = findViewById(R.id.search_result);
        noBook = findViewById(R.id.nobook);
        searchResultTextView.setText("검색결과 : "+query);

        if("java".equalsIgnoreCase(query)){
            java.setVisibility(View.VISIBLE);
        } else if("ml".equalsIgnoreCase(query)){
            ml.setVisibility(View.VISIBLE);
        } else if("linux".equalsIgnoreCase(query)){
            linux.setVisibility(View.VISIBLE);
        } else if("unity".equalsIgnoreCase(query)){
            unity.setVisibility(View.VISIBLE);
        } else{
            noBook.setVisibility(View.VISIBLE);
        }

    }

}
