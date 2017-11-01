package easier.com.easier;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import easier.com.easier.tools.DialogFragmentActivity;

public class ShareActivity extends DialogFragmentActivity {
    private Button share_qq;
    private Button share_weixin;
    private TextView share_cancel;
    // 向微信注册自己的APP_ID
    private static final String App_ID = "wxf359b1eeb4790d8b";
    private static final String AppSecret = "d47c7f016918c5cd135f09aab5eafb14";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);
        api = WXAPIFactory.createWXAPI(getActivity(), App_ID, true);
        api.registerApp(App_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.share_main, container);
        share_qq = view.findViewById(R.id.share_qq);
        share_weixin = view.findViewById(R.id.share_weixin);
        share_cancel = view.findViewById(R.id.share_cancel);
        shareClick();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window dialogWindow = getDialog().getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.style_white)));
        WindowManager.LayoutParams layoutPosition = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        layoutPosition.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutPosition.height = 400;
        dialogWindow.setAttributes(layoutPosition);
    }

    private void shareClick() {
        // QQ分享事件
        share_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "QQ分享暂未开放",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // 微信分享事件
        share_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareToWebChat(0);
            }
        });
        // 取消分享
        share_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("取消：", "取消分享执行！");
                ShareActivity.this.dismiss();
            }
        });
    }

    public void shareToWebChat(int flag) {

        if (!api.isWXAppInstalled()) {
            Toast.makeText(getActivity(), "您还未安装微信客户端",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = "mylove.vicp.net";
        WXMediaMessage msg = new WXMediaMessage(webPage);

        msg.title = "电梯宝";
        msg.description = "致力于搬运文档，分析经验，解决问题的原则.针对各种款式的电梯详细参考文档、密码及常见问题处理方案的App，为用户提供标准的资料途径！";
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.share_pic);
        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag;
        api.sendReq(req);
    }
}
