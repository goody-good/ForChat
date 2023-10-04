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
    private MyDBhelper myDBhelper;
    // 好友群组名称设定
    // 群组名称
    private String[] groupName = new String[]{"家人", "大学", "中学"};
    private int[] groupPic = new int[]{R.drawable.g3, R.drawable.g2,
            R.drawable.g1};
    // 好友名称
    private String[][] childName = new String[][]{{"Lily", "Jack"},
            {"Alan", "Brian", "Tom"}, {"Bowen", "Frank"}};
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
                String groupName1 = MainActivity.this.groupName[groupPosition];
                String childName1 = MainActivity.this.childName[groupPosition][childPosition];
                if (groupName1.equals("家人")) {
                    if (childName1.equals("Lily")) {
                        //myMessage message=(myMessage)ChatAdapter.getItem(groupPosition,childPosition)
                        Intent intent = new Intent(MainActivity.this, LilyActivity.class);
                        startActivity(intent);
                    } else if (childName1.equals("Jack")) {
                        Intent intent = new Intent(MainActivity.this, JackActivity.class);
                        startActivity(intent);
                    }
                } else if (groupName1.equals("大学")) {
                    if (childName1.equals("Tom")) {
                        Intent intent = new Intent(MainActivity.this, TomActivity.class);
                        startActivity(intent);
                    } else if (childName1.equals("Alan")) {
                        Intent intent = new Intent(MainActivity.this, AlanActivity.class);
                        startActivity(intent);
                    } else if (childName1.equals("Brian")) {
                        Intent intent = new Intent(MainActivity.this, BrianActivity.class);
                        startActivity(intent);
                    }
                } else if (groupName1.equals("中学")) {
                    if (childName1.equals("Bowen")) {
                        Intent intent = new Intent(MainActivity.this, BowenActivity.class);
                        startActivity(intent);
                    } else if (childName1.equals("Frank")) {
                        Intent intent = new Intent(MainActivity.this, FrankActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
    }
}
