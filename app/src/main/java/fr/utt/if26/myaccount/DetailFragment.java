package fr.utt.if26.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailFragment extends Fragment {
    RecyclerView recyclerView;
    private LineViewModel mLineViewModel;
    private Spinner monthSpin;
    private Spinner yearSpin;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        recyclerView=root.findViewById(R.id.main_recyclerview);
        final AccountAdapter adapter = new AccountAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);
        mLineViewModel.getmAllLines().observe(this, new Observer<List<LineEntity>>() {
            @Override
            public void onChanged(@Nullable final List<LineEntity> lineEntities) {
                adapter.setAccount(lineEntities);
            }
        });




        return root;
    }
}