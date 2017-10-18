package easier.com.easier.tools;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InterfaceActivity {
    private String strURL = "http://172.16.1.188:8080/ieasier/Test";

    /**
     * 程序中访问http数据接口
     */

    public static String getURLContent(String urlStr) {
        /** 网络的url地址 */
        URL url = null;
        /** http连接 */
        HttpURLConnection httpConn = null;
            /**//** 输入流 */
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        String result = sb.toString();
        try {
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "GBK"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException ex) {
            Log.d("调取接口异常", "调取接口异常:" + ex.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                Log.i("调取接口异常：", e.getMessage());
            }
        }
        return result;
    }

    public void SendRequest() {
        String result = getURLContent(strURL);
        Log.d("调取接口", "result:" + result);
    }
}
