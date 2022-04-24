package com.alvin.aclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alvin.aclass.pojo.Student;

public class RegisterActivity extends Activity {

    private EditText etUserNumber,etPassword,etName,againPassword;

    private MyStudentDBHelper myStudentDBHelper;

    private RadioButton rbAgree;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisiter);
        //设置背景图片透明度
        View vBackground = findViewById(R.id.rb);
        vBackground.getBackground().setAlpha(100);
        initView();
        myStudentDBHelper = new MyStudentDBHelper(this);

    }

    private void initView() {
        etUserNumber = findViewById(R.id.et_usernumber);
        etPassword = findViewById(R.id.et_password);
        etName = findViewById(R.id.et_username);
        againPassword = findViewById(R.id.again_password);
        rbAgree = findViewById(R.id.rb_agree);
    }

    public void userRegister(View view) {
        if(rbAgree.isChecked()) {

            String secondPassword = againPassword.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (password.equals(secondPassword)) {
                String usernumber = etUserNumber.getText().toString().trim();
                String name = etName.getText().toString().trim();

                Student student = new Student();
                student.setUsernumber(usernumber);
                student.setPassword(password);
                student.setName(name);
                long rowId = myStudentDBHelper.insertStudent(student);
                if (rowId != -1) {
                    Toast.makeText(this, "恭喜新用户添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "新用户添加失败！", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "两次输入密码不一致，请重新输入密码", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(this, "请先点击同意协议按钮", Toast.LENGTH_SHORT).show();
        }

    }
}
