import React from 'react'
import {
    View,
    TouchableOpacity,
    Text,
    Slider,
    StyleSheet,
} from 'react-native'
import SignaturePad from "react-native-android-signaturepad";

export default class RNAndroidSignaturePad extends React.Component {
    constructor(props){
        super(props);
        this.state={
            erasing:false,
            undo:false,
            penWidth:3,
        }
    }

    handleSliderValueChange=(value)=>{
        this.setState({penWidth:Number(value.toFixed(0))})
    };

    handleSignaturePadChange=(event)=>{
        let message = event.nativeEvent.message;
        switch (message) {
            case "onSigned":
                this.setState({undo:true});
                break;
            case "onClear":
                this.setState({undo:false});
                break;
        }
    };

    handleErasing=()=>{
        this.setState(function(preState){
            return {erasing:!preState.erasing}
        })
    };

    handleUndo=()=>{
        this.refs._signaturePad&&this.refs._signaturePad.undo((status)=>{
            if(status !== "onUndo"){
                this.setState({undo:false})
            }
        })
    };

    handleClear=()=>{
        this.refs._signaturePad&&this.refs._signaturePad.clear()
    };


    render(){
        const {erasing,undo,penWidth} = this.state;
        return (
            <View style={{flex:1}}>
                <SignaturePad
                    ref={"_signaturePad"}
                    style={{width:"100%",height:"100%",backgroundColor:'#E5E5E5'}}
                    penMaxWidth={penWidth+6}
                    penMinWidth={penWidth}
                    onChange={this.handleSignaturePadChange}
                    undo
                    erasing={erasing}
                />
                <View style={{width:'100%',top:0,position:'absolute',flexDirection:"row"}}>
                    <View style={{flex:1,flexDirection:'row'}}>
                        <TouchableOpacity onPress={this.handleErasing} style={[styles.signBtn,erasing&&{backgroundColor:"#A2CCE3"}]}>
                            <Text style={{color:'#FFFFFF',paddingHorizontal:10,paddingVertical:5}}>{erasing&&"取消"}橡皮擦</Text>
                        </TouchableOpacity>
                        <TouchableOpacity disabled style={[styles.signBtn]}>
                            <Text style={{color:'#FFFFFF',paddingHorizontal:10,paddingVertical:5}}>粗细{this.state.penWidth}</Text>
                        </TouchableOpacity>
                        <Slider style={{width:150,marginTop:5,}}
                                value={penWidth}
                                maximumValue={15}
                                minimumValue={1}
                                onValueChange={this.handleSliderValueChange}
                        />
                    </View>
                    <View style={{flexDirection:'row'}}>
                        {undo&&<TouchableOpacity onPress={this.handleUndo} style={styles.signBtn}>
                            <Text style={styles.signText}>回退上一步</Text>
                        </TouchableOpacity>}
                        <TouchableOpacity onPress={this.handleClear} style={styles.signBtn}>
                            <Text style={styles.signText}>清空</Text>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        )
    }
}

const styles = StyleSheet.create({
    signBtn:{
        backgroundColor:"#1C8CE3",
        alignSelf:'flex-start',
        justifyContent:'center',
        margin:5,
        borderRadius:5
    },
    signText:{
        color:'#FFFFFF',
        paddingHorizontal:10,
        paddingVertical:5
    },
});
