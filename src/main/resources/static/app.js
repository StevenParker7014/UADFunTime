var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/fun/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).message);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").prepend("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    
    $("#toggleSound").click(function(){
    	$.get("/fun/rest/sound", function(){});
    });
    
    $( "#upload" ).click(function() { 
    	var frm = $("#wallpaperForm");
    	var formData = new FormData(frm[0]);
    	$.ajax({
            url: "/fun/rest/wallpaper",
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false
        });
    });
    $( "#sendMsg" ).click(function() { 
    	var frm = $("#messageForm");
    	var formData = new FormData(frm[0]);
    	$.ajax({
            url: "/fun/rest/message",
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false
        });
    });
    
});