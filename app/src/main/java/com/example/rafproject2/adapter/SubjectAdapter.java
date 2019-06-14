package com.example.rafproject2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.rafproject2.R;
import com.example.rafproject2.repository.db.entity.ScheduleEntity;
import com.example.rafproject2.util.ScheduleDiffCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    private List<ScheduleEntity> mDataSet;
    private OnItemClickedListener mOnItemClickedListener;

    public SubjectAdapter(){
        mDataSet = new ArrayList<>();
    }


    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_raspored_list_item, parent,false);

        return new SubjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {
        ScheduleEntity scheduleEntity = mDataSet.get(position);
        holder.mSubjectTv.setText(scheduleEntity.getSubject());
        holder.mSubjectTypeTv.setText("Tip: " + scheduleEntity.getType());
        holder.mDayTv.setText(scheduleEntity.getDay());
        holder.mTimeTv.setText(scheduleEntity.getTime());
        holder.mGroupsTv.setText("Grupa: " + scheduleEntity.getGroups());
        holder.mProfessorTv.setText("Profesor: " + scheduleEntity.getTeacher());
        holder.mClassRoomTv.setText("Ucionica: " + scheduleEntity.getClassRoom());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<ScheduleEntity> schedule){

        ScheduleDiffCallback callback = new ScheduleDiffCallback(mDataSet, schedule);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(schedule);
        result.dispatchUpdatesTo(this);
    }

    public class SubjectHolder extends RecyclerView.ViewHolder {

        private TextView mSubjectTv;
        private TextView mSubjectTypeTv;
        private TextView mProfessorTv;
        private TextView mClassRoomTv;
        private TextView mGroupsTv;
        private TextView mTimeTv;
        private TextView mDayTv;

        public SubjectHolder(@NonNull View itemView) {
            super(itemView);
            mSubjectTv = itemView.findViewById(R.id.raspored_subject_tv);
            mSubjectTypeTv = itemView.findViewById(R.id.raspored_subject_type_tv);
            mProfessorTv = itemView.findViewById(R.id.raspored_professor_tv);
            mClassRoomTv = itemView.findViewById(R.id.raspored_classroom_tv);
            mGroupsTv = itemView.findViewById(R.id.raspored_groups_tv);
            mTimeTv = itemView.findViewById(R.id.raspored_time_tv);
            mDayTv = itemView.findViewById(R.id.raspored_day_tv);

        }
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClicked(ScheduleEntity scheduleEntity);
    }
}
