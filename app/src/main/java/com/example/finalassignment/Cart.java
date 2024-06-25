package com.example.finalassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {

    private TextView cartTextView;
    private String bookName;
    private LinearLayout java, ml, linux, unity, buttons;
    private Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        setTitle("장바구니");
        cartTextView = findViewById(R.id.cartTextView);

        java = findViewById(R.id.java_search);
        ml = findViewById(R.id.ml_search);
        linux = findViewById(R.id.linux_search);
        unity = findViewById(R.id.unity_search);
        buttons = findViewById(R.id.buttons);
        buyButton = findViewById(R.id.buyButton);

        // Intent에서 장바구니 아이템 str를 읽어옴
        Intent intent = getIntent();
        if (intent != null){
            bookName = intent.getStringExtra("str");
        }

        if("java".equalsIgnoreCase(bookName)){
            java.setVisibility(View.VISIBLE);
            buttons.setVisibility(View.VISIBLE);

        } else if("ml".equalsIgnoreCase(bookName)){
            ml.setVisibility(View.VISIBLE);
            buttons.setVisibility(View.VISIBLE);

        } else if("linux".equalsIgnoreCase(bookName)){
            linux.setVisibility(View.VISIBLE);
            buttons.setVisibility(View.VISIBLE);

        } else if("unity".equalsIgnoreCase(bookName)){
            unity.setVisibility(View.VISIBLE);
            buttons.setVisibility(View.VISIBLE);
        }
        else {
            cartTextView.setVisibility(View.VISIBLE);
        }

    }

    public void buy(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("책 구매");
        alertDialogBuilder.setMessage("지금담은 책 구매하시겠습니까?");
        alertDialogBuilder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Cart.this, BookList.class);
                startActivity(intent);
                Toast.makeText(Cart.this, "구매 완료", Toast.LENGTH_LONG).show();
            }
        });
        alertDialogBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Cart.this, "구매 취소하셨습니다.", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

