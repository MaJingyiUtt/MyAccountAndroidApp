package fr.utt.if26.myaccount;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LineRespository {
    private LineDao mLineDao;
    private LiveData<List<LineEntity>> mAllLines;

    LineRespository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mLineDao = db.lineDao();
        mAllLines = mLineDao.getAllLines();
    }

    LiveData<List<LineEntity>> getAllLines() {
        return mAllLines;
    }

    void insert(LineEntity lineEntity) {
        new insertAsyncTask(mLineDao).execute(lineEntity);
    }
    public void delete (String id){
        new deletAsyncTask(mLineDao).execute(id);
    }
    private static class insertAsyncTask extends AsyncTask<LineEntity, Void, Void> {
        private LineDao mAsyncTaskDao;

        insertAsyncTask(LineDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final LineEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private  static class deletAsyncTask extends AsyncTask<String, Void, Void> {

        private LineDao mAsyncTaskDao;

        deletAsyncTask(LineDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
