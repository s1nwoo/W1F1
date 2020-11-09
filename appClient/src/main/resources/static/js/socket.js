//document.write("<script src='js/_define.js'></script>");
//var domain = "http://localhost:8080/order";
var domain = "http://ec2-54-180-98-41.ap-northeast-2.compute.amazonaws.com:8080/order_socket";

// websocket을 지정한 URL로 연결
var sock = new SockJS(domain+"/echo");
