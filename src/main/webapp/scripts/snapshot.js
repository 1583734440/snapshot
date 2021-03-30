
function book() {

    var goodsId = $('#goodsId').text();
    var shopId = $('#shopId').text();
    var userId = $('#userId').text();
    var goodsTitle = $('#goodsTitle').text();
    var goodsDesc = $('#goodsDesc').text();
    var goodsPrice = $('#goodsPriceNum').text();
    var goodsPriceNum = parseInt(goodsPrice);

    var deliveryType = $('input[name="deliveryType"]:checked').val();
    var goodsServiceKeys = $('#goodsServiceKeys').text();

    //TODO goodsServiceKeys express 的值需要根据 deliveryType radio 的值进行替换

    var payWay = $('input[name="payWay"]:checked').val();

    var param = {
        'order' :{
            'shopId' :shopId,
            'userId': userId,
            'deliveryType': deliveryType,
            'payWay': payWay
        },

        'goods': {
            'shopId': shopId,
            'goodsId' : goodsId,
            'price': goodsPriceNum,
            'title': goodsTitle,
            'desc' : goodsDesc,
            'keys' : goodsServiceKeys
        }

    }

    var request = {
        url: 'http://localhost:8080/goodsnapshot/book',
        data: JSON.stringify(param),
        dataType: 'json',
        contentType: 'application/json ; charset=utf-8',
        type: 'POST',
        timeout: 5000
    }

    function succTodo(resp) {

        $('#BookArea').css('display', 'none');
        $('#bookSuccessArea').css('display', 'block');
        $('#orderNoResult').text(resp.data.orderNo);

        $('#lookSnapshotBtn').click(function(event) {

           var bookGoodsId = resp.data.goodsId;
           var bookOrderNo = resp.data.orderNo;

           var detailParam = {
               'goodsId': bookGoodsId,
               'orderNo': bookOrderNo
           }

           var detailRequest = {
               url: 'http://localhost:8080/goodsnapshot/detail',
               data: JSON.stringify(detailParam),
               dataType: 'json',
               contentType: 'application/json ; charset=utf-8',
               type: 'POST',
               timeout: 5000
           }

           var detailSuccTodo = function(resp) {

               $('#lookSnapshotArea').empty();

               var snapshotDivs = '';
               var snapshotData = resp.data.goodsServiceSnapshots;
               for (i=0; i < snapshotData.length; i++) {
                   snapshotDivs += '<div class="snapshot"><div>' + snapshotData[i].title + '</div><div>' + snapshotData[i].desc + '</div></div>';
               }

               $('#lookSnapshotArea').append('<div>商品价格：<div id="goodsPriceResult" class="inline lh">' + resp.data.price + '</div></div>');
               $('#lookSnapshotArea').append('<div>商品标题：<div id="goodsTitleResult" class="inline lh">' + resp.data.goodsTitle + '</div></div>');
               $('#lookSnapshotArea').append('<div>商品描述：<div id="goodsDescResult" class="inline lh">' + resp.data.goodsDesc + '</div></div>');
               $('#lookSnapshotArea').append('<div id="goodsServiceResult">商品服务：<div id="goodsSnapshotResult" class="lh">' + snapshotDivs + '</div></div>');

           }

           var detailFailedTodo = function(resp) {
               alert(JSON.stringify(resp));
           }

           var detailResponse = jQuery.ajax(detailRequest);
           detailResponse.done(detailSuccTodo);
           detailResponse.fail(detailFailedTodo);
        });

        $('#cancelBtn').click(function(event) {
            $('#orderNoResult').text();
            $('#lookSnapshotArea').empty();
            $('#bookSuccessArea').css('display', 'none');
            $('#lookSnapshotBtn').unbind('click');
            $('#BookArea').css('display', 'block');

        });


    }

    function failedTodo(resp) {
        alert(JSON.stringify(resp));
    }

    var req = jQuery.ajax(request);
    req.done(succTodo);
    req.fail(failedTodo);

}
