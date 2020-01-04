package fr.utt.if26.myaccount;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Iterator;
import java.util.List;

public class LineViewModel extends AndroidViewModel {
    private LineRespository mRepository;
    private LiveData<List<LineEntity>> mAllLines;

    public LineViewModel(Application application) {
        super(application);
        mRepository = new LineRespository(application);
        mAllLines = mRepository.getAllLines();
    }
    LiveData<List<LineEntity>> getmAllLines() { return mAllLines; }
    LiveData<List<LineEntity>> getmLinesbyMonth(int year, int month) {
        return mRepository.getAllLinesByMonth(year,month);
    }

    public void insert(LineEntity lineEntity) { mRepository.insert(lineEntity); }

    public void delete(String id) {
        mRepository.delete(id);
    }

    public int getExpenseByMonth(int year, int month) {
        return mRepository.getExpenseByMonth(year,month);
    }

    public int getIncomeByMonth(int year, int month) {
        return mRepository.getIncomeByMonth(year,month);
    }
}
