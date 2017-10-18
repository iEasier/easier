package easier.com.easier;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import easier.com.easier.tools.InterfaceActivity;
import easier_interface.SourceFiles;

public class ResourceActivity extends TopBarActivity implements SourceFiles {
    private ArrayList<Object> fileLists = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_main);
        setTitle("技术库资料");
        showBackwardView(R.id.button_backward, false, true);
        showShare();
        createViews(20);
        Button button_backward = findViewById(R.id.button_backward);
        button_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResourceActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("進入", "Resources If");
            ResourceActivity.this.finish();
            return true;
        }
        Log.i("進入", "Resources Or");
        return false;
    }

    public void createViews(int length) {
        InterfaceActivity in = new InterfaceActivity();
        in.SendRequest();
        Drawable drawable = this.getDrawable(R.drawable.file);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        RelativeLayout layout = this.findViewById(R.id.resources_Relative);
        ArrayList<Object> fileNames = getFileList("");
        Button Btn[] = new Button[fileNames.size()];
        TextView Tex[] = new TextView[fileNames.size()];
        int j = -1;
        for (int i = 0; i < 20; i++) {
            Btn[i] = new Button(this);
            Btn[i].setId(2000 + i);
            Btn[i].setBackground(drawable);
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((widthPixels - 50) / 4, 300);
            if (i % 4 == 0) {
                j++;
            }
            btParams.leftMargin = 10 + ((widthPixels - 50) / 4 + 10) * (i % 4);   //横坐标定位
            btParams.topMargin = 20 + 400 * j;   //纵坐标定位
            layout.addView(Btn[i], btParams);   //将按钮放入layout组件

            Tex[i] = new TextView(this);
            Tex[i].setText("" + fileNames.get(i));
            Tex[i].setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams TvParams = new RelativeLayout.LayoutParams((widthPixels - 50) / 4, 100);
            TvParams.leftMargin = 10 + ((widthPixels - 50) / 4 + 10) * (i % 4);   //横坐标定位
            TvParams.addRule(RelativeLayout.BELOW, 2000 + i);
            layout.addView(Tex[i], TvParams);   //将按钮放入layout组件
        }
    }

    public ArrayList<Object> getFileList(String strPath) {
        File dir = new File("/sdcard");
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        String fileNames;
        for (int i = 0; i < 100; i++) {
            fileNames = "新建文件夹" + i;
            fileLists.add(fileNames);
        }
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                Log.i("getFileList:===", "" + fileName);
//                if (files[i].isDirectory()) { // 判断是文件还是文件夹
//                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
//                } else if (fileName.endsWith("avi")) { // 判断文件名是否以.avi结尾
//                    String strFileName = files[i].getAbsolutePath();
////                    Log.i("getFileList:===", "" + strFileName);
//                    fileLists.add(files[i]);
//                } else {
//                    continue;
//                }
            }
        }
        return fileLists;
    }

    @Override
    public List getSourceNames(String s) {
        return null;
    }
}
