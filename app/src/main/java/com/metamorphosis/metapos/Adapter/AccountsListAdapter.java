package com.metamorphosis.metapos.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metamorphosis.metapos.Model.AccountsPojo;
import com.metamorphosis.metapos.R;

import java.util.List;

public class AccountsListAdapter extends RecyclerView.Adapter<AccountsListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<AccountsPojo.AccountsData> mLists;
    private String imageBytes, accountsTitle, accountsAmount;
    private Double acctAmount2;

    private static final String TAG = "AccountsListAdapter";

    public AccountsListAdapter(Context mContext, List<AccountsPojo.AccountsData> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.custom_layout_accounts, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        accountsTitle = mLists.get(position).getTitle();
        acctAmount2 = mLists.get(position).getAmount();

        accountsAmount = String.format("%,.2f", acctAmount2);

        Log.d(TAG, "Title: " +accountsTitle);
        Log.d(TAG, "Amount: " +accountsAmount);

        ///imageView add here




        holder.tvAcctTitle.setText(mLists.get(position).getTitle());
        holder.tvAcctAmnt.setText(accountsAmount);

    }

    @Override
    public int getItemCount() {
        System.out.println("mListSize: " +mLists.size());
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAcctTitle, tvAcctAmnt;
        ImageView iconHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAcctTitle = itemView.findViewById(R.id.tvSalesTitle);
            tvAcctAmnt = itemView.findViewById(R.id.tvSalesAmnt);
            iconHolder = itemView.findViewById(R.id.iconHolder);
        }
    }
}
