function ajax(method,url,data,callback,json){
	//1.创建XMLHttpRequest对象
	console.log("进入ajax处理！")
	var xmlhttp = null;
	if(window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	}else{
		// code for IE6, IE5
		xmlhttp = new ActiveXObject();
	}
	
	//2.创建回调函数，在回调函数中处理返回值
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			//获取服务器的响应，获得字符串形式的响应数据，也可以获取responseXml形式的数据
			console.log("正在处理数据！")
			var result = xmlhttp.responseText;
			if(json !=undefined && json.toUpperCase() == "JSON"){
				var objJson = null;
				//如果是ie
				if(window.ActiveXObject){
					objJson = eval('('+ result +')')
				}else{
					objJson = JSON.parse(result);
				}
				callback(objJson);
			}else{
				callback(result);
			}
		}else{
			console.log('error!')
		}
	}
	
	//3.发送请求（判断是get还是post请求）
	if(method.toUpperCase() == "GET"){
		//为了避免缓存的结果情况，请向 URL 添加一个唯一的 ID：
		//xmlhttp.open("GET",url+"t=" + Math.random(),true);
		
		//希望通过 GET 方法发送信息，请向 URL 添加信息：
		//xmlhttp.open("GET","demo_get2.asp?fname=Bill&lname=Gates",true);
		xmlhttp.open("GET",url,true);

		xmlhttp.send();
	}else{
		//如果有 HTML 表单那样 POST 数据，
		//请使用 setRequestHeader() 来添加 HTTP 头。
		//然后在 send() 方法中规定您希望发送的数据：
		xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send(data);
	}
	
}
/**
 * 登录
 * 检查用户名不能为空
 */
function checkUserName() {
	var username = document.getElementById("username").value;
	var uname = document.getElementById("uname");
	
	var password = document.getElementById("password").value;
	var pwd = document.getElementById("pwd");
	
	if(username == null || username == "") {
		//alert(用户名不能为空);
		uname.innerHTML = "<font color='red'>用户名不能为空</font>";
		return false;
	}
	if(password == null || password == "") {
		pwd.innerHTML = "<font color='red'>密码不能为空</font>";
		return false;
	}
	return true;
}

/**
 * 注册
 * 用户名是否合法
 * @returns {Boolean}
 */
function isUsernameLegal() {
	/* 用户名是否填写 */
	var username = document.getElementById("username").value;
	var usernull = document.getElementById("usernull");
	if(username == null || username == "") {
		usernull.innerHTML = "<font color='red'>用户名不能为空!</font>";
		return false;
	}
	
	/* 用户名是否已存在 alreadyExsits*/
	isExists();
	
	usernull.innerHTML = "";	//输入合法后消除提示
	return true;
}

/**
 * Ajax检查用户是否已经被注册
 * 异步：发送请求时不等返回结果，由回调函数处理结果
 * @returns {Boolean}
 */
function isExists() {
	var username = document.getElementById("username").value;	//获取用户名
	ajax("post","RegisterServlet?action=check&username="+username,"",function(data){
		if(data == "true"){
			usernull.innerHTML = "<font color='red'>当前用户名已被注册！</font>";
			return false;
		} else if(data == "false") {
			usernull.innerHTML = "<font color='green'>当前用户名可用！</font>";
			return true;
		}
		
	})
}
/*function isExists() {
	var xmlHttp;	//定义 xmlHttp  XmlHttpRequest对象从服务器异步获取数据后通过javascript修改页面局部信息
	try {
		//根据不同浏览器初始化不同的xmlHttp
		//IE6以上浏览器
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			//FireFox
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				//IE5.5+
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持Ajax");
				return false;
			}
		}
	}
	var username = document.getElementById("username").value;	//获取用户名
	*//**
	 * open(提交方式[get|post]，url（servlet路径），同步或异步[false|true])
	 * RegisterServlet： servlet路径
	 * 打开和后台服务器的链接
	 *//*
	xmlHttp.open("POST", "RegisterServlet?action=check&username="+username, true);	//路径中不能有空格
	xmlHttp.send(null);	//传送数据
	*//**
	 * onreadystatechange:调用回调函数
	 * readyState：请求状态，代码是未初始化，1：初始化，2：发送请求，3：开始接受数据，4：接受结果完毕
	 * status: http状态码 200成功，404路径错误，500后台代码错误,
	 *//*
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState==4 && xmlHttp.status==200) {
			var usernull = document.getElementById("usernull");
			var result = xmlHttp.responseText;	//接受服务器端传过来的数据，写出来的数据都是String类型
			if(result == "true") {
				usernull.innerHTML = "<font color='red'>当前用户名已被注册</font>";
				return false;
			} else if(result == "false") {
				usernull.innerHTML = "<font color='green'>当前用户名可用</font>";
				return true;
			}
		}
	}
}
*/



/**
 * 注册
 * 注册密码是否合法
 * @returns {Boolean}
 */
function isPasswordLegal() {
	var password = document.getElementById("password").value;
	var nullpassword = document.getElementById("nullpassword");
	if(password == null || password == "") {
		nullpassword.innerHTML = "<font color='red'>密码不能为空</font>";
		return false;
	} else if(password.length < 8) {
		nullpassword.innerHTML = "<font color='red'>密码至少八个字符</font>";
		//document.getElementById("font").style.color= "red";
		return false;
	}
	
	nullpassword.innerHTML = "";	//输入合法后消除提示
	return true;
}

/**
 * 注册
 * 重新确认注册密码
 * @returns {Boolean}
 */
function isRepasswordLegal() {
	var password = document.getElementById("password").value;
	
	var rePassword = document.getElementById("rePassword").value;
	var nullrePassword = document.getElementById("nullrePassword");
	if(password != rePassword) {
		nullrePassword.innerHTML = "<font color='red'>前后密码不唯一</font>";
		return false;
	}
	
	nullrePassword.innerHTML = "";	//输入合法后消除提示
	return true;
}

/**
 * 注册
 * 邮箱验证
 * @returns {Boolean}
 */
function isEmailLegal() {
	var email = document.getElementById("email").value;
	var emailnull = document.getElementById("nullemail");
	var regEx = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z_-]+)+$/;
	if(email == null || email == "") {
		emailnull.innerHTML = "<font color = 'red'>邮箱不能为空</font>";
		return false;
	}else if(!regEx.test(email)) {
		emailnull.innerHTML = "<font color = 'red'>邮箱格式不正确</font>";
		return false;
	}else {
		emailnull.innerHTML="";	//将值清空
	}
	return true;
}

/**
 * 注册检验
 * @returns {Boolean}
 */
function checkRegister() {
	if(isUsernameLegal() && isPasswordLegal() && isRepasswordLegal() && isEmailLegal()) {
		return true;
	}
	return false;
}






