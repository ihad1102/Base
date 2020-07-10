package com.yj.base.home;
import android.graphics.Path;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yj.base.ApiControct;
import com.yj.base.ImageHttpUtils;
import com.yj.base.R;
import com.yj.base.home.activity.BlockCultureActivity;
import com.yj.base.home.activity.CommunityBuildingActivity;
import com.yj.base.home.activity.OperateActivity;
import com.yj.base.home.activity.SmartAssetsActivity;
import com.yj.base.home.activity.SmartPropertyActivity;
import com.yj.baselibrary.base.BaseFragment;
import com.yj.baselibrary.picture.image.ImageManager;
import com.yj.baselibrary.picture.image.options.ImageOptions;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener,BGABanner.Delegate<ImageView, String>,
        BGABanner.Adapter<ImageView, String> {

    private TextView tb_tv_title,tv_zhzc,tv_zhwy,tv_jqwh,tv_zsyy,tv_sqyz;

    private BGABanner banner_main;
    /**
     * 构造空的fragmnet
     */

    public HomeFragment(){

    }
    public static HomeFragment homeFragment;

    public static HomeFragment getInstance(){
        homeFragment = new HomeFragment();
        return homeFragment;
    }
    @Override
    protected int getLayoutResource(){
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter(){

    }
    @Override
    protected void initView(){
        tb_tv_title=rootView.findViewById(R.id.tb_tv_title);
        tv_zhzc=rootView.findViewById(R.id.tv_zhzc);
        tv_zhwy=rootView.findViewById(R.id.tv_zhwy);
        tv_jqwh=rootView.findViewById(R.id.tv_jqwh);
        tv_zsyy=rootView.findViewById(R.id.tv_zsyy);
        tv_sqyz=rootView.findViewById(R.id.tv_sqyz);
        banner_main=rootView.findViewById(R.id.banner_main);
        initData();
        initEvent();
    }

    private void initEvent() {
        tv_zhzc.setOnClickListener(this);
        tv_zhwy.setOnClickListener(this);
        tv_jqwh.setOnClickListener(this);
        tv_zsyy.setOnClickListener(this);
        tv_sqyz.setOnClickListener(this);
    }

    private List<String> imageList=new ArrayList<>(); //图片地址
    private void initData(){
        imageList.clear();
        tb_tv_title.setText("首页");
        for(int i = 0; i< ApiControct.img.length; i++){
                imageList.add(ApiControct.img[i]);
         }
            banner_main.setAdapter(this);
            banner_main.setData(imageList, null);
            banner_main.setDelegate(this);
    }
    @Override
    public void onClick(View v) {
           switch (v.getId()){
               //智慧资产
               case R.id.tv_zhzc:
                   startActivity(SmartAssetsActivity.class);
                   break;
               //智慧物业
               case R.id.tv_zhwy:
                   startActivity(SmartPropertyActivity.class);
                   break;
               //街区文化
               case R.id.tv_jqwh:
                   startActivity(BlockCultureActivity.class);
                   break;
               //招商运营
               case R.id.tv_zsyy:
                   startActivity(OperateActivity.class);
                   break;
               //社区营造
               case R.id.tv_sqyz:
                   startActivity(CommunityBuildingActivity.class);
                   break;
           }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        ImageHttpUtils.getInstance().LoadHttpImage(itemView,model);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, @Nullable String model, int position) {

    }
}
