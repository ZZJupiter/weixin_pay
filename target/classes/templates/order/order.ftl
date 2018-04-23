<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
orderResult:${orderResult}<br>
order:${order}
<button style="width: 200px;height: 40px" onclick="showPay()">chooseWXPay</button>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${wxJsConfigVO.appId}', // 必填，企业号的唯一标识，此处填写企业号corpid
        timestamp:${wxJsConfigVO.timestamp} , // 必填，生成签名的时间戳
        nonceStr: '${wxJsConfigVO.nonceStr}', // 必填，生成签名的随机串
        signature: '${wxJsConfigVO.signature}',// 必填，签名，见附录1
        jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    function showPay(){
        wx.chooseWXPay({

            timestamp: ${order.timeStamp}, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符

            nonceStr: '${order.nonceStr}', // 支付签名随机串，不长于 32 位

            package: '${order._package}', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）

            signType: '${order.signType}', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'

            paySign: '${order.paySign}', // 支付签名

            success: function (res) {

                // 支付成功后的回调函数

            }

        });
    }


</script>
</html>