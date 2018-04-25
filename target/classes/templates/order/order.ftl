<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
prepay_id:${prepay_id}<br>
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
        jsApiList: ['getBrandWCPayRequest'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    <#--function showPay(){-->
    <#--wx.chooseWXPay({-->

    <#--timestamp: ${order.timeStamp}, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符-->

    <#--nonceStr: '${order.nonceStr}', // 支付签名随机串，不长于 32 位-->

    <#--package: '${order._package}', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）-->

    <#--signType: '${order.signType}', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'-->

    <#--paySign: '${order.paySign}', // 支付签名-->

    <#--success: function (res) {-->

    <#--// 支付成功后的回调函数-->

    <#--}-->

    <#--});-->

    function showPay() {
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId": '${order.appId}',     //公众号名称，由商户传入
                    "timeStamp": ${order.timeStamp},         //时间戳，自1970年以来的秒数
                    "nonceStr": '${order.nonceStr}', //随机串
                    "package": '${order._package}',
                    "signType": '${order.signType}',         //微信签名方式：
                    "paySign": '${order.paySign}' //微信签名
                },
                function (res) {
                    alert(res.err_code + res.err_desc + res.err_msg);
                    if (res.err_msg == "get_brand_wcpay_request：ok") {
                        alert(ok);
                    }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                }
        );
    }


</script>
</html>