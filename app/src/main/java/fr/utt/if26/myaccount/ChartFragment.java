package fr.utt.if26.myaccount;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;

public class ChartFragment extends Fragment {

    private LineViewModel mLineViewModel;
    private Spinner monthSpin;
    private Spinner yearSpin;
    private PieChart pieChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chart, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        chartViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);
        pieChart = root.findViewById(R.id.chart_pie);
        monthSpin = root.findViewById(R.id.chart_month_spinner);
        yearSpin = root.findViewById(R.id.chart_year_spinner);
        monthSpin.setSelection(getCurrentMonthPos());
        yearSpin.setSelection(getCurrentYearPos());

        yearSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedYear = Integer.valueOf(yearSpin.getSelectedItem().toString());
                int selectedMonth = Integer.valueOf(monthSpin.getSelectedItem().toString());
                setmChart(selectedYear, selectedMonth);
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
                setmChart(selectedYear, selectedMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        return root;
    }

    private void setmChart(int year, int month) {
        PieDataSet pieDataSet = new PieDataSet(dataValues(year, month), "");
        int[] colorClassArray = new int[]{getResources().getColor(R.color.colorTransport), getResources().getColor(R.color.colorShopping), getResources().getColor(R.color.colorFood), getResources().getColor(R.color.colorHousing)};
        pieDataSet.setColors(colorClassArray);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(15);
        pieChart.setData(pieData);
        pieChart.setDrawEntryLabels(true);
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleRadius(0);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(15);
        pieChart.invalidate();
    }

    private ArrayList<PieEntry> dataValues(int year, int month) {
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        if(mLineViewModel.getTransportByMonth(year, month)!="0"){
            dataVals.add(new PieEntry(Integer.parseInt(mLineViewModel.getTransportByMonth(year, month)), "Transport"));
        }
        if(mLineViewModel.getShoppingByMonth(year, month)!="0"){
            dataVals.add(new PieEntry(Integer.parseInt(mLineViewModel.getShoppingByMonth(year, month)), "Shopping"));
        }
        if(mLineViewModel.getFoodByMonth(year, month)!="0"){
            dataVals.add(new PieEntry(Integer.parseInt(mLineViewModel.getFoodByMonth(year, month)), "Food"));
        }
        if(mLineViewModel.getHousingByMonth(year, month)!="0"){
            dataVals.add(new PieEntry(Integer.parseInt(mLineViewModel.getHousingByMonth(year, month)), "Housing"));
        }

        return dataVals;
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