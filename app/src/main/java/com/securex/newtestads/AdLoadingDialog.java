package com.securex.newtestads;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.securex.newtestads.R;

//import livevideocall.randomchat.call.chat.newcall.newgirlvideocall.R;


public class AdLoadingDialog extends Dialog {
    public AdLoadingDialog(@NonNull Context context) {
        super(context);
//        context.setTheme(R.style.AlertTheme);
        initView();
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.ad_dilog_loader,null));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        show();
    }

    public AdLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AdLoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }



}
