/* 测试用 */

import React from 'react'
import {requireNativeComponent,NativeModules,findNodeHandle,processColor} from 'react-native'
import normalizeColor from 'normalize-css-color';

const SignatureView = requireNativeComponent('SignaturePad', SignaturePad, {
    nativeOnly: {onChange: true},
});
const SignatureModule = NativeModules.SignatureModule;

export default class SignaturePad extends React.PureComponent {
    static defaultProps = {
        _signaturePadHandle: Number,
        undo:true,
        penColor: "#000000",
        bitmap:undefined
    };

    constructor(props){
        super(props);
        this.state={
            isLayout:false
        };
    }

    componentWillReceiveProps(nextProps){
        if(nextProps.bitmap !== this.props.bitmap && this.state.isLayout){
            this.setSignatureBitmap(nextProps.bitmap)
        }
    }

    onLayout=()=>{
        if(!this.state.isLayout){
            this.setState({isLayout:true});
            this.setSignatureBitmap(this.props.bitmap)
        }
    };

    _setReference = (ref) => {
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
        if(typeof bitmap === "string"){
            SignatureModule.setSignatureBitmap(this._signaturePadHandle, bitmap)
        }
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
                onLayout={this.onLayout}
                penColor={processColor(normalizeColor(this.props.penColor))}
            />
        )
    }
}
