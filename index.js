/* 测试用 */

import React from 'react'
import {requireNativeComponent,NativeModules,findNodeHandle,processColor} from 'react-native'
import normalizeColor from 'normalize-css-color';

const SignatureView = requireNativeComponent('SignaturePad', SignaturePad, {
    nativeOnly: {onChange: true},
});
const SignatureModule = NativeModules.SignatureModule;

export default class SignaturePad extends React.PureComponent {
    static defaultProps: Object = {
        _signaturePadHandle: Number,
        undo:true,
        penColor: "#000000"
    };

    _setReference = (ref: ?Object) => {
        if (ref) {
            this._signaturePadHandle = findNodeHandle(ref);
        } else {
            this._signaturePadHandle = null;
        }
    };

    clear=()=>{
        SignatureModule.clear(this._signaturePadHandle)
    };

    getSignatureBitmap=async ()=>{
        return await SignatureModule.getSignatureBitmap(this._signaturePadHandle)
    };

    setSignatureBitmap=(bitmap)=>{
        SignatureModule.setSignatureBitmap(this._signaturePadHandle, bitmap)
    };

    getTransparentSignatureBitmap=async ()=>{
        return await SignatureModule.getTransparentSignatureBitmap(this._signaturePadHandle)
    };

    getSignatureSvg=async ()=>{
        return await SignatureModule.getSignatureSvg(this._signaturePadHandle)
    };

    undo=(callback=Function)=>{
        SignatureModule.undo(this._signaturePadHandle,callback)
    };

    render(){
        return (
            <SignatureView
                ref={this._setReference}
                {...this.props}
                penColor={processColor(normalizeColor(this.props.penColor))}
            />
        )
    }
}
