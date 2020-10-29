package com.ualr.recyclerviewassignment.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private List<Inbox> mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, Inbox obj, int position);

        void onIconClick(View view, Inbox obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mInboxClickListener) {
        this.mOnItemClickListener = mInboxClickListener;
    }

    public RecyclerAdapter(Context context, List<Inbox> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View inboxView = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_item_layout, parent, false);
        vh = new InboxViewHolder(inboxView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        InboxViewHolder viewHolder = (InboxViewHolder) holder;
        final Inbox inbox = mItems.get(position);

        viewHolder.senderIcon.setText(inbox.getFrom().substring(0, 1));
        viewHolder.senderName.setText(inbox.getFrom());
        viewHolder.senderEmail.setText(inbox.getEmail());
        viewHolder.emailPreview.setText(R.string.lorem_ipsum);
        viewHolder.emailTimestamp.setText(inbox.getDate());

    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public void addEmail(int position, Inbox email) {
        mItems.add(position, email);
        notifyItemInserted(position);
    }


    private class InboxViewHolder extends RecyclerView.ViewHolder {
        public TextView senderIcon;
        public TextView senderName;
        public TextView senderEmail;
        public TextView emailPreview;
        public TextView emailTimestamp;
        public View lyt_parent;

        public InboxViewHolder(View v) {
            super(v);
            senderIcon = v.findViewById(R.id.sender_icon);
            senderName = v.findViewById(R.id.sender_name);
            senderEmail = v.findViewById(R.id.sender_email);
            emailPreview = v.findViewById(R.id.email_preview);
            emailTimestamp = v.findViewById(R.id.email_timestamp);
            lyt_parent = v.findViewById(R.id.lyt_parent);

            senderIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onIconClick(view, mItems.get(getLayoutPosition()), getLayoutPosition());
                }
            });
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, mItems.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }
}

