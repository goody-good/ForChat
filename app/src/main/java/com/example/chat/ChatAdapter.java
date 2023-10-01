package com.example.chat;

import android.content.Context;
import com.example.chat.javabean.myMessage;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<myMessage> messageList;
    private Context context;

    LayoutInflater inflater;

    public ChatAdapter(Context context, List<myMessage> messageList){
        this.inflater=LayoutInflater.from(context);
        this.messageList=messageList;
        this.context=context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }
    // 绑定ViewHolder和数据
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        myMessage message=messageList.get(position);
        holder.bind(message);
    }
    // 获取数据集合的大小
    @Override
    public int getItemCount() {
        return messageList.size();
    }
    // 清除聊天记录
    public void clearMessages() {
        messageList.clear();
        notifyDataSetChanged();
    }
    // 添加消息到聊天记录
    public void addMessage(myMessage message) {
        messageList.add(message);
        notifyItemInserted(messageList.size() - 1);
    }
    // 更新数据列表
    public void updateMessageList(List<myMessage> messages) {
        messageList.clear();
        messageList.addAll(messages);
        notifyDataSetChanged();
    }
    // 定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView FriendIdTextView;
        private TextView messageTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            FriendIdTextView = itemView.findViewById(R.id.friendTextView);
            messageTextView = itemView.findViewById(R.id.me);
        }

        public void bind(myMessage message) {
            FriendIdTextView.setText(message.getFriendId());
            messageTextView.setText(message.getContent());
        }
    }
}
