package com.alvin.aclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alvin.aclass.fragment.FindFragment;
import com.alvin.aclass.fragment.HomeFragment;
import com.alvin.aclass.fragment.MineFragment;

public class BottomNaviActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llHome,llFind,llMine;
    private ImageView ivHome,ivFind,ivMine;
    private TextView tvHome,tvFind,tvMine;

    private FragmentManager supportFragmentManager;

    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navi);

        //初始化id
        initView();
        initEvent();
    }

    private void initEvent() {
        HomeFragment homefragment = new HomeFragment();
        supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fcv,homefragment)
                .addToBackStack(null)
                .commit();
        ivHome.setSelected(true);
        tvHome.setTextColor(getResources().getColor(R.color.et_bg_purple));

        llHome.setOnClickListener(this);
        llFind.setOnClickListener(this);
        llMine.setOnClickListener(this);


    }

    private void initView() {
        llFind = findViewById(R.id.ll_find);
        llHome = findViewById(R.id.ll_home);
        llMine = findViewById(R.id.ll_mine);

        ivFind = findViewById(R.id.iv_find);
        ivHome = findViewById(R.id.iv_home);
        ivMine = findViewById(R.id.iv_mine);
        
        tvFind = findViewById(R.id.tv_find);
        tvHome = findViewById(R.id.tv_home);
        tvMine = findViewById(R.id.tv_mine);
    }

    @Override
    public void onClick(View view) {

        bottomReset();
        switch (view.getId()){
            case R.id.ll_find:
                FindFragment findFragment = new FindFragment();
                supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fcv,findFragment)
                        .addToBackStack(null)
                        .commit();
                ivFind.setSelected(true);
                tvFind.setTextColor(getResources().getColor(R.color.et_bg_purple));
                break;
            case R.id.ll_home:
                HomeFragment homefragment = new HomeFragment();
                supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fcv,homefragment)
                        .addToBackStack(null)
                        .commit();
                ivHome.setSelected(true);
                tvHome.setTextColor(getResources().getColor(R.color.et_bg_purple));
                break;
            case R.id.ll_mine:
                MineFragment mineFragment = new MineFragment();
                supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fcv, mineFragment)
                        .addToBackStack(null)
                        .commit();
                ivMine.setSelected(true);
                tvMine.setTextColor(getResources().getColor(R.color.et_bg_purple));
                break;
        }
    }

    private void bottomReset() {
        ivFind.setSelected(false);
        tvFind.setTextColor(getResources().getColor(R.color.huise));
        ivHome.setSelected(false);
        tvHome.setTextColor(getResources().getColor(R.color.huise));
        ivMine.setSelected(false);
        tvMine.setTextColor(getResources().getColor(R.color.huise));


    }
}