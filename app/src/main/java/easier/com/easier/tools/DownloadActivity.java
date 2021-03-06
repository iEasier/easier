package easier.com.easier.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import easier.com.easier.MainActivity;
import easier.com.easier.R;
import easier.com.easier.Resource.ResourceSubActivity;

public class DownloadActivity extends DialogFragmentActivity {
    private static String rootPath = "/sdcard/easier/";
    private static String Tips = "文件已存在";
    private static String ENCODING_UTF_8 = "UTF-8";
    private static final int NO_4 = 0x4;

    public static int Download(String UrlPath, Context context) {
        Log.i("UrlPath", UrlPath);
        File rootFile = new File(rootPath);
        rootFile.mkdirs();
        int startIndex = UrlPath.lastIndexOf("/") + 1;
        String fileName = UrlPath.substring(startIndex, UrlPath.length());
        try {
            UrlPath = UrlPath.substring(0, startIndex) + URLEncoder.encode(fileName, ENCODING_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String downloadPath = rootPath + fileName;
        File subFile = new File(downloadPath);
        if (!subFile.exists()) {
            Toast.makeText(context, "正在下载中..." + fileName, Toast.LENGTH_SHORT).show();
            int status = downloadContent(UrlPath, downloadPath, context);
            Log.i("status1", "" + status);
            return status;
        } else {
            return 999;
        }
    }

    public static int downloadContent(String UrlPath, String downloadPath, Context context) {
        if (!URLUtil.isNetworkUrl(UrlPath)) {
            Toast.makeText(context, "媒体路径错误", Toast.LENGTH_SHORT).show();
            return -1;
        } else {
            try {
                /*取得URL*/
                URL myURL = new URL(UrlPath);
                /*创建连接*/
                URLConnection conn = myURL.openConnection();
                conn.connect();
                /*InputStream 下载文件*/
                InputStream input = conn.getInputStream();
                FileOutputStream write = new FileOutputStream(downloadPath);
                byte buf[] = new byte[1024];
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                Resources resources=context.getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.renren);
                Intent intent =new Intent (context,MainActivity.class);
                PendingIntent pi = PendingIntent.getActivities(context, 0, new Intent[]{intent}, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pi);
                builder.setLargeIcon(bitmap);
                builder.setSmallIcon(R.drawable.pengy);
                builder.setTicker("来自电梯宝的推荐通知");
                builder.setContentTitle("下载");
                builder.setContentText("正在下载");
                builder.setDefaults(Notification.DEFAULT_ALL);
                manager.notify(NO_4, builder.build());
                builder.setProgress(100, 0, false);
                int sumLength = conn.getContentLength();
                int sum = 0;
                int percent = 0;
                do {
                    int read = input.read(buf);
                    if (read <= 0) {
                        break;
                    }
                    sum += read;
                    // 注意强转方式，防止一直为0
                    percent = (int) (100.0 * sum / sumLength);
                    builder.setProgress(100, percent, false);
                    builder.setOnlyAlertOnce(true);
                    builder.setContentText("下载" + percent + "%");
                    //下载进度提示
                    manager.notify(NO_4, builder.build());
                    write.write(buf, 0, read);
                } while (true);
                builder.setContentTitle("下载完成");
                builder.setContentText("已完成");
                manager.notify(NO_4, builder.build());
                input.close();
                write.close();
                return 1;
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("下载日志错误", "" + e.getMessage());
                return -1;
            }
        }
    }
}
