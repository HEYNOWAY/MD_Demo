package com.example.luos.demofortest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luos on 2016/9/24.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<NewsBean> mDataList;
    private Context context;
    private OnRecyclerViewItemClickListener mItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mContentTextView;
        public CardView mCardView;
        public ImageButton mShareImageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.content_image);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title_textview);
            mContentTextView = (TextView) itemView.findViewById(R.id.content_textview);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mShareImageButton = (ImageButton) itemView.findViewById(R.id.share_button);
        }
    }

    public RecyclerViewAdapter(Context context, ArrayList<NewsBean> list) {
        this.mDataList = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsBean currentItem = mDataList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageId());
        holder.mTitleTextView.setText(currentItem.getTitle());
        holder.mContentTextView.setText(currentItem.getContent());
        holder.mCardView.setOnClickListener(this);
        holder.mShareImageButton.setOnClickListener(this);
        holder.mCardView.setTag(currentItem);
    }

    @Override
    public void onClick(View v) {
        NewsBean item = (NewsBean) v.getTag();
        mItemClickListener.OnItemClick(v, item);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mItemClickListener = listener;
    }


}
