package com.holy.simplemall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private final List<Product> ProductList = new ArrayList<>(); // 모든 케이크 리스트
    private int productCount = 5;                                // 현재 추가된 케이크 개수
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 케이크 리스트 초기화
        ProductList.add(new Product("블루베리 케이크", R.drawable.blueberry_cake));
        ProductList.add(new Product("치즈 케이크", R.drawable.cheese_cake));
        ProductList.add(new Product("초코화이트 케이크", R.drawable.chock_white_cake));
        ProductList.add(new Product("초코 케이크", R.drawable.choco_cake));
        ProductList.add(new Product("녹차 케이크", R.drawable.green_tea_cake));
        ProductList.add(new Product("멜론 케이크", R.drawable.melon_cake));
        ProductList.add(new Product("오렌지 케이크", R.drawable.orange_cake));
        ProductList.add(new Product("호박 케이크", R.drawable.pumpkin_cake));
        ProductList.add(new Product("레드벨벳 케이크", R.drawable.red_velvet_cake));
        ProductList.add(new Product("딸기 케이크", R.drawable.strawberry_cake));
        ProductList.add(new Product("고구마 케이크", R.drawable.sweet_potato_cake));

        RecyclerView recyclerProduct = findViewById(R.id.recyclerProduct);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonProfile = findViewById(R.id.buttonProfile);

        //???~~~~~~~~~~~~~`
        // 케이크 어댑터와 연결하기
        ProductsAdapter adapter = new ProductsAdapter();
        recyclerProduct.setAdapter(adapter);
        adapter.submitList(ProductList.subList(0, productCount));


        // 추가 버튼 클릭 시 케이크 추가
        buttonAdd.setOnClickListener(view -> {
            if (productCount < 10) {
                productCount++;
                adapter.submitList(ProductList.subList(0, productCount));
            }
        });

        // 삭제 버튼 클릭 시 케이크 삭제
        buttonDelete.setOnClickListener(view -> {
            if (productCount > 0) {
                productCount--;
                adapter.submitList(ProductList.subList(0, productCount));
            }
        });
        
        // 회원정보 클릭 시 실행 로그인상태랑 아닐때 구분
        buttonProfile.setOnClickListener(view -> {
            // 로그인 여부 확인
            User user = ((App) getApplication()).getCurrentUser();
            //비로그인 상태면 null
            if (user == null) {
                // 비로그인 상태일 때 회원가입 할 지 물어보기
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("회원가입").setMessage("회원이 아닙니다, 회원가입 하시겠습니까?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int i){
                        Intent intent = new Intent(getApplication(), RegisterActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("아니오",null).show();

            } else {
                // 로그인 상태이면 회원정보 대화상자 띄우기
                View dialogView = View.inflate(this, R.layout.profile_dialog, null);
                TextView idTextView = dialogView.findViewById(R.id.textViewId);
                TextView nameTextView = dialogView.findViewById(R.id.textViewName);
                TextView phoneTextView = dialogView.findViewById(R.id.textViewPhone);
                TextView addressTextView = dialogView.findViewById(R.id.textViewAddress);
                //user객체에서 get으로 정보가져다씀
                idTextView.setText(user.getId());
                nameTextView.setText(user.getName());
                phoneTextView.setText(user.getPhone());
                addressTextView.setText(user.getAddress());

                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("회원정보").setView(dialogView);
                builder2.setPositiveButton("확인",null).show();

            }
        });
    }

}