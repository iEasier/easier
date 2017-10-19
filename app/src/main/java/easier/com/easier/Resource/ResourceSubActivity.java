package easier.com.easier.Resource;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import easier.com.easier.R;
import easier.com.easier.TopBarActivity;
import easier.com.easier.tools.InterfaceActivity;


public class ResourceSubActivity extends TopBarActivity {
    private static JSONObject jsonResp;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                createSubViews(jsonResp);
            } else {
                Toast.makeText(ResourceSubActivity.this, "服务器错误...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_main);
        setTitle("技术库资料");
        showBackwardView(R.id.button_backward, false, true);
        showShare();
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("fileName");
        SendRequest("getFileContent",fileName);
    }


    public void SendRequest(final String interfaceName, final String requestParams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                jsonResp = new InterfaceActivity().SendRequest(interfaceName, requestParams);
                Message message = new Message();
                int isSuccess = -1;
                if ((int) jsonResp.get("retCode") == 0) {
                    isSuccess = 0;
                } else {
                    isSuccess = -1;
                }
                message.what = isSuccess;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void createSubViews(JSONObject jsonResp) {
        Log.d("接口调用值：",""+jsonResp.toString());
    }
}
