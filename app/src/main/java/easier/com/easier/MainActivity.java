package easier.com.easier;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.Date;


public class MainActivity extends TopBarActivity {
    private long mExitTime = new Date().getTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("电梯");
        showShare();
        // 首页六个按钮
        Button btnOne = (Button) findViewById(R.id.btnOne);
        Button btnTwo = (Button) findViewById(R.id.btnTwo);
        Button btnThree = (Button) findViewById(R.id.btnThree);
        Button btnFour = (Button) findViewById(R.id.btnFour);
        Button btnFive = (Button) findViewById(R.id.btnFive);
        Button btnSix = (Button) findViewById(R.id.btnSix);

        btnOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("btnOne","btnOne点击事件");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ResourceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnTwo", "btnTwo点击事件");
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnThree", "btnThree点击事件");
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnFour", "btnFour点击事件");
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnFive", "btnFive点击事件");
            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btnSix", "btnSix点击事件");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        // Log.i("KeyEvent.keyCode",""+keyCode);
        if(keyCode == KeyEvent.KEYCODE_BACK){
            MainActivity.this.finish();
            System.exit(0);
            Log.i("進入","if");
            return true;
        }
        Log.i("進入","or");
        return false;
    }
}
