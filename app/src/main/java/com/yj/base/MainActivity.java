package com.yj.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yj.base.home.HomeFragment;
import com.yj.base.information.InformationFragment;
import com.yj.base.my.MineFragment;
import com.yj.baselibrary.base.BaseActivity;
import com.yj.baselibrary.utils.ToastUitl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_home;   //首页
    LinearLayout ly_home;
    HomeFragment homeFragment;
    TextView tv_home;
    ImageView iv_information;   //资讯
    LinearLayout ly_information;
    InformationFragment informationFragment;
    TextView tv_information;
    ImageView iv_mine;    //我的
    LinearLayout ly_mine;
    TextView tv_mine;
    MineFragment mineFragment;
    // 当前选中的
    private int currentTabIndex;
    private Fragment[] fragments;
    private boolean isSysExit = false;
    public int index;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        iv_home=findViewById(R.id.iv_home);
        ly_home=findViewById(R.id.ly_home);
        tv_home=findViewById(R.id.tv_home);

        iv_information=findViewById(R.id.iv_information);
        ly_information=findViewById(R.id.ly_information);
        tv_information=findViewById(R.id.tv_information);

        iv_mine=findViewById(R.id.iv_mine);
        ly_mine=findViewById(R.id.ly_mine);
        tv_mine=findViewById(R.id.tv_mine);

        ly_home.setOnClickListener(this);
        ly_information.setOnClickListener(this);
        ly_mine.setOnClickListener(this);
        showView();
    }

    private void showView() {
        // 防止fragment内存重启出现view的重影
        if (mSavedInstanceState != null){
            List<Fragment> fragmentsList = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragmentsList) {
                if (fragment instanceof HomeFragment) {
                    homeFragment = (HomeFragment) fragment;
                }
                if (fragment instanceof InformationFragment) {
                    informationFragment = (InformationFragment) fragment;
                }
                if (fragment instanceof MineFragment) {
                    mineFragment = (MineFragment) fragment;
                }
            }
            initFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            index = mSavedInstanceState.getInt("currentTabIndex");
            currentTabIndex = index;
            setView(currentTabIndex);

            for (int i = 0; i < fragments.length; i++) {
                if (fragments[i] != null && fragments[i].isAdded() && i == index) {
                    ft.show(fragments[i]);
                } else if (fragments[i] != null && fragments[i].isAdded() && i != index) {
                    ft.hide(fragments[i]);
                }
            }
            ft.commit();
        } else {
            initFragment();
            index = isToSessionActivity() ? 1 : 0;
            currentTabIndex = index;
            setView(currentTabIndex);
            // 设置选中̬
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();

            for (int i = 0; i < fragments.length; i++) {
                Fragment fragment = fragments[i];
                if (!fragment.isAdded()) {
                    trx.add(R.id.ly_main, fragment);
                    if (i != currentTabIndex) {
                        trx.hide(fragment);
                    }
                }
            }
            trx.commit();
        }
    }
    public void setView(int fragmentid) {  // 设置显示fragment
        switch (fragmentid) {
            case 0:
                showDefault();
                tv_home.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
                iv_home.setSelected(true);
                ly_home.setEnabled(false);
                showFragmens();
                break;
            case 1:
                showDefault();
                tv_information.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimary));
                iv_information.setSelected(true);
                ly_information.setEnabled(false);
                showFragmens();
                break;
            case 2:
                  showDefault();
                  tv_mine.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
                  iv_mine.setSelected(true);
                  ly_mine.setEnabled(false);
                  showFragmens();
                break;
        }
    }

    private void showDefault() {
        iv_home.setSelected(false);
        ly_home.setEnabled(true);
        tv_home.setTextColor(ContextCompat.getColor(this,R.color.home_class_tv));
        iv_information.setSelected(false);
        ly_information.setEnabled(true);
        tv_information.setTextColor(ContextCompat.getColor(this,R.color.home_class_tv));
        iv_mine.setSelected(false);
        ly_mine.setEnabled(true);
        tv_mine.setTextColor(ContextCompat.getColor(this,R.color.home_class_tv));
    }
    private void showFragmens(){
        if (currentTabIndex != index){
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);

            if (!fragments[index].isAdded()) {
                trx.add(R.id.ly_main, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        currentTabIndex = index;
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        if (informationFragment == null) {
            informationFragment = new InformationFragment();
        }
        if (mineFragment == null){
            mineFragment = new MineFragment();
        }
        fragments = null;
        fragments = new Fragment[]{homeFragment, informationFragment,mineFragment};
    }

    private boolean isToSessionActivity() {
        return getIntent().hasExtra("conversationType") && getIntent().hasExtra("conversationId");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentTabIndex", currentTabIndex);
        super.onSaveInstanceState(outState);
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        if (!isSysExit) {
            isSysExit = true;
            ToastUitl.show("再点击一次就退出了!");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    isSysExit = false;
                }
            }, 1000);
        } else {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_home:
                index=0;
                break;
            case R.id.ly_information:
                index=1;
                break;
            case R.id.ly_mine:
                index=2;
                break;
        }
        setView(index);
    }
}
