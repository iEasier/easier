package easier.com.easier;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ResourceActivity extends TopBarActivity {
    private Button button_backward;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_main);
        setTitle("技术库资料");
        showBackwardView(R.id.button_backward,false,true);
        showShare();
        Button button_backward = findViewById(R.id.button_backward);
        button_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ResourceActivity.this,MainActivity.class);
                ResourceActivity.this.finish();
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Log.i("進入","Resources If");
            ResourceActivity.this.finish();
            return true;
        }
        Log.i("進入","Resources Or");
        return false;
    }
}
