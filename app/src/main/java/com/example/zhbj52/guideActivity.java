package com.example.zhbj52;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class guideActivity extends AppCompatActivity {
    private  static  final  int[] mImgIds=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private ViewPager vpGuide;
    ArrayList<ImageView> mImageViewList=new ArrayList<ImageView>();
    private LinearLayout llPointGroup;//小圆点父级控件
    private  int mPointWidth;
    private View viewRedView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        vpGuide=(ViewPager) findViewById(R.id.vp_guide);
        viewRedView=findViewById(R.id.view_red_point);
        llPointGroup=(LinearLayout) findViewById(R.id.ll_point);
        initView();
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.setOnPageChangeListener(new GuidePageLister());

    }
    private  void initView(){
        //初始化界面
     for(int i=0;i<mImgIds.length;i++){
         ImageView image=new ImageView(this);
         image.setBackgroundResource(mImgIds[i]);
         mImageViewList.add(image);
     }

     //初始化小圆点
        for(int i=0;i<mImgIds.length;i++){
            View point=new View(this);
            point.setBackgroundResource(R.drawable.shape_point);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30,30);

            if(i>0){
                params.leftMargin=30;
            }
            point.setLayoutParams(params);
            llPointGroup.addView(point);//将圆点添加给线性布局
        }
        //

        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mPointWidth=llPointGroup.getChildAt(1).getLeft() -llPointGroup.getChildAt(0).getLeft();
                System.out.println("width=="+mPointWidth);
                llPointGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
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

    //监听滚动
    class GuidePageLister implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            System.out.println("当前位置:"+position+"百分比:"+positionOffset+"移动距离:"+positionOffsetPixels);
           int len= (int)(mPointWidth*positionOffset)+(position*mPointWidth);
            RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams) viewRedView.getLayoutParams();
            params.leftMargin=len;
            viewRedView.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
