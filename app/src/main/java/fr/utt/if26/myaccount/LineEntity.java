package fr.utt.if26.myaccount;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account_table")
public class LineEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int day;
    private int month;
    private int year;
    private Double amount;
    private String category;
    private boolean expense;

    public LineEntity(String title, int day, int month, int year, Double amount, String category, boolean expense) {
        this.title = title;
        this.day = day;
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.category = category;
        this.expense = expense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isExpense() {
        return expense;
    }

    public void setExpense(boolean expense) {
        this.expense = expense;
    }
}
