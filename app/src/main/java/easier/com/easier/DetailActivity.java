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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import easier.com.easier.tools.DialogFragmentActivity;

public class DetailActivity extends DialogFragmentActivity implements View.OnClickListener {
    private SimpleDraweeView detail_simpleDraw;
    private LinearLayout detail_View;
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
        //获取GenericDraweeHierarchy对象
        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(getResources())
                //设置圆形圆角参数
                //.setRoundingParams(rp)
                //设置圆角半径
                //.setRoundingParams(RoundingParams.fromCornersRadius(20))
                //分别设置左上角、右上角、左下角、右下角的圆角半径
//                .setRoundingParams(RoundingParams.fromCornersRadii(100,100,100,100))
//                设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
                .setRoundingParams(RoundingParams.asCircle())
                //设置淡入淡出动画持续时间(单位：毫秒ms)
                .setFadeDuration(5000)
                //构建
                .build();
        Intent intent = getActivity().getIntent();
        String uri = intent.getStringExtra("uri");
        Log.i("here", "" + uri);
        RoundingParams rp = new RoundingParams();
        rp.setRoundAsCircle(true);
        View view = inflater.inflate(R.layout.detail_main, container);
        detail_View = view.findViewById(R.id.detail_View);
        Drawable drawable = getActivity().getDrawable(R.drawable.dialog);
        detail_View.setBackground(drawable);
        detail_simpleDraw = view.findViewById(R.id.detail_simpleDraw);
        detail_textView = view.findViewById(R.id.detail_textView);
        detail_cancel = view.findViewById(R.id.detail_cancel);
        detail_call = view.findViewById(R.id.detail_call);
//        Uri uri = Uri.parse("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg");
        detail_simpleDraw.setImageURI(uri);
        detail_simpleDraw.setHierarchy(hierarchy);
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
        dialogWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), 900);
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
