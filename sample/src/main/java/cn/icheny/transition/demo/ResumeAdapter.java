package cn.icheny.transition.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.icheny.transition.ElementTransition;


/**
 * 简历项适配器
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {

    private List<Resume> mResumes;
    private LayoutInflater mInflater;
    private Context mContext;

    public ResumeAdapter(List<Resume> resumes, Context context) {
        mResumes = resumes;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public ResumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResumeViewHolder(mInflater.inflate(R.layout.item_resume_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ResumeViewHolder holder, final int position) {
        Resume resume = mResumes.get(position);
        holder.tv_name.setText(resume.getName());
        holder.tv_desc.setText(resume.getDesc());
        holder.iv_head.setImageResource(resume.getHeadId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resume resume = mResumes.get(position);
                Activity activity = (Activity) mContext;
                Intent intent = new Intent(activity, ResumeDetailActivity.class);
                intent.putExtra("resume", resume);
                ElementTransition.startActivity(intent, activity, holder.iv_head, holder.tv_name, holder.tv_desc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResumes.size();
    }


    static class ResumeViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_head;
        TextView tv_name, tv_desc;

        public ResumeViewHolder(View itemView) {
            super(itemView);
            iv_head = itemView.findViewById(R.id.iv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
