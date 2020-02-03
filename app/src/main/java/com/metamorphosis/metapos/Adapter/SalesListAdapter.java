package com.metamorphosis.metapos.Adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.metamorphosis.metapos.Model.SalesPojo;
import com.metamorphosis.metapos.R;
import com.raizlabs.android.dbflow.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class SalesListAdapter extends RecyclerView.Adapter<SalesListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<SalesPojo.SalesData> mLists;
    private String imageBytes, salesTitle, salesAmount;
    private Double salesAmount2;

    private static final String TAG = "SalesListAdapter";

    public SalesListAdapter(Context mContext, List<SalesPojo.SalesData> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.custom_layout_sales, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull SalesListAdapter.ViewHolder holder, int position) {

        salesTitle = mLists.get(position).getTitle();
        salesAmount2 = mLists.get(position).getAmount();
        Log.d(TAG, "salesAmount2: " +salesAmount2);

        //DecimalFormat decimalFormat = new DecimalFormat("0.00");
        //float twoDigitsF = Float.valueOf(decimalFormat.format(salesAmount2));

        salesAmount = String.format("%,.2f", salesAmount2);



        Log.d(TAG, "Title: " +salesTitle);
        Log.d(TAG, "Amount: " +salesAmount);

        ///imageView add here




        holder.tvSalesTitle.setText(mLists.get(position).getTitle());
        holder.tvSalesAmnt.setText(salesAmount);


    }

    @Override
    public int getItemCount() {
        System.out.println("mListSize: " +mLists.size());
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSalesTitle, tvSalesAmnt;
        ImageView iconHolder;

        public ViewHolder(View itemView) {
            super(itemView);

            tvSalesTitle = itemView.findViewById(R.id.tvSalesTitle);
            tvSalesAmnt = itemView.findViewById(R.id.tvSalesAmnt);
            iconHolder = itemView.findViewById(R.id.iconHolder);
        }
    }

}
