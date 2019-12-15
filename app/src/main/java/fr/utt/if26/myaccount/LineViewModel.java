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
    LiveData<List<LineEntity>> getmAllLines() { return mAllLines; }

    public void insert(LineEntity lineEntity) { mRepository.insert(lineEntity); }

    public void delete(String id) {
        mRepository.delete(id);
    }

}
