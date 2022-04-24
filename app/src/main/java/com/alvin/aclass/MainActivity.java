package com.alvin.aclass;

import static com.alvin.aclass.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.alvin.aclass.fragment.RegisiterFragment;
import com.alvin.aclass.pojo.Student;

public class MainActivity extends AppCompatActivity {
    private String mUserName = "alvin",mPassword = "123456";

    private MyStudentDBHelper myStudentDBHelper;

    private CheckBox cbRemeber,cbAutoLogin;

    private EditText etUsername,etPassword;

    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        //设置背景图片透明度
        View vBackground = findViewById(R.id.total_background);
        vBackground.getBackground().setAlpha(100);
        initView();
        myStudentDBHelper = new MyStudentDBHelper(this);
    }

    private void initView() {
        cbRemeber = findViewById(id.cb_remeber);//记住密码控件
        cbAutoLogin = findViewById(id.cb_autoLogin);//自动登录

        etUsername = findViewById(id.et_username);//用户输入账号
        etPassword = findViewById(id.et_password);//用户输入密码
    }

    /**
     * 用户登录，界面转到BottomNaviActivity
     * @param view
     */
    public void Login(View view) {
        String userNumber = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if(judgeIsExist(userNumber)){
            if(password.equals(student.getPassword()))//密码一致
            {
                Intent intent = new Intent(MainActivity.this,BottomNaviActivity.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(this, "输入密码错误", Toast.LENGTH_SHORT).show();
            }

        }else
        {
            Toast.makeText(this, "当前用户不存在，请先注册账户", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 新用户注册
     * @param view
     */
    public void toUserRegister(View view) {
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 判断账号是否已经注册过
     * @param userNumber
     * @return
     */
    public boolean judgeIsExist(String userNumber){
        student = myStudentDBHelper.selectByUserNumber(userNumber);
        if(student!=null){
            return true;
        }
        return false;
    }
}