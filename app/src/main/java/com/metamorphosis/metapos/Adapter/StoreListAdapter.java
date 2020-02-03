package com.metamorphosis.metapos.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metamorphosis.metapos.Model.StoreListPojo;
import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.AppConstant;
import com.metamorphosis.metapos.session.Session;

import java.util.List;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder> {

private LayoutInflater layoutInflater;
private Context mContext;
private List<StoreListPojo.StoreListData> mLists;
private String imageBytes, storeTitle;
private Integer storeId;

private static final String TAG = "StoreListAdapter";

public StoreListAdapter(Context mContext, List<StoreListPojo.StoreListData> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
        }

@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.custom_layout_store_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
        }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        storeTitle = mLists.get(position).getName();
        storeId = mLists.get(position).getId();

        Log.d(TAG, "Title: " +storeTitle);
        Log.d(TAG, "Amount: " +storeId);

        ///imageView add here




        holder.tvStoreTitle.setText(storeTitle);
        holder.tvStoreId.setText(String.valueOf(storeId));

        holder.tvStoreId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.saveDataAtSharePreference(mContext, AppConstant.LOGIN_RESPONSE_STORE_ID, String.valueOf(mLists.get(position).getId()));
                Log.d(TAG, "Store Id: " +Session.getStringFromSharePreference(mContext, AppConstant.LOGIN_RESPONSE_STORE_ID));
                Toast.makeText(mContext, mLists.get(position).getName()+ " is Selected", Toast.LENGTH_LONG).show();
                //holder.tvStoreId.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
            }
        });

        }

@Override
public int getItemCount() {
        System.out.println("mListSize: " +mLists.size());
        return mLists.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvStoreTitle, tvStoreId;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvStoreTitle = itemView.findViewById(R.id.tvStoreTitle);
        tvStoreId = itemView.findViewById(R.id.tvStoreId);
    }
}
}
