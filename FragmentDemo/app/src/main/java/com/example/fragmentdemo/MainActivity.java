package com.example.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add_fragment1;
    private Button add_fragment2;
    private Button popBackStack;
    private FrameLayout fragments;
    private FragmentManager supportFragmentManager;
    private Button add_fragment34;
    private Button remove_fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        supportFragmentManager = getSupportFragmentManager();


    }

    private void initView() {
        add_fragment1 = (Button) findViewById(R.id.add_fragment1);
        add_fragment2 = (Button) findViewById(R.id.add_fragment2);
        popBackStack = (Button) findViewById(R.id.popBackStack);
        fragments = (FrameLayout) findViewById(R.id.fragments);

        add_fragment1.setOnClickListener(this);
        add_fragment2.setOnClickListener(this);
        popBackStack.setOnClickListener(this);
        add_fragment34 = (Button) findViewById(R.id.add_fragment34);
        add_fragment34.setOnClickListener(this);
        remove_fragment1 = (Button) findViewById(R.id.remove_fragment1);
        remove_fragment1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_fragment1:
                Fragment1 fragment1 = new Fragment1();
                if (!fragment1.isAdded()){
                    addFragment(fragment1,"fragment1");
                }
                break;
            case R.id.add_fragment2:
                Fragment2 fragment2 = new Fragment2();
                if (!fragment2.isAdded()){
                    addFragment(fragment2,"fragment2");
                }

                break;
            case R.id.add_fragment34:
                Fragment3 fragment3 = new Fragment3();
                Fragment4 fragment4 = new Fragment4();
                //获得事务对象,并添加
                FragmentTransaction fragmentTransaction3 = supportFragmentManager.beginTransaction();

                if (!fragment3.isAdded()||!fragment4.isAdded()){
                    fragmentTransaction3.add(R.id.fragments, fragment3);
                    fragmentTransaction3.add(R.id.fragments, fragment4);
                }

                fragmentTransaction3.addToBackStack("fragment");
                //提交
                fragmentTransaction3.commit();


                break;
            case R.id.remove_fragment1:
                FragmentTransaction fragmentTransaction4 = supportFragmentManager.beginTransaction();
                Fragment fragment21 = supportFragmentManager.findFragmentByTag("fragment1");
                if (fragment21!=null){
                    fragmentTransaction4.remove(fragment21);
                    supportFragmentManager.popBackStack();
                    fragmentTransaction4.commit();
                }
                break;
            case R.id.popBackStack:

                Toast.makeText(this,"aaaaaaaaa",Toast.LENGTH_SHORT);
                setPopBackStack();
                break;
        }
    }




    private void addFragment(Fragment fragment,String tag){
        FragmentTransaction fragmentTransaction1 = supportFragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.fragments, fragment,tag);
        fragmentTransaction1.addToBackStack("fragment1");
        fragmentTransaction1.commit();

    }



    //事务回滚
    private void setPopBackStack() {
        //清除上一次执行事务之前的操作,也就是执行commit之前
        supportFragmentManager.popBackStack();

    }
}
