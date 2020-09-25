package com.github.ReactSextant.signaturepad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;

import java.io.ByteArrayOutputStream;

public class SignatureModule extends ReactContextBaseJavaModule {
    @Override
    public String getName() {
        return "SignatureModule";
    }

    public SignatureModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void clear(final int viewTag) {
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                final SignaturePadView signaturePad;

                try {
                    signaturePad = (SignaturePadView) nativeViewHierarchyManager.resolveView(viewTag);
                    Paint2.clear();
                    signaturePad.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void getSignatureBitmap(final int viewTag, final Promise promise){

        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                final SignaturePadView signaturePad;

                try {
                    signaturePad = (SignaturePadView) nativeViewHierarchyManager.resolveView(viewTag);
                    promise.resolve(bitmapToString(signaturePad.getSignatureBitmap()));
                } catch (Exception e) {
                    e.printStackTrace();
                    promise.reject(e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void setSignatureBitmap(final int viewTag,String string){
        final Bitmap bitmap = stringToBitmap(string);

        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                final SignaturePadView signaturePad;

                try {
                    signaturePad = (SignaturePadView) nativeViewHierarchyManager.resolveView(viewTag);
                    signaturePad.setSignatureBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Base64 String to Bitmap
     * **/
    Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    String bitmapToString(Bitmap bitmap) {
        if(bitmap == null) return "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imgBytes = baos.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    @ReactMethod
    public void getTransparentSignatureBitmap(final int viewTag, final Promise promise){

        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                final SignaturePadView signaturePad;

                try {
                    signaturePad = (SignaturePadView) nativeViewHierarchyManager.resolveView(viewTag);
                    promise.resolve(bitmapToString(signaturePad.getTransparentSignatureBitmap()));
                } catch (Exception e) {
                    e.printStackTrace();
                    promise.reject(e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void getSignatureSvg(final int viewTag, final Promise promise){

        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                final SignaturePadView signaturePad;

                try {
                    signaturePad = (SignaturePadView) nativeViewHierarchyManager.resolveView(viewTag);
                    promise.resolve(signaturePad.getSignatureSvg());
                } catch (Exception e) {
                    e.printStackTrace();
                    promise.reject(e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void undo(final int viewTag, final Callback callback){
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                final SignaturePadView signaturePad;

                try {
                    signaturePad = (SignaturePadView) nativeViewHierarchyManager.resolveView(viewTag);
                    signaturePad.undo(callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
