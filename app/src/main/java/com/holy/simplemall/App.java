package com.holy.simplemall;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

//액티비티간 클래스 공유, application 객체멤버는 어디 프로세스에서도 참조 가능**
public class App extends Application {

    //공통 전역변수
    private final List<User> userList;  // 유저 리스트
    private User currentUser;           // 현재 로그인 한 유저

    public App() {
        super();

        userList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }

    //회원가입시 유저 등록
    public void register(User user) {

        for (User u : userList) {
            // 중복 아이디 존재하면 리턴
            if (u.getId().equals(user.getId())) {
                return;
            }
        }
        //중복된 아이디가 없으면 user를 추가
        userList.add(user);
    }

    //LoginActivity에서 로그인 버튼 최종 승인시
    public void login(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
