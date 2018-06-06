package com.god.piptest;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_button = (Button)findViewById(R.id.btn_button);

        // Oreo에서만 동작가능한 PIP모드라서 분기처리를 해준다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            btn_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        enterPictureInPictureMode();
                    }

                }
            });
            btn_button.setVisibility(View.VISIBLE);
        } else {
            btn_button.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);

        if (isInPictureInPictureMode) {
            // Hide the controls in picture-in-picture mode.
            Log.i("TEST","isInPictureInPictureMode");
        } else {
            // Restore the playback UI based on the playback status.

            Log.i("TEST","isInPictureInPictureMode false");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // PIP에서 동영상 재생 계속
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && isInPictureInPictureMode()) {
            // Continue playback
        }
    }
}
