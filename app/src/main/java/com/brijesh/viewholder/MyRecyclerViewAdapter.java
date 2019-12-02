/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.brijesh.viewholder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    Context context;
    private ArrayList<DataObject> mDataset;
    private OnItemClickListener mListener;

    public MyRecyclerViewAdapter(Context context, ArrayList<DataObject> mDataset) {
        this.context = context;
        this.mDataset = mDataset;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder {

        private TextView desc;
        //private TextView end_date;
        private TextView end_time;
        //private String key;
        //private String link;
        private TextView name;
        private TextView link;
        //private TextView start_date;
        private TextView start_time;

        public DataObjectHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            desc =  (TextView)itemView.findViewById(R.id.desc);
            //end_date = (TextView)itemView.findViewById(R.id.end_date);
            end_time = (TextView)itemView.findViewById(R.id.end_time);
            name = (TextView)itemView.findViewById(R.id.name);
            link = (TextView)itemView.findViewById(R.id.site);
            //start_date = (TextView)itemView.findViewById(R.id.start_date);
            start_time = (TextView)itemView.findViewById(R.id.start_time);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                    }
                }
            });

        }


    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view,mListener);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.desc.setText(mDataset.get(position).getCODE());
        holder.end_time.setText(mDataset.get(position).getEND_TIME());
        holder.start_time.setText(mDataset.get(position).getSTART_TIME());
        holder.name.setText(mDataset.get(position).getNAME());
        holder.link.setText(mDataset.get(position).getURL());
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}