package com.holy.simplemall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

//회원가입 화면
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 위젯 가져오기
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        // 회원가입 버튼 시 실행
        buttonRegister.setOnClickListener(view -> {
            String id = editTextId.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String passwordConfirm = editTextPasswordConfirm.getText().toString().trim();
            String name = editTextName.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();
            String address = editTextAddress.getText().toString().trim();

            // 빈 에딧 텍스트 체크
            if (id.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()
                || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "모두 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            // 패스워드 일치 체크
            if (!password.equals(passwordConfirm)) {
                Toast.makeText(this, "비밀번호가 정확하지 않습니다", Toast.LENGTH_SHORT).show();
                return;
            }

            // 아이디, 패스워드 길이 체크
            if (id.length() < 3) {
                Toast.makeText(this, "아이디는 3글자 이상이어야 합니다", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 4) {
                Toast.makeText(this, "비밀번호는 4글자 이상이어야 합니다", Toast.LENGTH_SHORT).show();
                return;
            }

            // 아이디 중복 체크
            List<User> userList = ((App) getApplication()).getUserList();
            for (User u : userList) {
                if (u.getId().equals(id)) {
                    Toast.makeText(this, "이미 존재하는 아이디입니다", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // 약관 대화상자 보여주기
            View dialogView = View.inflate(this, R.layout.policy_dialog, null);
            RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroupAccept);

            AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
            builder3.setTitle("이용약관").setView(dialogView).setPositiveButton("확인",(dialogInterface,i) ->{
                if(radioGroup.getCheckedRadioButtonId() != R.id.radioButtonAccept){
                    Toast.makeText(this, "약관에 동의해야 가입할 수 있습니다.", Toast.LENGTH_SHORT).show();
                    return ;
                }
                User user = new User(id,password,name,phone,address);
                ((App) getApplication()).register(user);
                finish();
            }).show();


        });
    }


}