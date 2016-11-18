<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RunJava</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
<script >
	var keyNow;
	var keyPrev;
	function onTestChange(){
			keyNow=window.event.keyCode;
		if (keyNow == 123){
			document.getElementById("codeToExecute").value=document.getElementById("codeToExecute").value+"{}";

			return false;
		}else if (keyNow == 91){
			document.getElementById("codeToExecute").value=document.getElementById("codeToExecute").value+"[]";
			return false;
		}else if (keyNow == 39){
			document.getElementById("codeToExecute").value=document.getElementById("codeToExecute").value+"''";
			return false;
		}else if (keyNow == 34){
			document.getElementById("codeToExecute").value=document.getElementById("codeToExecute").value+"\"\"";
			return false;
		}else if (keyNow == 60){
			document.getElementById("codeToExecute").value=document.getElementById("codeToExecute").value+"<>";
			return false;
		}else if (keyNow == 40){
			document.getElementById("codeToExecute").value=document.getElementById("codeToExecute").value+"()";
			return false;
		}else{
			keyPrev = keyNow;
			return true;
		}
	}
	
	</script>
</head>
<body>


	<div id="header">
		<div>
			<div class="logo">
				<a href="index.html">RunJava</a>
			</div>
			<ul id="navigation">
				<li class="active">
					<a href="index.html">Home</a>
				</li>
				<li>
					<a href="index.html">Explore</a>
				</li>
				<li>
					<a href="index.html">About</a>
				</li>
				
				<li>
					<a href="index.html">Contact</a>
				</li>
			</ul>
		</div>
	</div>
	
	
	<div id="adbox">
			<div>
    					<th><h2>Input</h2></th>
    					<form action="executeCode" name="executeCode" method="post" class="message">
							<textarea id="codeToExecute" class="text" cols="200" rows ="80" name="codeToExecute" onkeypress="return onTestChange();">${sessionScope.codeToExecute}</textarea>
							&nbsp;

							<input type="submit" value="Run Java Code"/>
						<th><h2>Output</h2></th>
						</form>
						<form action="executedCode" name="executedCode" method="post" class="message">
    						<textarea id="codeResult" class="text" cols="86" rows ="20" name="codeResult" style="color:${sessionScope.colorResult};">${sessionScope.codeResult}</textarea>
						</form>
					
			</div>
		</div>
&nbsp;&nbsp;&nbsp;
</body>
</html>