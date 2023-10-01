package com.example.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 定义扩展ExpandableListView 所需要变量
    private ExpandableListView expandablelistview;
    private MyExpandAdapter myExpandAdapter;
    // 好友群组名称设定
    // 群组名称
    private String[] groupName = new String[]{"在线好友", "大学", "中学"};
    private int[] groupPic = new int[]{R.drawable.g3, R.drawable.g2,
            R.drawable.g1};
    // 好友名称
    private String[][] childName = new String[][]{{"Lily", "Jack"},
            {"Lily", "老刘", "Tom"}, {"包包", "珊珊"}};
    private int[][] childPic = new int[][]{{R.drawable.a7, R.drawable.a6},
            {R.drawable.a3, R.drawable.a4, R.drawable.a5},
            {R.drawable.a1, R.drawable.a2}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        expandablelistview = findViewById(R.id.elv);
        myExpandAdapter = new MyExpandAdapter(this, groupName, childName, groupPic, childPic);
        expandablelistview.setAdapter(myExpandAdapter);

        expandablelistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),
                        groupName[groupPosition] + ":" + childName[groupPosition][childPosition],
                        Toast.LENGTH_SHORT).show();
                String groupName1=MainActivity.this.groupName[groupPosition];
                String childName1=MainActivity.this.childName[groupPosition][childPosition];
                if(groupName1.equals("在线好友")){
                    if(childName1.equals("Lily")){
                        Intent intent =new Intent(MainActivity.this,LilyActivity.class);
                    }

                }
                return false;
            }
        });
    }
}
