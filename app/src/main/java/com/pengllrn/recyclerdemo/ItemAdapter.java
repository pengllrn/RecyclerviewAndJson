package com.pengllrn.recyclerdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private List<Fruits> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView fruitname;
        TextView fruitid;
        public ViewHolder(View itemView) {
            super(itemView);
            fruitid = (TextView) itemView.findViewById(R.id.tv_id);
            fruitname = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
    public ItemAdapter(List<Fruits> fruits){
        mFruitList=fruits;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruits fruit = mFruitList.get(position);
        holder.fruitid.setText(fruit.getId());
        holder.fruitname.setText(fruit.getFruitname());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }



}
