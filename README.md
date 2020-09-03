# react-native-android-signaturepad
Integrate https://github.com/gcacace/android-signaturepad into `react-native/android`

# Install <a href="https://npmjs.org/package/react-native-android-signaturepad"><img alt="npm version" src="http://img.shields.io/npm/v/react-native-android-signaturepad.svg?style=flat-square"></a> <a href="https://npmjs.org/package/react-native-android-signaturepad"><img alt="npm version" src="http://img.shields.io/npm/dm/react-native-android-signaturepad.svg?style=flat-square"></a>
```bash
npm i react-native-android-signaturepad
```

# Changelog

 - **1.0.4**
 
   1.添加 `erasing={boolean}`橡皮擦功能
   
   2.添加`initBitmap={string}`初始化时会渲染传入的bitmap

# Demo
#### ONLY IN Android
```jsx harmony
import React from 'react'
import {Button,View} from 'react-native'
import SignaturePad from 'react-native-android-signaturepad'

export default class extends React.Component {
    state={
        erasing:false
    };

    clear=()=>{
        this.refs._signaturePad.clear();
    };

    render(){
        return (
            <View style={{flex:1}}>
                <SignaturePad
                    ref={"_signaturePad"}
                    style={{flex:1,backgroundColor:'yellow'}}
                    penMaxWidth={7}
                    penMinWidth={3}
                    erasing={this.state.erasing}
                    onChange={(event)=>{
                        let message = event.nativeEvent.message;
                        console.log(message)
                    }}
                />
                <Button title={"clear"} onPress={this.clear}/>
            </View>
        )
    }
}
```

# Usage
https://github.com/gcacace/android-signaturepad

# 其他
使用`<Image />`渲染bitmap记得添加前缀`'data:image/png;base64,'`以作为`base64`格式数据
```jsx harmony

this.setState({bitmap:await this.refs._signaturePad.getTransparentSignatureBitmap()})

<Image source={{uri:'data:image/png;base64,'+bitmap}}
```

