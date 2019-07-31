$(document).ready(function (){

	connectionToggle();
	//延时订阅
	setTimeout('subscribe()',3000);

});
// 创建客户端实例
var client = null;
var connected = false;

//当客户端连接时调用
function onConnect(context) {
  var connectionString = context.invocationContext.host + ":" + context.invocationContext.port + context.invocationContext.path;
  logMessage("INFO", "Connection Success ", "[URI: ", connectionString, ", ID: ", context.invocationContext.clientId, "]");
  connected = true;
}

function onConnected(reconnect, uri) {
  logMessage("INFO", "Client Has now connected: [Reconnected: ", reconnect, ", URI: ", uri, "]");
  connected = true;
}

function onFail(context) {
  logMessage("ERROR", "Failed to connect. [Error Message: ", context.errorMessage, "]");
  connected = false;
}

// 当客户端失去连接时调用
function onConnectionLost(responseObject) {
  if (responseObject.errorCode !== 0) {
    logMessage("INFO", "Connection Lost. [Error Message: ", responseObject.errorMessage, "]");
  }
  connected = false;
}

// =======================当消息到达时处理方法=================================================
function onMessageArrived(message) {
  logMessage("INFO", "Message Recieved: [Topic: ", message.destinationName, ", Payload: ", message.payloadString, ", QoS: ", message.qos, ", Retained: ", message.retained, ", Duplicate: ", message.duplicate, "]");
  var jsonMessage = JSON.parse(message.payloadString);
  toastr.options = {
			"closeButton": false,
			"debug": false,
			"positionClass": "toast-bottom-left",
			"onclick": null,
			"showDuration": "300",
			"hideDuration": "1000",
			"timeOut": "10000",//展现时间
			"extendedTimeOut": "1000",
			"showEasing": "swing",
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
		}	
       toastr.info(jsonMessage.content)

}

function connectionToggle() {
  if (connected) {
	  disconnect();
  	} else {
      connect();
  	}

}

function connect() {
  var hostname = '10.20.23.24';
  var port = 61623;

  var path = '/ws';
  var clientId = 'ktv-'+makeid();
  var keepAlive = 60;
  var timeout = 3;
  var tls = false;
  var automaticReconnect = true;
  var cleanSession = true;

  client = new Paho.Client(hostname, port, path, clientId);
  logMessage("INFO", "Connecting to Server: [Host: ", hostname, ", Port: ", port, ", Path: ", client.path, ", ID: ", clientId, "]");

  // 设置回调处理程序
  client.onConnectionLost = onConnectionLost;
  client.onMessageArrived = onMessageArrived;
  client.onConnected = onConnected;

  var options = {
    invocationContext: { host: hostname, port: port, path: client.path, clientId: clientId },
    timeout: timeout,
    keepAliveInterval: keepAlive,
    cleanSession: cleanSession,
    useSSL: tls,
    reconnect: automaticReconnect,
    onSuccess: onConnect,
    onFailure: onFail
  };

  var user = mqttUsername;
  var pass = mqttPassword;

  if (user.length > 0) {
    options.userName = user;
  }

  if (pass.length > 0) {
    options.password = pass;
  }

  // 连接客户端
  client.connect(options);
  
}

function disconnect() {
  logMessage("INFO", "Disconnecting from Server.");
  client.disconnect();
  var statusSpan = document.getElementById("connectionStatus");
  statusSpan.innerHTML = "Connection - Disconnected.";
  connected = false;
}

//订阅消息
function subscribe() {
  var topic = 'message';
  var qos = '0';
  logMessage("INFO", "Subscribing to: [Topic: ", topic, ", QoS: ", qos, "]");
  client.subscribe(topic, { qos: Number(qos) });
}


//生成clientId
function makeid() {
  var text = "";
  var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  for (var i = 0; i < 5; i++)
    text += possible.charAt(Math.floor(Math.random() * possible.length));

  return text;
}

function logMessage(type, ...content) {
  var date = new Date();
  var timeString = date.toUTCString();
  var logMessage = timeString + " - " + type + " - " + content.join("");
  if (type === "INFO") {
    console.info(logMessage);
  } else if (type === "ERROR") {
    console.error(logMessage);
  } else {
    console.log(logMessage);
  }
}

