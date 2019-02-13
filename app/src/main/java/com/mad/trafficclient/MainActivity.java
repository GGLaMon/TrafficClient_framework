/**
 *
 */
package com.mad.trafficclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mad.trafficclient.fragment.FragmentHome;
import com.mad.trafficclient.fragment.Fragment_1;
import com.mad.trafficclient.fragment.Fragment_10;
import com.mad.trafficclient.fragment.Fragment_12;
import com.mad.trafficclient.fragment.Fragment_16;
import com.mad.trafficclient.fragment.Fragment_2;
import com.mad.trafficclient.fragment.Fragment_3;
import com.mad.trafficclient.fragment.Fragment_4;
import com.mad.trafficclient.fragment.Fragment_5;
import com.mad.trafficclient.fragment.Fragment_6;
import com.mad.trafficclient.fragment.Fragment_7;
import com.mad.trafficclient.fragment.Fragment_8;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    private SlidingPaneLayout slidepanel;

    private Fragment fragment;

    private ListView listView;
    SimpleAdapter simpleAdapter;

    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;

    TextView tV_title;
    ImageView iVSliding;
    ImageView ivHome;
    LinearLayout bar;

    private android.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        slidepanel = (SlidingPaneLayout) findViewById(R.id.slidingPL);

        listView = (ListView) findViewById(R.id.listView1);
        ivHome = (ImageView) findViewById(R.id.imageView_home);
        ivHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setHome();
            }
        });

        iVSliding = (ImageView) findViewById(R.id.imageView_Sliding);
        tV_title = (TextView) findViewById(R.id.tv_title);
        tV_title.setText(getString(R.string.app_title));
        bar = (LinearLayout) findViewById(R.id.bar);


        iVSliding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });


        final String[] actionTexts = new String[]{
                "第1题",
                "第2题",
                "第3题",
                "第4题",
                "第5题",
                "第6题",
                "第7题",
                "第8题",
                "第9题",
                "第10题",
                "第12题",
                "第16题"


        };
        int[] actionImages = new int[]{
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book,
                R.drawable.btn_l_book


        };

        actionItems = new ArrayList<HashMap<String, Object>>();
        actionAdapter = new SimpleAdapter(getApplicationContext(), actionItems, R.layout.left_list_fragment_item,
                new String[]{"action_icon", "action_name"},
                new int[]{R.id.recharge_method_icon, R.id.recharge_method_name});

        for (int i = 0; i < actionImages.length; ++i) {
            HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("action_icon", actionImages[i]);
            item1.put("action_name", actionTexts[i]);
            actionItems.add(item1);
        }
        listView.setAdapter(actionAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_1()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_2()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_3()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_4()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_5()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_6()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 6:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_7()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 7:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_8()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 8:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_10()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 9:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_12()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    case 10:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_16()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;

                    default:
                        break;
                }

            }
        });

        fragmentManager = getFragmentManager();

        setHome();

        EventBus.getDefault().register(this);
    }

    public void setHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new FragmentHome()).commit();
        tV_title.setText(R.string.app_title);

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

        int[] listImg = new int[]{R.drawable.icon_trafic, R.drawable.icon_busstop, R.drawable.icon_lamp, R.drawable.icon_parking, R.drawable.icon_light, R.drawable.icon_etc, R.drawable.icon_speed};
        String[] listName = new String[]{"城市交通", "公交站点", "城市环境", "找车位", "红绿灯管理", "ETC管理", "高速车速监控"};
        for (int i = 0; i < listImg.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("itemImage", listImg[i]);
            item.put("itemName", listName[i]);
            items.add(item);
        }
        return items;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exitAppDialog();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void exitAppDialog() {
        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(Message message) {
        Bundle bundle;
        switch (message.what) {
            case 1://隐藏或显示标题栏
                bar.setVisibility((Integer) message.obj);
                break;
        }

    }

}
