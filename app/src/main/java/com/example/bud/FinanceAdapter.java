package com.example.bud;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder> {
    private Context mContext;
    private Cursor mCursor;


    public FinanceAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class FinanceViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView countText;
        public TextView dateText;

        public FinanceViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
            dateText = itemView.findViewById(R.id.textview_date_item);
        }
    }

    @NonNull
    @Override
    public FinanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.account_item, parent, false);
        return new FinanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinanceViewHolder financeViewHolder, int i) {
        if (!mCursor.move(i)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.Account_COL2));
        String amount = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.Total_COL4));
        String date = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.Date_COL3));

        financeViewHolder.nameText.setText(name);
        financeViewHolder.countText.setText(amount);
        financeViewHolder.dateText.setText(date);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
