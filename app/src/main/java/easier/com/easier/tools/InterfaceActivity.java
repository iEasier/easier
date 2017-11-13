package easier.com.easier.tools;

import android.util.Log;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InterfaceActivity {

    private static String strURL = "http://mylove.vicp.net/ieasier/";
    private static String ENCODING_UTF_8 = "UTF-8";

    /**
     * 程序中访问http数据接口
     * 访问模式为GET请求模式
     */
    public static JSONObject SendRequest(String interfaceName) {
        JSONObject jsonResp = new JSONObject();
        String interfaceURL = strURL + interfaceName;
        String result = getURLContent(interfaceURL);
        try {
            jsonResp = JSONObject.fromObject(result);
        } catch (Exception e) {
            jsonResp.put("retCode", -1);
            Log.e("接口调用失败：", e.getMessage());
        }
        Log.i("接口调用结果", jsonResp.toString());
        return jsonResp;
    }

    /**
     * 程序中访问http数据接口
     * 访问模式为POST方式
     */
    public static JSONObject SendRequest(String interfaceName, String requestProperty) {
        JSONObject jsonResp = new JSONObject();
        String interfaceURL = strURL + interfaceName;
        String result = getURLContent(interfaceURL, requestProperty);
        try {
            jsonResp = JSONObject.fromObject(result);
        } catch (Exception e) {
            jsonResp.put("retCode", -1);
            Log.e("接口调用失败：", e.getMessage());
        }
        Log.d("接口调用结果", jsonResp.toString());
        return jsonResp;
    }

    /**
     * 程序中访问http数据接口
     * 访问模式为POST请求模式
     */

    private static String getURLContent(String urlStr, String requestProperty) {
        /** 网络的url地址 */
        URL url = null;
        /** http连接 */
        HttpURLConnection httpConn = null;
        /** 输入流 */
        BufferedReader responseReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String result = null;
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(false);// 忽略缓存
            httpConn.setRequestMethod("POST");// 设置URL请求方法
            byte[] requestStringBytes = requestProperty.getBytes(ENCODING_UTF_8);
            httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
            httpConn.setRequestProperty("Content-Type", "application/octet-stream");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");

            // 建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(requestStringBytes);
            outputStream.close();

            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), ENCODING_UTF_8));
                String str = null;
                while ((str = responseReader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                result = stringBuffer.toString();
            } else {
                Log.d("调取接口返回码", "" + responseCode);
            }
        } catch (IOException ex) {
            result = "{'retCode':-1}";
            Log.d("调取接口异常 IOException", ex.getMessage());
        } finally {
            try {
                if (responseReader != null) {
                    responseReader.close();
                }
            } catch (Exception e) {
                Log.i("调取接口异常", e.getMessage());
            }
        }
        return result;
    }

    /**
     * 程序中访问http数据接口
     * 访问模式为GET方式
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
        } catch (Exception ex) {
            result = "{'retCode':-1}";
            Log.e("调取接口异常","");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                Log.e("调取接口异常", e.getMessage());
            }
        }
        return result;
    }
}
