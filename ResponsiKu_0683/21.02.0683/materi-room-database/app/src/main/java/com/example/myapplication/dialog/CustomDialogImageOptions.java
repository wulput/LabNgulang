package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.util.DialogImageOptionsListener;

public class CustomDialogImageOptions extends Dialog {
    private final DialogImageOptionsListener listener;

    public CustomDialogImageOptions(@NonNull Context context, DialogImageOptionsListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image_options);

        findViewById(R.id.open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCameraClick();
                dismiss();
            }
        });

        findViewById(R.id.open_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onGalleryClick();
                dismiss();
            }
        });
    }
}
