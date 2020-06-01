package com.example.lab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //名字数组
    private String setname;
    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat"};
    private  int[] imageids = new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat};
    private MainActivity activity;
    private TextView edit;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.txt);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        List<Map<String,Object>> listItems = new ArrayList<>();
        for(int i = 0; i < names.length; i++){
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("animal",names[i]);
            listItem.put("image",imageids[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter SA = new SimpleAdapter(this, listItems,R.layout.simple_item,new String[]{"animal","image"},new int[]{R.id.animal,R.id.image});
        listView = findViewById(R.id.list1);
        listView.setAdapter(SA);
        // 为ListView的列表项的单击事件绑定事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()    {
            // 第position项被单击时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                setname = names[position];
                System.out.println(names[position] + "被单击了");
                //View v=parent.getChildAt(position);
                // v.setBackgroundColor(Color.RED);
                for(int i=0;i<parent.getCount();i++){
                    View v=parent.getChildAt(i);
                    if (position == i) {
                        v.setBackgroundColor(Color.RED);
                    } else {
                        v.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });
        // 为ListView的列表项的选中事件绑定事件监听器
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            // 第position项被选中时激发该方法
            // 在android模拟器中需要按键盘上下键触发选中事件
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setname = names[position];
                System.out.println(names[position] + "被选中了");
                for(int i=0;i<parent.getCount();i++){
                    View v=parent.getChildAt(i);
                    v.setBackgroundColor(Color.RED);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                System.out.println(setname);
                builder.setMessage("this is " + setname)
                        .setTitle("信息")
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                }).create().show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(getLayoutInflater().inflate(R.layout.alert_dialog,null))
                        .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .create().show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi) {
        switch (mi.getItemId()) {
            case R.id.small:
                edit.setTextSize(10);
                break;
            case R.id.middle:
                edit.setTextSize(16);
                break;
            case R.id.big:
                edit.setTextSize(20);
                break;
            case R.id.red:
                edit.setTextColor(Color.RED);
                break;
            case R.id.black:
                edit.setTextColor(Color.BLACK);
                break;

            case R.id.ordinary:
                Toast toast = Toast.makeText(MainActivity.this, "普通菜单项",
                        Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }
    private void setOverflowShowingAlways() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
