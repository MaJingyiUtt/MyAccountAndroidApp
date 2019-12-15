package fr.utt.if26.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        monthSpin=root.findViewById(R.id.detail_month_spinner);
        yearSpin=root.findViewById(R.id.detail_year_spinner);
        monthSpin.setSelection(getCurrentMonthPos());
        yearSpin.setSelection(getCurrentYearPos());
        final AccountAdapter adapter = new AccountAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);

        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);
        mLineViewModel.getmAllLines().observe(this, new Observer<List<LineEntity>>() {
            @Override
            public void onChanged(@Nullable final List<LineEntity> lineEntities) {
                adapter.setAccount(lineEntities);
            }
        });




        return root;
    }

    private int getCurrentMonthPos() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return month;
    }
    private int getCurrentYearPos() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year-2017;
    }
}