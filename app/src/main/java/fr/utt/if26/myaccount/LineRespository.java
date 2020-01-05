package fr.utt.if26.myaccount;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public LiveData<List<LineEntity>> getAllLinesByMonth(int year, int month) {
        return mLineDao.getAllLinesByMonth(year, month);
    }

    void insert(LineEntity lineEntity) {
        new insertAsyncTask(mLineDao).execute(lineEntity);
    }

    public void delete(String id) {
        new deletAsyncTask(mLineDao).execute(id);
    }

    public String getExpenseByMonth(int year, int month) {
        //Log.v("mjy","getExpenseByMonth() "+year+" "+month);
        AsyncTask task = new getExpenseByMonthAsyncTask(mLineDao).execute(year, month);
        String result = null;
        try {
            result = task.get().toString();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getIncomeByMonth(int year, int month) {
        AsyncTask task = new getIncomeByMonthAsyncTask(mLineDao).execute(year, month);
        String result = null;
        try {
            result = task.get().toString();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
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

    private static class deletAsyncTask extends AsyncTask<String, Void, Void> {

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

    private static class getExpenseByMonthAsyncTask extends AsyncTask<Integer, Void, Integer> {

        private LineDao mAsyncTaskDao;

        getExpenseByMonthAsyncTask(LineDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Integer doInBackground(final Integer... params) {
            return mAsyncTaskDao.getExpenseByMonth(params[0], params[1]);
        }
    }

    private static class getIncomeByMonthAsyncTask extends AsyncTask<Integer, Void, Integer> {

        private LineDao mAsyncTaskDao;

        getIncomeByMonthAsyncTask(LineDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Integer doInBackground(final Integer... params) {
            return mAsyncTaskDao.getIncomeByMonth(params[0], params[1]);
        }
    }

}
