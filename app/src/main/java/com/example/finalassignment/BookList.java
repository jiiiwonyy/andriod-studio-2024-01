package com.example.finalassignment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class BookList extends AppCompatActivity {
    private TableLayout frame1, frame2;
    private ImageButton one, two;
    private boolean isLoggedIn;

    public Context mContext;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        frame1=findViewById(R.id.frame1);
        frame2=findViewById(R.id.frame2);
        frame2.setVisibility(View.INVISIBLE);
        setTitle("기말 프로젝트");

        one=findViewById(R.id.one);
        two=findViewById(R.id.two);

        Intent intent = getIntent();
        if(intent!=null) {
            isLoggedIn = intent.getBooleanExtra("isLoggedIn", false);
        }


    }
    public void onClick(View view){

        int f = view.getId();
        if (f==R.id.one){
            frame1.setVisibility(View.VISIBLE);
            frame2.setVisibility(View.INVISIBLE);
            one.setImageResource(R.drawable.list_type1);
            two.setImageResource(R.drawable.list_type22);
        }
        else if(f==R.id.two){
            frame1.setVisibility(View.INVISIBLE);
            frame2.setVisibility(View.VISIBLE);
            one.setImageResource(R.drawable.list_type12);
            two.setImageResource(R.drawable.list_type2);
        }
    }

    /* 장바구니 담기 메소드 */
    public void cartInput(View view){
        if (isLoggedIn) {
            final int itemId = view.getId();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("장바구니 담기");
            alertDialogBuilder.setMessage("장바구니에 담으시겠습니까?");
            alertDialogBuilder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(BookList.this, Cart.class);
                    intent.putExtra("item_id", itemId);
                    startActivity(intent);
                    Toast.makeText(BookList.this, "장바구니에 담기 완료!", Toast.LENGTH_LONG).show();
                }
            });
            alertDialogBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(BookList.this, "장바구니 담기 취소", Toast.LENGTH_LONG).show();
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            showToast("로그인 후 이용가능합니다.");
        }
    }

/* 메뉴 생성 및 클릭효과 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        mContext = getApplicationContext();
        getMenuInflater().inflate(R.menu.booklist_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_icon);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        if (searchView!=null){
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setQueryHint("검색어를 입력하세요");

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(BookList.this, SearchResult.class);
                    intent.putExtra("query", query);
                    startActivity(intent);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //Toast.makeText(getApplicationContext(), "입력중입니다.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.home){
            Intent intent = new Intent(BookList.this, MainActivity.class);
            startActivity(intent);
            showToast("홈으로 메뉴가 클릭되었습니다");
        }
        else if (id==R.id.booklist){
            Intent intent = new Intent(BookList.this, BookList.class);
            startActivity(intent);
            showToast("도서목록 메뉴가 클릭되었습니다");
        }
        else if (id==R.id.cart){
            if(isLoggedIn) {
                Intent intent = new Intent(BookList.this, Cart.class);
                startActivity(intent);
                showToast("장바구니 메뉴가 클릭되었습니다");
            }
            else{
                showToast("로그인 후 이용가능합니다.");
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
