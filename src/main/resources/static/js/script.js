'use strict';

document.querySelector('#welcomeForm').addEventListener('submit', connect, true);
document.querySelector('#dialogueForm').addEventListener('submit', sendMessage, true);

var stompClient = null;
var name = null;

function connect(event) {
    name = document.querySelector('#name').value.trim();

    if (name) {
        console.log('Conectando a la puja al usuario ' + name);
        document.querySelector('#welcome-page').classList.add('hidden');
        document.querySelector('#dialogue-page').classList.remove('hidden');

        var socket = new SockJS('/webSocketApp');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, connectionSuccess);
        console.log('conectado a la puja el usuario ' + name);

        // Llamar a la función para cargar mensajes guardados
        console.log('Cargando valor puja atual...');
        // loadSavedMessages();
        //loadMessages();
    }
    event.preventDefault();
}

function connectionSuccess() {
    stompClient.subscribe('/topic/bid', onMessageReceived);

    stompClient.send("/app/new_user", {}, JSON.stringify({
        userPublicId: name,
        productPublicId: "88d7eaf0-74bb-4312-b12f-7aa16834e121",
        initBidDate: "20-07-2024",
    }));
}

function sendMessage(event) {
    var messageContent = document.querySelector('#chatMessage').value.trim();

    if (messageContent && stompClient) {
        var chatMessage = {
            publicId: '7a8635f5-0759-41a6-b206-8d51759d9406',
            userPublicId: name,
            amount: document.querySelector('#chatMessage').value,
            productPublicId: '88d7eaf0-74bb-4312-b12f-7aa16834e121',
            initBidDate: '20-07-2024'
        };

        stompClient.send("/app/push_bid", {}, JSON.stringify(chatMessage));
        document.querySelector('#chatMessage').value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log(message);
    var messageElement = document.createElement('li');

    if (message.type === 'newUser') {
        messageElement.classList.add('event-data');
        message.content = message.userPublicId + ' ha ingresado al foro';
    } else if (message.type === 'Leave') {
        messageElement.classList.add('event-data');
        message.content = message.userPublicId + ' ha abandonado el foro';
    } else {
        messageElement.classList.add('message-data');

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.userPublicId);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.amount);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    document.querySelector('#messageList').appendChild(messageElement);
    document.querySelector('#messageList').scrollTop = document.querySelector('#messageList').scrollHeight;
}

async function loadMessages() {
    const response = await fetch('/foro/messages');
    const messages = await response.json();

    console.log(messages);

    messages.forEach(message => {
        var messageElement = document.createElement('li');

        messageElement.classList.add('message-data');

        var element = document.createElement('i');
        var text = document.createTextNode(message.sender[0]);
        element.appendChild(text);

        messageElement.appendChild(element);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        document.querySelector('#messageList').appendChild(messageElement);
    });
    document.querySelector('#messageList').scrollTop = document.querySelector('#messageList').scrollHeight;
}

function loadSavedMessages() {
    fetch('/foro/messages')
        .then(response => {
            console.log(response.json());
            response.json();
        })
        .then(messages => {
            messages.forEach(message => {
                var messageElement = document.createElement('li');

                messageElement.classList.add('message-data');

                var element = document.createElement('i');
                var text = document.createTextNode(message.sender[0]);
                element.appendChild(text);

                messageElement.appendChild(element);

                var usernameElement = document.createElement('span');
                var usernameText = document.createTextNode(message.sender);
                usernameElement.appendChild(usernameText);
                messageElement.appendChild(usernameElement);

                var textElement = document.createElement('p');
                var messageText = document.createTextNode(message.content);
                textElement.appendChild(messageText);

                messageElement.appendChild(textElement);

                document.querySelector('#messageList').appendChild(messageElement);
            });
            document.querySelector('#messageList').scrollTop = document.querySelector('#messageList').scrollHeight;
        });
}
