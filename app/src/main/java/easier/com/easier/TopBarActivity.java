package easier.com.easier;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义标题栏
 */
public class TopBarActivity extends Activity {

    private TextView mTitleTextView;
    private ImageView mBackward;
    private Button mForwardButton;
    private FrameLayout mContentLayout;
    private Button mshareButton;
    private LinearLayout linearBackward;
    private LinearLayout linearSearch;
    private LinearLayout linearSearchInput;
    private TextView confirm_Search;


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
        mBackward = findViewById(R.id.Image_backward);
        mForwardButton = findViewById(R.id.button_share);
        mshareButton = findViewById(R.id.button_share);
        linearBackward = findViewById(R.id.linearBackward);
        linearSearch = findViewById(R.id.linearSearch);
        linearSearchInput = findViewById(R.id.linearSearchInput);
        confirm_Search = findViewById(R.id.confirm_Search);
        confirm_Search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TopBarActivity.this, "搜索功能暂时未开放", Toast.LENGTH_SHORT).show();
            }
        });
        linearSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(TopBarActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        linearBackward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * 是否显示返回按钮
     *
     * @param showBack 文字
     */
    protected void showBackOrSearch(Boolean showBack) {
        if (showBack) {
            linearSearch.setVisibility(View.INVISIBLE);
            linearBackward.setVisibility(View.VISIBLE);
        } else {
            linearSearch.setVisibility(View.VISIBLE);
            linearBackward.setVisibility(View.INVISIBLE);
        }
    }

    public void showSearchInput() {
        linearSearch.setVisibility(View.INVISIBLE);
        confirm_Search.setVisibility(View.VISIBLE);
        linearSearchInput.setVisibility(View.VISIBLE);
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