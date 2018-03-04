function deleteQueue(queueCode) {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var url = '/queue/' + queueCode;

    console.log(token, header);

    $.ajax({
        url: url,
        type: 'DELETE',
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function (result) {
            window.location.reload(true);
        }
    });
}

function claimQueueItem(username, itemId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var url = '/queueitem/' + itemId;

    $.ajax({
        url: url,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(data) {
            console.log(data);
        }
    });



}

function completeQueueItem(itemId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
}

function deleteQueueItem(itemId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
}