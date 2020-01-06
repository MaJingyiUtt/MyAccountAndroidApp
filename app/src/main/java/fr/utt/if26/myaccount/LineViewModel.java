package fr.utt.if26.myaccount;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LineViewModel extends AndroidViewModel {
    private LineRespository mRepository;
    private LiveData<List<LineEntity>> mAllLines;

    public LineViewModel(Application application) {
        super(application);
        mRepository = new LineRespository(application);
        mAllLines = mRepository.getAllLines();
    }

    LiveData<List<LineEntity>> getmAllLines() {
        return mAllLines;
    }

    LiveData<List<LineEntity>> getmLinesbyMonth(int year, int month) {
        return mRepository.getAllLinesByMonth(year, month);
    }

    public void insert(LineEntity lineEntity) {
        mRepository.insert(lineEntity);
    }

    public void delete(String id) {
        mRepository.delete(id);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public String getExpenseByMonth(int year, int month) {
        return mRepository.getExpenseByMonth(year, month);
    }

    public String getIncomeByMonth(int year, int month) {
        return mRepository.getIncomeByMonth(year, month);
    }

    public String getTransportByMonth(int year, int month) {
        return mRepository.getTransportByMonth(year, month);
    }

    public String getShoppingByMonth(int year, int month) {
        return mRepository.getShoppingByMonth(year, month);
    }

    public String getFoodByMonth(int year, int month) {
        return mRepository.getFoodByMonth(year, month);
    }

    public String getHousingByMonth(int year, int month) {
        return mRepository.getHousingByMonth(year, month);
    }
}
