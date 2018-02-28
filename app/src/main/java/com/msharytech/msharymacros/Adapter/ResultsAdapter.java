package com.msharytech.msharymacros.Adapter;

import android.accounts.Account;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.msharytech.msharymacros.R;
import com.msharytech.msharymacros.RealmManager;
import com.msharytech.msharymacros.Result;
import com.msharytech.msharymacros.Utils;

import java.util.List;

import io.realm.Realm;

/**
 * Created by pcarrillo on 17/09/2015.
 */
public class ResultsAdapter extends BaseRecyclerViewAdapter<ResultsAdapter.CategoryViewHolder> {

    private List<Result> mResultsList;
    private int lastPosition = -1;
    private BaseViewHolder.RecyclerClickListener onRecyclerClickListener;

    public static class CategoryViewHolder extends BaseViewHolder {

        public TextView tvintake;
        public ImageButton imagebutonDelete;
        public TextView tvburned;
        public TextView tvdate;

        public CategoryViewHolder(View v, RecyclerClickListener onRecyclerClickListener) {
            super(v, onRecyclerClickListener);
            tvdate = (TextView) v.findViewById(R.id.txDays);
            tvburned = (TextView) v.findViewById(R.id.txCalBurnNum);
            tvintake = (TextView) v.findViewById(R.id.txIntakeNum);
            imagebutonDelete = (ImageButton) v.findViewById(R.id.imageButtonDelete);
            imagebutonDelete.setClickable(false);

        }

    }

    public ResultsAdapter(List<Result> ResultList, BaseViewHolder.RecyclerClickListener recyclerClickListener) {
        this.mResultsList = ResultList;
        this.onRecyclerClickListener = recyclerClickListener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_result_item, parent, false);
        return new CategoryViewHolder(v, onRecyclerClickListener);
    }



    @Override
    public void onBindViewHolder(ResultsAdapter.CategoryViewHolder holder, final int position) {
        final Result result = mResultsList.get(position);
        holder.tvintake.setText(String.valueOf(result.getIntake()));
        holder.tvburned.setText(String.valueOf(result.getCalBurned()));

        long timestampindays=Utils.getTimeStampToDays(result.getTimestamp());
        if(timestampindays==0)
            holder.tvdate.setText("today");
        else if(timestampindays==0)
            holder.tvdate.setText("yesterday");
        else
        holder.tvdate.setText(String.valueOf((int)timestampindays+" Day"));
        holder.itemView.setTag(result);
        holder.itemView.setSelected(isSelected(position));
        setAnimation(holder, position);
    }

    @Override
    public int getItemCount() {
        return mResultsList.size();
    }

    public void updateResults(List<Result> accountList) {
        this.mResultsList = accountList;
        notifyDataSetChanged();
    }

    private void setAnimation(CategoryViewHolder holder, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(Utils.getContext(), R.anim.push_left_in);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

}