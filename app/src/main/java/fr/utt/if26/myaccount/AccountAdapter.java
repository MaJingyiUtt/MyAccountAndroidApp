package fr.utt.if26.myaccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView date;
        public TextView category;
        public TextView amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.row_title);
            date= itemView.findViewById(R.id.row_date);
            category= itemView.findViewById(R.id.row_category);
            amount= itemView.findViewById(R.id.row_amount);

        }
    }
    private final LayoutInflater mInflater;
    private List<LineEntity> account; // Cached copy of words

    AccountAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    public void setAccount(List<LineEntity> l){
        account = l;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.account_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, int position) {
       if(account !=null){
           holder.title.setText(account.get(position).getTitle());
           holder.date.setText(account.get(position).getDay()+"/"+account.get(position).getMonth()+"/"+account.get(position).getYear());
           holder.category.setText(account.get(position).getCategory());
           if(account.get(position).isExpense()){
               holder.amount.setText("- "+account.get(position).getAmount());
           }else{
               holder.amount.setText("+ "+account.get(position).getAmount());
           }
       }else{
           holder.title.setText("none");
       }

    }

    @Override
    public int getItemCount() {
        if(account !=null){
            return account.size();
        }else{
            return 0;
        }

    }


}
