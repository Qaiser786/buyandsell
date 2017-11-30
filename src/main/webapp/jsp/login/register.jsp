<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="navigation" tagdir="/WEB-INF/tags/navigation"%>

<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
    <title tiles:fragment="title">Register</title>
</head>

<body id="page1">

<navigation:navigationBar/>

<div tiles:fragment="content" class="body2">
    <div class="main">
        <section id="content">
            <div class="wrapper">
                <form name="registerForm" id="registerForm" th:action="@{/register}" method="post">
        <div class="register-div-format">
            <legend>Please fill the data</legend>
            <div style="color: red; visibility: hidden" id="errorMsg">
                Password does not match, or login is empty
            </div>

            <div style="display: table; width: 100%;">
                <div>
                    <div style="display: table-row;">
                        <div style="border: none; display: table-cell; padding: 3px 10px;"><label for="login">Login</label></div>
                        <div style="border: none; display: table-cell; padding: 3px 10px;"><input type="text" id="login" name="login"/>
                        </div>
                    </div>
                    <div style="display: table-row;">
                        <div style="border: none; display: table-cell; padding: 3px 10px;">Password</div>
                        <div style="border: none; display: table-cell; padding: 3px 10px;"><input type="password" id="password" name="password"/>
                        </div>
                    </div>
                    <div style="display: table-row;">
                        <div style="border: none; display: table-cell; padding: 3px 10px;">Confirmation</div>
                        <div style="border: none; display: table-cell; padding: 3px 10px;"><input type="password" id="password_confirm" name="password_confirm"/>
                        </div>
                    </div>
                    <div>
                        <input type="radio" name="roleName" value="ROLE_BUYER" checked="checked">Buyer<br>
                        <input type="radio" name="roleName" value="ROLE_OWNER">Owner<br>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <input type="button" onclick="validateAndSubmit()" class="button" value="Register" />
            </div>
        </div>
    </form>
            </div>
        </section>
    </div>
</div>

<navigation:footer/>

<script type="application/javascript">
function validateAndSubmit() {
    if($("#registerForm input[name='password']").val() != "" && $("#registerForm input[name='password_confirm']").val() != "" && $("#registerForm input[name='login']").val() != "" && $("#registerForm input[name='password_confirm']").val() == $("#registerForm input[name='password']").val()) {
        console.log("success");
        $("#registerForm").submit();
    } else {
        console.log("failed");
        $("#errorMsg").css('visibility', 'visible');
    }
}
</script>
</body>
</html>
