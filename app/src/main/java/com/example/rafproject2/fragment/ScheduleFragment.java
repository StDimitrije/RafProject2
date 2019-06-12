package com.example.rafproject2.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rafproject2.R;
import com.example.rafproject2.adapter.SubjectAdapter;
import com.example.rafproject2.model.ScheduleFilter;
import com.example.rafproject2.repository.db.entity.ScheduleEntity;
import com.example.rafproject2.repository.web.model.Resource;
import com.example.rafproject2.viewmodel.MainViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleFragment extends Fragment {

    private Spinner mGroupSpinner;
    private Spinner mDaySpinner;
    private EditText mSearchEt;
    private Button mSearchBtn;
    private RecyclerView mRecyclerView;
    private SubjectAdapter mSubjectAdapter;
    private MainViewModel mainViewModel;




    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_raspored,container,false);

        mGroupSpinner = view.findViewById(R.id.spinner_group);
        mDaySpinner = view.findViewById(R.id.spinner_day);
        mSearchEt = view.findViewById(R.id.fragment_raspored_search_et);
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String subject = mSearchEt.getText().toString();
                String teacher = mSearchEt.getText().toString();
                String group = mGroupSpinner.getSelectedItem().toString();
                String day = mDaySpinner.getSelectedItem().toString();
                ScheduleFilter filter = new ScheduleFilter(subject,day,group);
                mainViewModel.setFilter(filter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mSearchBtn = view.findViewById(R.id.fragment_raspored_search_btn);

        mRecyclerView = view.findViewById(R.id.fragment_raspored_recycler_view);
        mSubjectAdapter = new SubjectAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mSubjectAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getSchedule().observe(this, new Observer<Resource<Void>>() {
            @Override
            public void onChanged(Resource<Void> voidResource) {
                String message = voidResource.isSuccessful() ? getString(R.string.fetch_success_message) : getString(R.string.fetch_fail_message);
                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
            }

        });
        mainViewModel.getCompleteSchedule().observe(this, new Observer<List<ScheduleEntity>>() {
            @Override
            public void onChanged(List<ScheduleEntity> scheduleEntityList) {

                mSubjectAdapter.setData(scheduleEntityList);
            }
        });
        mainViewModel.getFilteredSchedule().observe(this, new Observer<List<ScheduleEntity>>() {
            @Override
            public void onChanged(List<ScheduleEntity> scheduleEntityList) {
                mSubjectAdapter.setData(scheduleEntityList);
            }
        });

    }
}
