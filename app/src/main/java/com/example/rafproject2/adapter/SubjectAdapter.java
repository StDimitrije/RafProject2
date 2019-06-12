package com.example.rafproject2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rafproject2.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_raspored_list_item, parent,false);

        return new SubjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SubjectHolder extends RecyclerView.ViewHolder {

        private TextView mSubjectTv;
        private TextView mSubjectTypeTv;
        private TextView mProfessorTv;
        private TextView mClassRoomTv;
        private TextView mGroupsTv;
        private TextView mTimeTv;

        public SubjectHolder(@NonNull View itemView) {
            super(itemView);
            mSubjectTv = itemView.findViewById(R.id.raspored_subject_tv);
            mSubjectTypeTv = itemView.findViewById(R.id.raspored_subject_type_tv);
            mProfessorTv = itemView.findViewById(R.id.raspored_professor_tv);
            mClassRoomTv = itemView.findViewById(R.id.raspored_classroom_tv);
            mGroupsTv = itemView.findViewById(R.id.raspored_groups_tv);
            mTimeTv = itemView.findViewById(R.id.raspored_time_tv);

        }
    }
}
