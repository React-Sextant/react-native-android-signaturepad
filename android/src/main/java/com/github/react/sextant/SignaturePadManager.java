package com.github.react.sextant;

import android.content.Context;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class SignaturePadManager extends SimpleViewManager<SignaturePadView> {
    private static final String REACT_CLASS = "SignaturePad";
    private Context context;
    private SignaturePadView signaturePad;


    public SignaturePadManager(ReactApplicationContext reactContext){
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public SignaturePadView createViewInstance(ThemedReactContext context) {
        this.signaturePad = new SignaturePadView(context, null);
        return signaturePad;
    }

    @Override
    public void onDropViewInstance(SignaturePadView signaturePad) {
        signaturePad = null;
    }

    @ReactProp(name = "penMaxWidth")
    public void setMaxWidth(SignaturePadView signaturePad, int maxWidth) {
        signaturePad.setMaxWidth(maxWidth);
    }

    @ReactProp(name = "penMinWidth")
    public void setMinWidth(SignaturePadView signaturePad, int minWidth) {
        signaturePad.setMinWidth(minWidth);
    }
}