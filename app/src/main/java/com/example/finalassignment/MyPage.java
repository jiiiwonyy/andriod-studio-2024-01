package com.example.finalassignment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class MyPage extends AppCompatActivity {

    private ImageButton pen;
    private TextView name;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);
        setTitle("마이페이지");

        pen = findViewById(R.id.pen);
        name = findViewById(R.id.name);

        pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeName();
            }
        });

    }

    public void changeName(){
        final Dialog loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.change_name);

        Button change = (Button) loginDialog.findViewById(R.id.changeButton);
        Button cancel = (Button) loginDialog.findViewById(R.id.cancelButton2);
        final EditText nicknameEdit = (EditText) loginDialog.findViewById(R.id.nickname);

        change.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String nickname = nicknameEdit.getText().toString().trim();
                name.setText(nickname);
                loginDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                loginDialog.dismiss();
            }
        });
        loginDialog.show();
    }


}


