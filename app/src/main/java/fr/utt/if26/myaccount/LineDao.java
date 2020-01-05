package fr.utt.if26.myaccount;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LineEntity lineEntity);

    @Query("DELETE FROM account_table WHERE id= :id")
    void delete(String id);

    @Query("SELECT * FROM account_table ORDER BY year DESC, month DESC,day DESC ")
    LiveData<List<LineEntity>> getAllLines();

    @Query("DELETE FROM account_table")
    void deleteAll();

    @Query("SELECT * FROM account_table WHERE month=:month and year=:year ORDER BY year DESC, month DESC,day DESC ")
    LiveData<List<LineEntity>> getAllLinesByMonth(int year, int month);

    @Query("SELECT SUM(amount) FROM account_table WHERE month=:month and year=:year and expense=1")
    int getExpenseByMonth(int year, int month);

    @Query("SELECT SUM(amount) FROM account_table WHERE month=:month and year=:year and expense=0")
    int getIncomeByMonth(int year, int month);

    @Query("SELECT SUM(amount) FROM account_table WHERE month=:month and year=:year and category='Transport'")
    int getTransportByMonth(int year, int month);

    @Query("SELECT SUM(amount) FROM account_table WHERE month=:month and year=:year and category='Shopping'")
    int getShoppingByMonth(int year, int month);

    @Query("SELECT SUM(amount) FROM account_table WHERE month=:month and year=:year and category='Food'")
    int getFoodByMonth(int year, int month);

    @Query("SELECT SUM(amount) FROM account_table WHERE month=:month and year=:year and category='Housing'")
    int getHousingByMonth(int year, int month);

}
