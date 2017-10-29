package easier.com.easier.Recommend;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import easier.com.easier.DetailActivity;
import easier.com.easier.MainActivity;
import easier.com.easier.R;
import easier.com.easier.ShareActivity;
import easier.com.easier.TopBarActivity;
import easier.com.easier.tools.InterfaceActivity;
import easier.com.easier.tools.NotificationActivity;

public class RecommendActivity extends MainActivity {
    private static JSONObject jsonResp;
    private static JSONArray fileLists;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                createViews(jsonResp);
            } else {
                Toast.makeText(RecommendActivity.this, "服务器错误...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_main);
        setTitle("推荐");
        showShare();
//        SendRequest();
        createViews(jsonResp);
        showBackwardView(R.id.button_backward, false, true);
        Button button_backward = findViewById(R.id.button_backward);
        button_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecommendActivity.this.finish();
            }
        });
    }

    public void createViews(JSONObject jsonResp) {
//        fileLists = jsonResp.getJSONArray("fileNames");
        Drawable drawable = this.getDrawable(R.drawable.default_pic);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        RelativeLayout layout = this.findViewById(R.id.recommend_main);
//        int length = fileLists.size();
        int length = 20;
        Button Btn[] = new Button[length];
        SimpleDraweeView simpleDraweeView[] = new SimpleDraweeView[length];
        int j = -1;
        Uri uri = Uri.parse("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg");
        for (int i = 0; i < length; i++) {
            simpleDraweeView[i] = new SimpleDraweeView(this);
            simpleDraweeView[i].setId(5000 + i);
            simpleDraweeView[i].setImageURI(uri);
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((widthPixels - 30) / 2, 500);
            if (i % 2 == 0) {
                j++;
            }
            //横坐标定位
            btParams.leftMargin = 10 + ((widthPixels - 30) / 2 + 10) * (i % 2);
            //纵坐标定位
            btParams.topMargin = 10 + 510 * j;
            layout.addView(simpleDraweeView[i], btParams);

            final int index = i;
            simpleDraweeView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("点击文件夹", "当前点击的文件夹是" + index);
                    DetailActivity detail = new DetailActivity();
                    detail.show(getFragmentManager(), "");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            RecommendActivity.this.finish();
        }
        return false;
    }
}
