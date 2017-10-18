package easier.com.easier;

import android.content.Intent;
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

import easier.com.easier.tools.DialogFragmentActivity;

public class ShareActivity extends DialogFragmentActivity{
    private Button share_qq;
    private Button share_weixin;
    private TextView share_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);

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
                Log.i("QQ：", "分享执行！");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "http://www.baidu.com");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "QQ 分享执行！"));
            }
        });
        // 微信分享事件
        share_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("微信：", "分享执行！");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra ( "Kdescription" ,R.drawable.weixin) ;
                intent.putExtra(Intent.EXTRA_TEXT, "http://www.baidu.com");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "微信 分享执行！"));
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
}
