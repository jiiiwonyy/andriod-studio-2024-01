package com.example.finalassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {

    private TextView cartTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        cartTextView = findViewById(R.id.cartTextView);

        // Intent에서 장바구니 아이템 ID를 읽어옴
        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        int itemId = sharedPreferences.getInt("item_id", -1);

        // ID로 아이템 정보를 가져와서 TextView에 설정 (여기서는 단순히 ID를 표시)
        if (itemId != -1) {
            cartTextView.setText("장바구니 아이템: " + itemId);
        } else {
            cartTextView.setText("장바구니가 비어 있습니다");
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

