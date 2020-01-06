package fr.utt.if26.myaccount;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class AddFragment extends Fragment {

    // private AddViewModel mViewModel;
    private Boolean expense = true;
    private LineViewModel mLineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // mViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);
        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);
        final Button incomeButton = root.findViewById(R.id.ajoute_button_income);
        final Button expenseButton = root.findViewById(R.id.ajoute_button_expense);
        final DatePicker datePicker = (DatePicker) root.findViewById(R.id.ajoute_datepicker);
        final EditText titleEd = root.findViewById(R.id.ajoute_title_ed);
        final EditText amountEd = root.findViewById(R.id.ajoute_amount_ed);


        final Spinner categorySpinner = (Spinner) root.findViewById(R.id.ajoute_category_spinner);
        Button validerBt = root.findViewById(R.id.ajoute_button_valider);


        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                incomeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                expense = false;
                categorySpinner.setVisibility(View.INVISIBLE);
            }
        });
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incomeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                expenseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                expense = true;
                categorySpinner.setVisibility(View.VISIBLE);
            }
        });

        validerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String title = titleEd.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();
                if (title.length() != 0 && amountEd.getText().toString().length() != 0) {
                    Double amount = Double.parseDouble(amountEd.getText().toString());
                    LineEntity lineEntity = new LineEntity(title, day, month, year, amount, category, expense);
                    mLineViewModel.insert(lineEntity);
                    Log.v("mjy", expense + " " + day + " " + month + " " + year + " " + title + amount + category);
                    Toast.makeText(getActivity(), title + " of " + amount + "€ is added. ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Please fill in every field", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }
}