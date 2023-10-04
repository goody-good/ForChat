package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.javabean.LilyMsg;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<LilyMsg> messageList;
    private LayoutInflater layoutInflater;

    public ChatAdapter(Context context) {
        this.messageList =new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
    }
    //新增方法，用于设置消息列表
    public void setMessageList(List<LilyMsg> messageList){
        this.messageList=messageList;
        notifyDataSetChanged();//数据更新后通知适配器刷新界面
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.sender_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LilyMsg message = messageList.get(position);
        holder.t_content.setText(message.getContent());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView t_content;

        public ViewHolder(View view) {
            super(view);
            t_content = view.findViewById(R.id.sender_message);
        }
    }
}
