package com.holy.simplemall;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //앱을 처음켰을때 대표적 화면 나오고 2.5초뒤에 사라지고 화면나오게하기
        if (savedInstanceState == null) {
            ImageView imageViewSplash = findViewById(R.id.imageViewSplash);
            imageViewSplash.setVisibility(View.VISIBLE);

            // 툴바 숨기기
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
            //화면 2.5초 나오게하고 사라지기
            imageViewSplash.postDelayed(() -> {     //delay뒤에 실행 할 코드
                imageViewSplash.setVisibility(View.INVISIBLE);
                // 툴바보이게
                if (actionBar != null) {
                    actionBar.show();
                }
            }, 2500);
        }

        // 유저 리스트 준비하기
        List<User> userList = ((App) getApplication()).getUserList();


        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button buttonToMain = findViewById(R.id.buttonToMainPage);

        // 로그인 버튼 클릭 시 실행
        buttonLogin.setOnClickListener(view ->
        {
            String id = editTextId.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // 아이디, 패스워드 빈 입력 체크
            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "아이디와 패스워드 모두입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            // 아이디가 존재하는지 체크
            User user = null;
            for (User u : userList) {
                //가져온유저정보의 아이디와, 입력한 id가 일치하면
                if (u.getId().equals(id)) {
                    user = u;
                    break;
                }
            }
            //못찾았으면 메시지
            if (user == null) {
                Toast.makeText(this, "없는 아이디입니다", Toast.LENGTH_SHORT).show();
                return;
            }

            // 비밀번호 일치 여부 체크
            if (!password.equals(user.getPassword())) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                return;
            }

            // 로그인 성공시 실행
            ((App) getApplication()).login(user);
            startHomeActivity();
        });
        //로그인 버튼 눌렀을때의 기능 종료


        // 회원가입 버튼 클릭 시  회원가입화면으로 전환 실행
        buttonRegister.setOnClickListener(view ->

        {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        // 메인 화면 버튼 클릭 시 메인화면으로 전환 실행
        buttonToMain.setOnClickListener(view ->
                startHomeActivity());

    }

    private void startHomeActivity() {
        // 메인 화면 실행
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}