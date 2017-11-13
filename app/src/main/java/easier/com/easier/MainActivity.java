package easier.com.easier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import easier.com.easier.Recommend.RecommendActivity;
import easier.com.easier.Resource.ResourceActivity;


public class MainActivity extends TopBarActivity implements View.OnClickListener {
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button home_Recommend;
    private Button home;
    private Button home_Setting;
    //    tips
    private TextView home_tips;
    private TextView recommend_tips;
    private TextView setting_tips;
    private int tipsFocusColor;
    private int tipColor;

    private LinearLayout homes;
    private LinearLayout home_Recommends;
    private LinearLayout home_Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        setTitle("电梯");
        showBackOrSearch(false);
        showShare();
        changeStatus();
        // 首页六个按钮
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnOne", "btnOne点击事件");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ResourceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnTwo", "btnTwo点击事件");
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnThree", "btnThree点击事件");
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnFour", "btnFour点击事件");
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnFive", "btnFive点击事件");
            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnSix", "btnSix点击事件");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("KeyEvent.keyCode", "" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MainActivity.this.finish();
            System.exit(0);
            Log.i("進入", "if");
            return true;
        } else {
            Log.i("進入", "else");
        }
        return false;
    }

    public void changeStatus() {
        tipsFocusColor = getResources().getColor(R.color.home_tipsFocus);
        tipColor = getResources().getColor(R.color.home_tips);
        home_Recommend = findViewById(R.id.home_Recommend);
        home = findViewById(R.id.home);
        home_Setting = findViewById(R.id.home_Setting);
        //        tips
        home_tips = findViewById(R.id.home_tips);
        recommend_tips = findViewById(R.id.recommend_tips);
        setting_tips = findViewById(R.id.setting_tips);

        home_Recommends = findViewById(R.id.home_Recommends);
        homes = findViewById(R.id.homes);
        home_Settings = findViewById(R.id.home_Settings);

        home_Recommends.setOnClickListener(this);
        homes.setOnClickListener(this);
        home_Settings.setOnClickListener(this);

        home_Recommend.setOnClickListener(this);
        home.setOnClickListener(this);
        home_Setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_Recommend:
            case R.id.home_Recommends:
                home.setBackground(getDrawable(R.drawable.home));
                home_Recommend.setBackground(getDrawable(R.drawable.home_recommendfocus));
                home_Setting.setBackground(getDrawable(R.drawable.home_setting));

                recommend_tips.setTextColor(tipsFocusColor);
                home_tips.setTextColor(tipColor);
                setting_tips.setTextColor(tipColor);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RecommendActivity.class);
                startActivity(intent);
                break;
            case R.id.home:
            case R.id.homes:
                recommend_tips.setTextColor(tipColor);
                home_tips.setTextColor(tipsFocusColor);
                setting_tips.setTextColor(tipColor);

                home.setBackground(getDrawable(R.drawable.home_focus));
                home_Recommend.setBackground(getDrawable(R.drawable.home_recommend));
                home_Setting.setBackground(getDrawable(R.drawable.home_setting));
                Toast.makeText(this, "当前无最新内容", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_Setting:
            case R.id.home_Settings:
                recommend_tips.setTextColor(tipColor);
                home_tips.setTextColor(tipColor);
                setting_tips.setTextColor(tipsFocusColor);

                home.setBackground(getDrawable(R.drawable.home));
                home_Setting.setBackground(getDrawable(R.drawable.home_settingfocus));
                home_Recommend.setBackground(getDrawable(R.drawable.home_recommend));
                Toast.makeText(this, "设置处于维护中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onStart() {
        home.setBackground(getDrawable(R.drawable.home_focus));
        home_Recommend.setBackground(getDrawable(R.drawable.home_recommend));
        home_Setting.setBackground(getDrawable(R.drawable.home_setting));

        recommend_tips.setTextColor(tipColor);
        home_tips.setTextColor(tipsFocusColor);
        setting_tips.setTextColor(tipColor);
        super.onStart();
    }
}
