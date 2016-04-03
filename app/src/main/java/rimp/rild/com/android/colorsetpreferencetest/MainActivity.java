package rimp.rild.com.android.colorsetpreferencetest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Preference で値のやり取りをする


    private final int STATE_BLACK = 0;
    private final int STATE_BLUE = 1;
    private final int STATE_RED = 2;

    int mColorState;

    TextView mTextView;
    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadColor();

        mTextView = (TextView) findViewById(R.id.main_text);
        mButton = (Button) findViewById(R.id.intent_button);

        updateTextViewBackgroundColor(mColorState);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSetting();
            }
        });

    }

    private void intentSetting() {
        //画面遷移する前に、今の情報を保存する
        saveColor();

        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
        startActivity(intent);
    }

    private void saveColor() {
        //色の情報をintentから受け取る
        SharedPreferences preferences = getSharedPreferences("color_info", Context.MODE_PRIVATE); //color_info はファイル名
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("text_color", mColorState);
        editor.apply();
    }

    private void loadColor() {
        //色の情報をpreferenceから読み込む
        SharedPreferences prefs = getSharedPreferences("color_info", Context.MODE_PRIVATE);
        mColorState = prefs.getInt("text_color", STATE_BLACK);
    }

    private void updateTextViewBackgroundColor(int color) {
        Resources res = getResources();
        int colorRes = res.getColor(R.color.bg_black);

        if (color == STATE_BLACK) {
            colorRes = res.getColor(R.color.bg_black);
        } else if (color == STATE_BLUE) {
            colorRes = res.getColor(R.color.bg_blue);
        } else if (color == STATE_RED) {
            colorRes = res.getColor(R.color.bg_red);
        }

        mTextView.setTextColor(getResources().getColor(R.color.bg_black));

        mTextView.setTextColor(colorRes);
    }
}
