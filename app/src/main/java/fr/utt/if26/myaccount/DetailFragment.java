package fr.utt.if26.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class DetailFragment extends Fragment {
    RecyclerView recyclerView;
    private LineViewModel mLineViewModel;
    private Spinner monthSpin;
    private Spinner yearSpin;
    private TextView expense;
    private TextView income;
    private ImageButton ib;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        recyclerView = root.findViewById(R.id.main_recyclerview);
        monthSpin = root.findViewById(R.id.detail_month_spinner);
        yearSpin = root.findViewById(R.id.detail_year_spinner);
        monthSpin.setSelection(getCurrentMonthPos());
        yearSpin.setSelection(getCurrentYearPos());
        final AccountAdapter adapter = new AccountAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);
        expense = root.findViewById(R.id.detail_expense_tv);
        income = root.findViewById(R.id.detail_income_tv);

        ib = (ImageButton) root.findViewById(R.id.detail_bt);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(mainIntent);

            }
        });
        //expense.setText(String.valueOf(mLineViewModel.getExpenseByMonth(getCurrentMonthPos(),getCurrentYearPos())));
        // income.setText(mLineViewModel.getIncomeByMonth(getCurrentMonthPos(),getCurrentYearPos()));

//        mLineViewModel.getmAllLines().observe(this, new Observer<List<LineEntity>>() {
//            @Override
//            public void onChanged(@Nullable final List<LineEntity> lineEntities) {
//                adapter.setAccount(lineEntities);
//            }
//        });
        mLineViewModel.getmLinesbyMonth(getCurrentYearPos(), getCurrentMonthPos()).observe(this, new Observer<List<LineEntity>>() {
            @Override
            public void onChanged(@Nullable final List<LineEntity> lineEntities) {
                adapter.setAccount(lineEntities);
            }
        });
        yearSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedYear = Integer.valueOf(yearSpin.getSelectedItem().toString());
                int selectedMonth = Integer.valueOf(monthSpin.getSelectedItem().toString());
                expense.setText(mLineViewModel.getExpenseByMonth(selectedYear, selectedMonth));
                income.setText(mLineViewModel.getIncomeByMonth(selectedYear, selectedMonth));
                mLineViewModel.getmLinesbyMonth(selectedYear, selectedMonth).observe(getViewLifecycleOwner(), new Observer<List<LineEntity>>() {
                    @Override
                    public void onChanged(@Nullable final List<LineEntity> lineEntities) {
                        adapter.setAccount(lineEntities);
                    }
                });
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        monthSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedYear = Integer.valueOf(yearSpin.getSelectedItem().toString());
                int selectedMonth = Integer.valueOf(monthSpin.getSelectedItem().toString());
                expense.setText(mLineViewModel.getExpenseByMonth(selectedYear, selectedMonth));
                income.setText(mLineViewModel.getIncomeByMonth(selectedYear, selectedMonth));
                mLineViewModel.getmLinesbyMonth(selectedYear, selectedMonth).observe(getViewLifecycleOwner(), new Observer<List<LineEntity>>() {
                    @Override
                    public void onChanged(@Nullable final List<LineEntity> lineEntities) {
                        adapter.setAccount(lineEntities);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
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
        return year - 2017;
    }
}