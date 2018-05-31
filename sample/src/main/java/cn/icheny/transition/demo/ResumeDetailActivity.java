package cn.icheny.transition.demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.icheny.transition.ElementTransition;

/**
 * 简历详情
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class ResumeDetailActivity extends AppCompatActivity {

    ImageView iv_head;
    TextView tv_name, tv_desc, tv_age, tv_work_years, tv_experience;
    LinearLayout ll_experienc;
    View v_resume_top_bg;
    private Resume mResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);

        initViews();
        initData();
        showResume();
    }

    private void showResume() {
        getSupportActionBar().setTitle(mResume.getName() + "的简历");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iv_head.setImageResource(mResume.getHeadId());
        tv_name.setText(mResume.getName());
        tv_age.setText(mResume.getAge() + "岁");
        tv_work_years.setText(mResume.getWorkYears() + "年工作经验");
        tv_desc.setText(mResume.getDesc());
        tv_experience.setText(mResume.getExperience());

        /**
         * 展开头背景
         */
        v_resume_top_bg.setScaleY(0);
        v_resume_top_bg.setPivotX(0);
        v_resume_top_bg.animate()
                .scaleY(1)
                .setDuration(1000)
                .start();

        /**
         * 履历视图进入界面
         */
        ll_experienc.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //取消监听
                ll_experienc.getViewTreeObserver().removeOnPreDrawListener(this);

                ll_experienc.setVisibility(View.VISIBLE);
                ll_experienc.setAlpha(0);
                ll_experienc.setTranslationY(ll_experienc.getHeight());
                ll_experienc.animate()
                        .setDuration(1000)
                        .alpha(1)
                        .translationY(0)
                        .start();

                return true;
            }
        });

        /**
         * 共享元素过渡动画
         */
        ElementTransition.runEnterAnim(this, 1000, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tv_age.setVisibility(View.VISIBLE);
                tv_work_years.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initData() {
        Intent data = getIntent();
        mResume = data.getParcelableExtra("resume");
    }

    private void initViews() {
        v_resume_top_bg = findViewById(R.id.v_resume_top_bg);
        iv_head = findViewById(R.id.iv_head);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_desc = findViewById(R.id.tv_desc);
        tv_work_years = findViewById(R.id.tv_work_years);
        tv_experience = findViewById(R.id.tv_experience);
        ll_experienc = findViewById(R.id.ll_experienc);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                back();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        ElementTransition.runExitAnim(this, 1000);

        /**
         * 收起头背景
         */
        v_resume_top_bg.animate()
                .scaleY(0)
                .setDuration(1000)
                .start();

        /**
         * 隐藏年龄和工作年限
         */
        tv_age.setVisibility(View.GONE);
        tv_work_years.setVisibility(View.GONE);
        ll_experienc.setVisibility(View.VISIBLE);


        /**
         * 履历视图离开界面
         */
        ll_experienc.animate()
                .setDuration(1200)
                .alpha(0)
                .translationY(ll_experienc.getHeight())
                .start();
    }
}
