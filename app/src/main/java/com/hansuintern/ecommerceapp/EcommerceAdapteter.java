package com.hansuintern.ecommerceapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class EcommerceAdapteter extends RecyclerView.Adapter<EcommerceAdapteter.EcommerceViewHolder>  {
    private Context mcontext;
    List<Order> orderList;
    EcommerceListItemClickListener listItemClickListener;


    public EcommerceAdapteter(Context mcontext ,EcommerceListItemClickListener listener) {
        this.mcontext = mcontext;
        this.listItemClickListener = listener;
    }
    public interface EcommerceListItemClickListener{
        void onItemClick(int itemId);
    }

    @Override
    public  EcommerceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate
                (R.layout.ecommerce_itemlist,parent,false);

        return new EcommerceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EcommerceViewHolder holder, int position) {
    Order order=orderList.get(position);
    String item=order.getItem();
    String amount= order.getAmount();
    String date= order.getDate();
    String comment = order.getComment();

    holder.itemName.setText(item);
    holder.amount.setText(amount);
    holder.date.setText(date);
    holder.comment.setText(comment);







    }

    @Override
    public int getItemCount() {
        if(orderList==null){
            return 0;
        }

        return orderList.size();
    }

    public class EcommerceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName;
        TextView amount;
        TextView date;
        TextView comment;

        public EcommerceViewHolder(View itemView) {
            super(itemView);
            itemName=(itemView).findViewById(R.id.itemView);
            amount=(itemView).findViewById(R.id.amountView);
            date=(itemView).findViewById(R.id.dateView);
            comment=(itemView).findViewById(R.id.commentView);

            // it helps in displaying the content in the Details class//
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemId = orderList.get(getAdapterPosition()).getId();
            listItemClickListener.onItemClick(itemId);

        }
    }
public void setOrderListData(List<Order> orderListData){
        orderList=orderListData;
        notifyDataSetChanged();

}

}