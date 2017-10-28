package easier.com.easier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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
    // 向微信注册自己的APP_ID
    private static final String App_ID = "wxf359b1eeb4790d8b";
    private static final String AppSecret = "d47c7f016918c5cd135f09aab5eafb14";
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        RegisterWeChat();
        setTitle("电梯");
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
                shareToweixin(0);
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
        home_Recommend = findViewById(R.id.home_Recommend);
        home = findViewById(R.id.home);
        home_Setting = findViewById(R.id.home_Setting);
        home_Recommend.setOnClickListener(this);
        home.setOnClickListener(this);
        home_Setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_Recommend:
                home.setBackground(getDrawable(R.drawable.home));
                home_Recommend.setBackground(getDrawable(R.drawable.home_recommendfocus));
                home_Setting.setBackground(getDrawable(R.drawable.home_setting));
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RecommendActivity.class);
                startActivity(intent);
                break;
            case R.id.home:
                home.setBackground(getDrawable(R.drawable.home_focus));
                home_Recommend.setBackground(getDrawable(R.drawable.home_recommend));
                home_Setting.setBackground(getDrawable(R.drawable.home_setting));
                Toast.makeText(this, "当前无最新内容", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_Setting:
                home.setBackground(getDrawable(R.drawable.home));
                home_Setting.setBackground(getDrawable(R.drawable.home_settingfocus));
                home_Recommend.setBackground(getDrawable(R.drawable.home_recommend));
                Toast.makeText(this, "设置处于维护中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void RegisterWeChat() {
        api = WXAPIFactory.createWXAPI(this, App_ID, true);
        api.registerApp(App_ID);

    }

    @Override
    protected void onStart() {
        home.setBackground(getDrawable(R.drawable.home_focus));
        home_Recommend.setBackground(getDrawable(R.drawable.home_recommend));
        home_Setting.setBackground(getDrawable(R.drawable.home_setting));
        super.onStart();
    }

    private void shareToweixin(int flag) {

        if (!api.isWXAppInstalled()) {
            Toast.makeText(MainActivity.this, "您还未安装微信客户端",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "www.baidu.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);

        msg.title = "谷歌主站";
        msg.description = "谷歌公司（Google Inc.）成立于1998年9月4日，由拉里·佩奇和谢尔盖·布林共同创建，被公认为全球最大的搜索引擎。";

        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.renren);

        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag;
        api.sendReq(req);
    }
}
