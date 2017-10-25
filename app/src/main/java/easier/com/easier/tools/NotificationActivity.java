package easier.com.easier.tools;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;


import easier.com.easier.R;

public class NotificationActivity extends AppCompatActivity {
    //定义notification实用的ID
    private static final int NO_3 = 0x3;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_main);
        button = (Button) findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(view);
            }
        });

    }

    public void show(View v) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.pengy);
        builder.setTicker("来自电梯宝的推荐通知");
        builder.setContentTitle("下载");
        builder.setContentText("正在下载");
//        builder.setDefaults(Notification.DEFAULT_ALL);
        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NO_3, builder.build());
        builder.setProgress(100, 0, false);
        //下载以及安装线程模拟
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    builder.setProgress(100, i, false);

                    //下载进度提示
                    builder.setContentText("下载" + i + "%");
                    manager.notify(NO_3, builder.build());
                    try {
                        Thread.sleep(50);//演示休眠50毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //下载完成后更改标题以及提示信息
                builder.setContentTitle("下载完成");
                builder.setContentText("已完成");
                manager.notify(NO_3, builder.build());
            }
        }).start();
    }
}