<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="navigation" tagdir="/WEB-INF/tags/navigation" %>

<html>
<head>
    <title>Message Details</title>
    <navigation:navigationBar/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
</head>

<body>

<div tiles:fragment="content" class="body2">
    <div class="main">
        <section id="content">
            <div class="wrapper">
                <article class="common_col">
                    <div class="pad2">
                        <h2>Message Details:</h2>
                        <div class="table-property-details">
                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="dealDescription">Here is the question about:</label>
                                </div>
                                <div class="table-property-details-cell" id="dealDescription">
                                    <a href="/viewProperty/${message.propertyId}" class="button">Open Ad</a>
                                </div>
                            </div>
                        </div>


                        <div class="table-property-details">
                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    ${message.message}
                                </div>
                            </div>
                        </div>

                        <sec:authorize access="isAuthenticated()">
                            <div class="table-property-details-row">
                                <div style="padding-left:140px">
                                    <div class="table-message-replay-cell">
                                        Replay
                                    </div>
                                    <div class="table-message-replay-cell">
                                        <div style="padding-top: 10px">
                                            <input type="text" id="messagecontent" value=""
                                                   style="width:500px;height: 25px;"/>
                                            <a href="#" id="js-send-message-btn"
                                               class="button js_approve_property_btn">Send</a>
                                        </div>
                                    </div>
                                    <input type="hidden" id="propertyId" value="${message.propertyId}"/>
                                    <input type="hidden" id="ownerId" value="${message.ownerId}"/>
                                </div>
                            </div>
                        </sec:authorize>
                    </div>
                </article>
            </div>
        </section>
    </div>
</div>

<navigation:footer/>

<script type="application/javascript">
    $(document).ready(function () {
        $("#js-send-message-btn").on('click', function (event) {
            var ownerId = $("#ownerId").val();
            var propertyId = $("#propertyId").val();
            var messageToSend = $("#messagecontent").val();
            var messageJSON = {};
            messageJSON["message"] = messageToSend;
            messageJSON["propertyId"] = propertyId;
            messageJSON["ownerId"] = ownerId;
            $.ajax({
                type: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                contentType: "application/json; charset=utf-8",
                url: '/postMessage',
                data: JSON.stringify(messageJSON),
                dataType: 'json',
                success: function (result) {
                    alert("Your message has been sent");
                    $("#messagecontent").val("");
                },
                error: function (result) {
                    console.log(result);
                    s
                },
            });
        });
    })
</script>

</body>
</html>
