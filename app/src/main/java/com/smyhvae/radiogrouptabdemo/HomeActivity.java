package com.smyhvae.radiogrouptabdemo;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.smyhvae.radiogrouptabdemo.fragment.FindFragment;
import com.smyhvae.radiogrouptabdemo.fragment.HomeFragment;
import com.smyhvae.radiogrouptabdemo.fragment.ProfileFragment;
import com.smyhvae.radiogrouptabdemo.fragment.SearchFragment;


/**
 * Created by smyhvae on 2015/4/28.
 *
 */

public class HomeActivity extends FragmentActivity {
    private FrameLayout mHomeContent;
    private RadioGroup mHomeRadioGroup;
    private RadioButton mHomeHomeRb;
    private RadioButton mHomeFindRb;
    private RadioButton mHomeSearchRb;
    private RadioButton mHomeProfileRb;
    static final int NUM_ITEMS = 4;//一共四个fragment

    //fragment
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private FindFragment findFragment;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private SearchFragment searchFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
    }

    /**
     * tab上方和下方tabs的功能区域
     */
    protected void initView() {
        mHomeContent = (FrameLayout) findViewById(R.id.mHomeContent); //tab上方的区域
        mHomeRadioGroup = (RadioGroup) findViewById(R.id.mHomeRadioGroup);  //底部的四个tab
        mHomeHomeRb = (RadioButton) findViewById(R.id.mHomeHomeRb);
        mHomeFindRb = (RadioButton) findViewById(R.id.mHomeFindRb);
        mHomeSearchRb = (RadioButton) findViewById(R.id.mHomeSearchRb);
        mHomeProfileRb = (RadioButton) findViewById(R.id.mHomeProfileRb);

        //监听事件：为底部的RadioGroup绑定状态改变的监听事件
        mHomeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.mHomeHomeRb:
                        index = 0;
                        break;
                    case R.id.mHomeFindRb:
                        index = 1;
                        break;
                    case R.id.mHomeSearchRb:
                        index = 2;
                        break;
                    case R.id.mHomeProfileRb:
                        index = 3;
                        break;
                }
                //通过fragments这个adapter还有index来替换帧布局中的内容
                Fragment fragment = (Fragment) fragments.instantiateItem(mHomeContent, index);
                //一开始将帧布局中 的内容设置为第一个
                fragments.setPrimaryItem(mHomeContent, 3, fragment);
                fragments.finishUpdate(mHomeContent);
            }
        });
    }


    //第一次启动时，我们让mHomeHomeRb这个radiobutton处于选中状态。
    // 当然了，在这之前，先要在布局文件中设置其他的某一个radiobutton（只要不是mHomeHomeRb就行）
    // 的属性为android:checked="true"，才会出发下面的这个check方法切换到mHomeHomeRb
    @Override
    protected void onStart() {
        super.onStart();
        mHomeRadioGroup.check(R.id.mHomeHomeRb);
    }

    //用adapter来管理四个Fragment界面的变化。注意，我这里用的Fragment都是v4包里面的
    FragmentStatePagerAdapter fragments = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public int getCount() {
            return NUM_ITEMS;//一共有四个Fragment
        }
        //进行Fragment的初始化
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0://首页
                    fragment = new HomeFragment();
                    break;
                case 1://发现
                    fragment = new FindFragment();
                    break;

                case 2://搜索
                    fragment = new SearchFragment();
                    break;

                case 3://我的
                    fragment = new ProfileFragment();
                    break;
                default:
                    new HomeFragment();
                    break;
            }

            return fragment;
        }
    };


    private void initFragment(){
        findFragment = new FindFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        searchFragment = new SearchFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mHomeContent,homeFragment,homeFragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mHomeContent,fragment,fragment.getClass().getName());;
        fragmentTransaction.commit();
    }




}