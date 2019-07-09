package com.example.zhbj52;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;

public class guideActivity extends AppCompatActivity {
    private  static  final  int[] mImgIds=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private ViewPager vpGuide;
    ArrayList<ImageView> mImageViewList=new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        vpGuide=(ViewPager) findViewById(R.id.vp_guide);
        initView();
        vpGuide.setAdapter(new GuideAdapter());

    }
    private  void initView(){
        //初始化界面
     for(int i=0;i<mImgIds.length;i++){
         ImageView image=new ImageView(this);
         image.setBackgroundResource(mImgIds[i]);
         mImageViewList.add(image);
     }
    }
    class GuideAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mImgIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
