package com.example.rafproject2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rafproject2.R;
import com.example.rafproject2.adapter.SubjectAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RasporedFragment extends Fragment {

    private Spinner mGroupSpinner;
    private Spinner mDaySpinner;
    private EditText mSearchEt;
    private Button mSearchBtn;
    private RecyclerView mRecyclerView;
    private SubjectAdapter mSubjectAdapter;



    public static RasporedFragment newInstance() {
        return new RasporedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_raspored,container,false);

        mGroupSpinner = view.findViewById(R.id.spinner_group);
        mDaySpinner = view.findViewById(R.id.spinner_day);
        mSearchEt = view.findViewById(R.id.fragment_raspored_search_et);
        mSearchBtn = view.findViewById(R.id.fragment_raspored_search_btn);


        mSubjectAdapter = new SubjectAdapter();
        mRecyclerView = view.findViewById(R.id.fragment_raspored_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mSubjectAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
