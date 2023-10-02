package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chat.javabean.LilyMsg;

import java.util.List;

public class LilyAdapter extends BaseAdapter {
    //使用List<myMessage>，存储myMessage表中所有记录
    private List<LilyMsg> messageList;
    //LayoutInflater将某个布局转换成View对象
    private LayoutInflater layoutInflater;
    //当创建Adapter_Chat对象的时候，我需要messageList数据
    public LilyAdapter(Context context, List<LilyMsg> messageList){
        this.messageList=messageList;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        //messageList.get(position)以myMessage对象，对应表中某条记录
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertview==null){
            convertview=layoutInflater.inflate(R.layout.sender_layout,null,false);
            viewHolder=new ViewHolder(convertview);
            convertview.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertview.getTag();
        }
        //将数据库中的内容加载到对应控件上
        LilyMsg message=(LilyMsg) getItem(position);
        viewHolder.t_content.setText(message.getContent());
        return convertview;
    }
    //ViewHolder用于给item试图加载数据
    class ViewHolder{
        TextView t_content;
        public ViewHolder(View view){
            t_content=view.findViewById(R.id.sender_message);
        }
    }
}
