package easier.com.easier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 自定义标题栏
 */
public class TopBarActivity extends Activity {

    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;
    private Button mshareButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();   //加载 activity_title 布局 ，并获取标题及两侧按钮
        share();
    }


    private void setupViews() {
        super.setContentView(R.layout.topbar_main);
        mTitleTextView = findViewById(R.id.text_title);
        mContentLayout = findViewById(R.id.topbar_main);
        mBackwardbButton = findViewById(R.id.button_backward);
        mForwardButton = findViewById(R.id.button_share);
        mshareButton = findViewById(R.id.button_share);
    }

    /**
     * 是否显示返回按钮
     *
     * @param backwardResId 文字
     * @param showTitle     true则显示文字
     * @param showImage     true则显示图标
     */
    protected void showBackwardView(int backwardResId, boolean showTitle, boolean showImage) {
        if (showImage) {
            if (mBackwardbButton != null) {
                if (showTitle) {
                    mBackwardbButton.setText(backwardResId);
                    mBackwardbButton.setVisibility(View.VISIBLE);
                } else {
                    mBackwardbButton.setVisibility(View.VISIBLE);
                }
            } // else ignored
        } else {
            mBackwardbButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 提供是否显示提交按钮
     *
     * @param forwardResId 文字
     * @param showTitle    true则显示文字
     * @param showImage    true则显示图标
     */
    protected void showForwardView(int forwardResId, boolean showTitle, boolean showImage) {
        if (showImage) {
            if (mForwardButton != null) {
                if (showTitle) {
                    mForwardButton.setVisibility(View.VISIBLE);
                    mForwardButton.setText(forwardResId);
                } else {
                    mForwardButton.setVisibility(View.VISIBLE);
                }
            } // else ignored
        } else {
            mForwardButton.setVisibility(View.INVISIBLE);
        }
    }

    public void showShare() {
        mshareButton.setVisibility(View.VISIBLE);
    }

    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }


    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }

    private void share() {
        mshareButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareActivity shareDialog = new ShareActivity();
                shareDialog.show(getFragmentManager(), "");
            }
        });
    }
}