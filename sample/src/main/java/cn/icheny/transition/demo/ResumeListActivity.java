package cn.icheny.transition.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 简历列表
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class ResumeListActivity extends AppCompatActivity {
    private RecyclerView rv_resumes;
    private List<Resume> mResumes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_list);

        initViews();
        initData();
        showResumes();
    }

    private void showResumes() {
        ResumeAdapter adapter = new ResumeAdapter(mResumes, this);
        rv_resumes.setLayoutManager(new LinearLayoutManager(this));
        rv_resumes.setAdapter(adapter);
    }

    private void initData() {
        mResumes = new ArrayList<>();
        Resume resume = null;

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            resume = new Resume();
            int switchId = random.nextInt(6) + 1;
            resume.setName("李阿" + i);
            resume.setUserId(i);
            resume.setWorkYears(switchId);
            resume.setAge(switchId + 20);
            resume.setHeadId(getHeadId(switchId));
            resume.setDesc("一个" + switchId + "年工作经验的Android开发工程师");
            resume.setExperience(getString(R.string.experience));
            mResumes.add(resume);
        }
    }

    private int getHeadId(int switchId) {
        int headId;
        switch (switchId) {
            case 1:
                headId = R.drawable.hd_1;
                break;
            case 2:
                headId = R.drawable.hd_2;
                break;
            case 3:
                headId = R.drawable.hd_3;
                break;
            case 4:
                headId = R.drawable.hd_4;
                break;
            case 5:
                headId = R.drawable.hd_5;
                break;
            default:
                headId = R.drawable.hd_6;
                break;
        }
        return headId;
    }

    private void initViews() {
        rv_resumes = findViewById(R.id.rv_resumes);
    }
}
