package fr.utt.if26.myaccount;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    private LineViewModel mLineViewModel;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView date;
        public ImageView category;
        public TextView amount;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.row_title);
            date= itemView.findViewById(R.id.row_date);
            category= itemView.findViewById(R.id.row_category);
            amount= itemView.findViewById(R.id.row_amount);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final int p=getLayoutPosition();
                    Log.v("mjy","LongClick: "+account.get(p).getTitle());
                    //Log.v("mjy","fragment "+((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(R.id.navigation_detail));


                   //mLineViewModel.delete(Integer.toString(account.get(p).getId()));
                    AlertDialog.Builder dialog=new AlertDialog.Builder(itemView.getContext());
                    dialog.setMessage("Do you really want to delete "+ account.get(p).getTitle()+" ?");
                    dialog.setTitle("Attention");
                    dialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    //////TODO : delete in database
                                  //  mLineViewModel = ViewModelProviders.of((FragmentActivity)context).get(LineViewModel.class);
                                   // mLineViewModel.delete(Integer.toString(account.get(p).getId()));
                                }
                            });
                    dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog alertDialog=dialog.create();
                    alertDialog.show();
                    return true;
                }
            });
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
          // holder.category.setText(account.get(position).getCategory());
           switch (account.get(position).getCategory()) {
               case "Housing":
                   holder.category.setImageResource(R.drawable.housing);
                   break;
               case "Food":
                   holder.category.setImageResource(R.drawable.food);
                   break;
               case "Transport":
                   holder.category.setImageResource(R.drawable.transport);
                   break;
               case "Shopping":
                   holder.category.setImageResource(R.drawable.shopping);
                   break;
               default:
                   holder.category.setImageResource(R.drawable.icon);
           }

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
