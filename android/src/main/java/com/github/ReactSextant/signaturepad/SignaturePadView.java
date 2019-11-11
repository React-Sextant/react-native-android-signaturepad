package com.github.ReactSextant.signaturepad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.gcacace.signaturepad.views.SignaturePad;

public class SignaturePadView extends SignaturePad{

    private boolean isTouchMove = false;    //优化单击事件不触发onSigned()

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled())
            return false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                super.onTouchEvent(event);
                break;

            case MotionEvent.ACTION_MOVE:
                super.onTouchEvent(event);
                isTouchMove = true;
                break;

            case MotionEvent.ACTION_UP:
                if(isTouchMove){
                    super.onTouchEvent(event);
                    isTouchMove = false;
                    break;
                }else {
                    return false;
                }
            default:
                return false;
        }

        return true;
    }

    public SignaturePadView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {
                //Event triggered when the pad is touched
                WritableMap event = Arguments.createMap();
                event.putString("message", "onStartSigning");
                ReactContext reactContext = (ReactContext)getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        getId(),
                        "topChange",
                        event);
            }

            @Override
            public void onSigned() {
                //Event triggered when the pad is signed
                WritableMap event = Arguments.createMap();
                event.putString("message", "onSigned");
                ReactContext reactContext = (ReactContext)getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        getId(),
                        "topChange",
                        event);
            }

            @Override
            public void onClear() {
                //Event triggered when the pad is cleared
                WritableMap event = Arguments.createMap();
                event.putString("message", "onClear");
                ReactContext reactContext = (ReactContext)getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        getId(),
                        "topChange",
                        event);
            }
        });

    }
}
