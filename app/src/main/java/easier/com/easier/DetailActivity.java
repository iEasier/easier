package easier.com.easier;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import easier.com.easier.tools.DialogFragmentActivity;

public class DetailActivity extends DialogFragmentActivity implements View.OnClickListener {
    private SimpleDraweeView detail_simpleDraw;
    private TextView detail_textView;
    private Button detail_cancel;
    private Button detail_call;
    private String PhoneNumber = "15800000000";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.i("here","here");
        View view = inflater.inflate(R.layout.detail_main, container);
        detail_simpleDraw = view.findViewById(R.id.detail_simpleDraw);
        detail_textView = view.findViewById(R.id.detail_textView);
        detail_cancel = view.findViewById(R.id.detail_cancel);
        detail_call = view.findViewById(R.id.detail_call);
        Uri uri = Uri.parse("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg");
        detail_simpleDraw.setImageURI(uri);
        String text = "咨询人员:方先生\n";
        text += "联系电话:" + PhoneNumber + "\n";
        text += "邮箱地址:iEasier@163.com\n";
        detail_textView.setText(text);
        detail_textView.setMovementMethod(LinkMovementMethod.getInstance());
        detail_cancel.setText("返回");
        detail_call.setText("拨打");
        detail_cancel.setOnClickListener(this);
        detail_call.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window dialogWindow = getDialog().getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.style_white)));
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_cancel:
                DetailActivity.this.dismiss();
                break;
            case R.id.detail_call:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:" + PhoneNumber));
                startActivity(intent);
                break;
        }
    }
}
