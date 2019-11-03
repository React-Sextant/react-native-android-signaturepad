# react-native-android-signaturepad
Integrate https://github.com/gcacace/android-signaturepad into `react-native/android`

# Install <a href="https://npmjs.org/package/react-native-android-signaturepad"><img alt="npm version" src="http://img.shields.io/npm/v/react-native-android-signaturepad.svg?style=flat-square"></a> <a href="https://npmjs.org/package/react-native-android-signaturepad"><img alt="npm version" src="http://img.shields.io/npm/dm/react-native-android-signaturepad.svg?style=flat-square"></a>
```bash
npm i react-native-android-signaturepad
```

# Demo
#### ONLY IN Android
```jsx harmony
import React from 'react'
import {Button,View} from 'react-native'
import SignaturePad from 'react-native-android-signaturepad'

export default class extends React.Component {
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
