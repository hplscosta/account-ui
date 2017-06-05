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
    $("#accounts").html("");
}

function connect() {
    var socket = new SockJS('/account-ws-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/accounts', function (account) {
            var account = JSON.parse(account.body);
            var age = account.age;
            if(age==null) {
                age = "-";
            }
            var city;
            var country
            if(account.address==null) {
                city = "-";
                country = "-";
            } else {
                city=account.address.city;
                if(city==null) {
                    city = "-";
                }
                country=account.address.country;
                if(country==null) {
                    country = "-";
                }
            }
            showAccount(account.user, account.name, age, city, country);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function showAccount(user, name, age, city, country) {
    $("#accounts").append("<tr><td>" + user + "</td><td>" + name + "</td><td>" + age + "</td><td>" + city + "</td><td>" + country + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});

