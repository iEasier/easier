package easier.com.easier.tools;

import android.support.annotation.AnyRes;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InterfaceActivity {
    private static String strURL = "http://192.168.3.28:8080/ieasier/";


    public static void SendRequest(String interfaceName) {
        JSONObject jsonObject = new JSONObject();
        String interfaceURL = strURL + interfaceName;
        String result = getURLContent(interfaceURL);
        Log.d("调取接口成功：", result);
    }


    /**
     * 程序中访问http数据接口
     */

    private static String getURLContent(String urlStr) {
        /** 网络的url地址 */
        URL url = null;
        /** http连接 */
        HttpURLConnection httpConn = null;
        /** 输入流 */
        BufferedReader in = null;
        StringBuffer stringBuffer = new StringBuffer();
        String result = null;
        try {
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                stringBuffer.append(str);
            }
            result = stringBuffer.toString();
        } catch (IOException ex) {
            Log.d("调取接口异常", ex.getMessage());
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
}
