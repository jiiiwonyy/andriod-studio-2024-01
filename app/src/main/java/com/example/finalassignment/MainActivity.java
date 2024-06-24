package com.example.finalassignment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView coverImage;
    private ImageButton bookListButton, loginButton, csViewButton, mypageButton;
    private boolean isImage = true;
    private boolean isLoggedIn = false;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coverImage = findViewById(R.id.coverImage);
        //클릭했을 때 이미지가 변하도록 하는 메소드
        coverImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (isImage) {
                    coverImage.setImageResource(R.drawable.cover01);
                } else {
                    coverImage.setImageResource(R.drawable.cover02);
                }
                isImage = !isImage;
            }
        });

        //도서목록 버튼
        bookListButton = findViewById(R.id.bookListButton);
        bookListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, BookList.class);
                intent.putExtra("isLoggedIn", isLoggedIn); // isLoggedIn 정보를 전달
                launcher.launch(intent);
                showToast("도서목록 버튼이 클릭되었습니다.");
            }
        });

        //고객센터 버튼
        csViewButton = findViewById(R.id.csViewButton);
        csViewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, CustomerCenter.class);
                startActivity(intent);
                showToast("고객센터 버튼이 클릭되었습니다.");
            }
        });

        //마이페이지 버튼
        mypageButton = findViewById(R.id.mypageButton);
        mypageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(isLoggedIn) {
                    Intent intent = new Intent(MainActivity.this, MyPage.class);
                    startActivity(intent);
                    showToast("마이페이지 버튼이 클릭되었습니다.");
                }
                else{
                    showToast("로그인 후 이용가능합니다.");
                }
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        // 로그인 성공 시 로그인 상태 업데이트
                        isLoggedIn = data.getBooleanExtra("isLoggedIn", false);
                    }
                });
    }
    //토스트메시지 출력해주는 함수
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //로그인 팝업
    public void loginClick(View v){
        if(isLoggedIn){
            showToast("이미 로그인 되었습니다.");
        }
        else{
            final Dialog loginDialog = new Dialog(this);
            loginDialog.setContentView(R.layout.login);
            loginDialog.setTitle("로그인 화면");

            Button login = (Button) loginDialog.findViewById(R.id.loginButton);
            Button cancel = (Button) loginDialog.findViewById(R.id.cancelButton);
            final EditText username = (EditText) loginDialog.findViewById(R.id.username);
            final EditText password = (EditText) loginDialog.findViewById(R.id.password);

            login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(username.getText().toString().trim().equals("jiwon")&&password.getText().toString().trim().equals("jiwon123")){
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                        isLoggedIn=true;

                        // 로그인 정보를 Intent에 추가하여 BookList 액티비티로 전달
                        Intent intent = new Intent(MainActivity.this, BookList.class);
                        intent.putExtra("isLoggedIn", isLoggedIn);

                        loginDialog.dismiss();
                    } else{
                        Toast.makeText(getApplicationContext(), "다시 입력하시오", Toast.LENGTH_SHORT).show();
                    }
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


}