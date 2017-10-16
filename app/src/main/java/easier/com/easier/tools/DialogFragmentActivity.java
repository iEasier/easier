package easier.com.easier.tools;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/10/16.
 */

public class DialogFragmentActivity extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View view = inflater.inflate(R.layout.share_main, container);
//        return view;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Window dialogWindow = getDialog().getWindow();
//        dialogWindow.setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.style_white)));
//        WindowManager.LayoutParams layoutPosition = dialogWindow.getAttributes();
//        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
//        layoutPosition.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        layoutPosition.height = 400;
//        dialogWindow.setAttributes(layoutPosition);
//    }
}
