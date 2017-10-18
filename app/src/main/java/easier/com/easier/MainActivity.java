package easier.com.easier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends TopBarActivity implements View.OnClickListener {
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button home_RecommendFocus;
    private Button home_Focus;
    private Button home_SettingFocus;
    private Button home_Recommend;
    private Button home;
    private Button home_Setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Log.i("KeyEvent.keyCode",""+keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MainActivity.this.finish();
            System.exit(0);
            Log.i("進入", "if");
            return true;
        }
        Log.i("進入", "or");
        return false;
    }

    public void changeStatus() {
        home_RecommendFocus = findViewById(R.id.home_RecommendFocus);
        home_Focus = findViewById(R.id.home_Focus);
        home_SettingFocus = findViewById(R.id.home_SettingFocus);
        home_Recommend = findViewById(R.id.home_Recommend);
        home = findViewById(R.id.home);
        home_Setting = findViewById(R.id.home_Setting);
        home_RecommendFocus.setOnClickListener(this);
        home_Focus.setOnClickListener(this);
        home_SettingFocus.setOnClickListener(this);
        home_Recommend.setOnClickListener(this);
        home.setOnClickListener(this);
        home_Setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_Recommend:
                home_RecommendFocus.setVisibility(View.VISIBLE);
                home_Focus.setVisibility(View.INVISIBLE);
                home_SettingFocus.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "推荐处于维护中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                home_Focus.setVisibility(View.VISIBLE);
                home_SettingFocus.setVisibility(View.INVISIBLE);
                home_RecommendFocus.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "当前无最新内容", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_Setting:
                home_SettingFocus.setVisibility(View.VISIBLE);
                home_Focus.setVisibility(View.INVISIBLE);
                home_RecommendFocus.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "设置处于维护中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
