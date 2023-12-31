package com.example.chat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 定义扩展ExpandableListView 所需要变量
    private ExpandableListView expandablelistview;
    private MyExpandAdapter myExpandAdapter;
    private ImageView signout;
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
    private String[][] User_motto = new String[][]{{"掬水月在手，弄花香盈袖", "云青青兮欲雨，水澹澹兮生烟"},
            {"但屈指西风几时来，却不道，流年暗中偷换", "如鹏向海，义无反顾", "刚日读经，柔日读史"},
            {"兰有剑影，不沾血腥", "每有一息之差，而成终身之谬"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        expandablelistview = findViewById(R.id.elv);
        signout = findViewById(R.id.signout_button);
        myExpandAdapter = new MyExpandAdapter(this, groupName, childName, groupPic, childPic, User_motto);
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
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("退出登录")
                        .setMessage("What can I hold you with?" +
                                "\n" +
                                "我用什么才能留住你？"+
                                "\n" +
                                "我给你瘦落的街道、绝望的日落、荒郊的月亮"+
                                "\n"+
                                "我给你一个久久地望着孤月的人的悲哀")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "花开花落自有时\n相聚相逢本无意", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog = builder.create();
                dialog.show();


            }
        });
    }
}
