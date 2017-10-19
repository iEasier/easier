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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import easier.com.easier.MainActivity;
import easier.com.easier.R;
import easier.com.easier.TopBarActivity;
import easier.com.easier.tools.InterfaceActivity;


public class ResourceSubActivity extends TopBarActivity {
    private static JSONObject jsonResp;
    private Button button_backward;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                createSubViews(jsonResp);
            } else {
                Toast.makeText(ResourceSubActivity.this, "服务器错误...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_sub);
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("fileName");
        setTitle(fileName);
        showBackwardView(R.id.button_backward, false, true);
        showShare();
        SendRequest("getFileContent",fileName);
        button_backward = findViewById(R.id.button_backward);
        button_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResourceSubActivity.this.finish();
            }
        });

    }

    public void SendRequest(final String interfaceName, final String requestParams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                jsonResp = new InterfaceActivity().SendRequest(interfaceName, requestParams);
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

    public void createSubViews(JSONObject jsonResp) {
        JSONArray contentNames = jsonResp.getJSONArray("contentNames");
        int ListLength = contentNames.size();
        Drawable drawable = this.getDrawable(R.drawable.line);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        RelativeLayout layout = this.findViewById(R.id.resources_sub);
        TextView TexName[] = new TextView[ListLength];
        TextView TexSize[] = new TextView[ListLength];
        ImageView iView[] = new ImageView[ListLength];
        for (int i = 0; i < ListLength; i++) {
            TexName[i] = new TextView(this);
            TexName[i].setText("傲慢与偏见第" + i + "章节");
            TexName[i].setId(3000 + i);
            TexName[i].setGravity(Gravity.LEFT | Gravity.CENTER);
            TexName[i].setTextSize(16);
            TexName[i].setWidth(widthPixels - 200);
            TexName[i].setHeight(120);
            RelativeLayout.LayoutParams TexNameParams = new RelativeLayout.LayoutParams(widthPixels - 200, ViewGroup.LayoutParams.WRAP_CONTENT);
            TexNameParams.leftMargin = 20;
            if (i > 0) {
                TexNameParams.addRule(RelativeLayout.BELOW, 3000 + i - 1);
            }
            layout.addView(TexName[i], TexNameParams);

            TexSize[i] = new TextView(this);
            TexSize[i].setText(i + " Mb");
            TexSize[i].setId(4000 + i);
            TexSize[i].setTextSize(12);
            TexSize[i].setGravity(Gravity.RIGHT | Gravity.CENTER);
            TexSize[i].setHeight(120);
            RelativeLayout.LayoutParams TexSizeParams = new RelativeLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
            TexSizeParams.rightMargin = 20;
            TexSizeParams.addRule(RelativeLayout.RIGHT_OF, 3000 + i);
            if (i > 0) {
                TexSizeParams.addRule(RelativeLayout.BELOW, 4000 + i - 1);
            }
            layout.addView(TexSize[i], TexSizeParams);

            iView[i] = new ImageView(this);
            iView[i].setBackground(drawable);
            RelativeLayout.LayoutParams iViewParams = new RelativeLayout.LayoutParams(widthPixels, 1);
            iViewParams.addRule(RelativeLayout.BELOW, 3000 + i);
            layout.addView(iView[i], iViewParams);
        }
    }
}