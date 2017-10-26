package easier.com.easier.Resource;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import easier.com.easier.R;
import easier.com.easier.TopBarActivity;
import easier.com.easier.tools.InterfaceActivity;


public class ResourceActivity extends TopBarActivity {

    private static JSONObject jsonResp;
    private static JSONArray fileLists;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                createViews(jsonResp);
            } else {
                Toast.makeText(ResourceActivity.this, "服务器错误...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_main);
        setTitle("技术库资料");
        showBackwardView(R.id.button_backward, false, true);
        showShare();
        SendRequest("getFileList");
        Button button_backward = findViewById(R.id.button_backward);
        button_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResourceActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ResourceActivity.this.finish();
            return true;
        }
        return false;
    }

    public void createViews(JSONObject jsonResp) {
        fileLists = jsonResp.getJSONArray("fileNames");
        Drawable drawable = this.getDrawable(R.drawable.file);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        int heightPixel = (widthPixels - 50) / 4 + 140;
        RelativeLayout layout = this.findViewById(R.id.resources_Relative);
        int length = fileLists.size();
        Button Btn[] = new Button[length];
        TextView Tex[] = new TextView[length];
        int j = -1;
        for (int i = 0; i < length; i++) {
            Btn[i] = new Button(this);
            Btn[i].setId(2000 + i);
            Btn[i].setBackground(drawable);
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((widthPixels - 50) / 4, 300);
            if (i % 4 == 0) {
                j++;
            }
            //横坐标定位
            btParams.leftMargin = 10 + ((widthPixels - 50) / 4 + 10) * (i % 4);
            //纵坐标定位
            btParams.topMargin = 20 + heightPixel * j;
            layout.addView(Btn[i], btParams);
            Tex[i] = new TextView(this);
            Tex[i].setText("" + fileLists.get(i));
            Tex[i].setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams TvParams = new RelativeLayout.LayoutParams((widthPixels - 50) / 4, 100);
            TvParams.leftMargin = 10 + ((widthPixels - 50) / 4 + 10) * (i % 4);
            TvParams.addRule(RelativeLayout.BELOW, 2000 + i);
            layout.addView(Tex[i], TvParams);
            final int index = i;
            Btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("点击文件夹", "当前点击的文件夹是" + fileLists.get(index));
                    Intent intent = new Intent();
                    intent.setClass(ResourceActivity.this, ResourceSubActivity.class);
                    intent.putExtra("fileName", "" + fileLists.get(index));
                    startActivity(intent);
                }
            });
        }
    }

    public void SendRequest(final String interfaceName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                jsonResp = new InterfaceActivity().SendRequest(interfaceName);
                Message message = new Message();
                int isSuccess = -1;
                if ((int) jsonResp.get("retCode") == 0) {
                    isSuccess = 0;
                } else {
                    isSuccess = -1;
                }
                message.what = isSuccess;
                handler.sendMessage(message);
            }
        }).start();
    }
}