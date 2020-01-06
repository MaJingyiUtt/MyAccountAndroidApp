package fr.utt.if26.myaccount;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class AllDataActivity extends AppCompatActivity {
    private LineViewModel mLineViewModel;
    private RecyclerView recyclerView;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);
        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);
        recyclerView = findViewById(R.id.main_recyclerview);
        final AccountAdapter adapter = new AccountAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        bt = findViewById(R.id.alldata_bt);
        mLineViewModel.getmAllLines().observe(this, new Observer<List<LineEntity>>() {
            @Override
            public void onChanged(@Nullable final List<LineEntity> lineEntities) {
                adapter.setAccount(lineEntities);
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllDataActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
