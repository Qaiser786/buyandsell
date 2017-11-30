<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="navigation" tagdir="/WEB-INF/tags/navigation" %>

<html>
<head>
    <title>Messages List</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
</head>

<body>

<navigation:navigationBar/>

<div class="body2">
    <div class="main">
        <section id="content">
            <div class="wrapper">
                <div class="common_col">
                    <div class="pad2">
                        <h2>View property:</h2>
                        <c:choose>
                            <c:when test="${not empty messagesList}">
                                <div class="table-property">
                                    <div class="table-property-title">
                                        <p>List of messages to you</p>
                                    </div>
                                    <div class="table-property-heading">
                                        <div class="table-property-cell">
                                            <p>From</p>
                                        </div>
                                        <div class="table-property-cell">
                                            <p>Property</p>
                                        </div>
                                        <div class="table-property-cell">
                                            <p>Status</p>
                                        </div>
                                        <div class="table-property-cell">
                                            <p>Action</p>
                                        </div>
                                    </div>
                                    <c:forEach items="${messagesList}" var="message">
                                        <div class="table-property-row">
                                            <div class="table-property-cell">
                                                <p>${message.senderName}</p>
                                            </div>
                                            <div class="table-property-cell">
                                                <p>${message.adTopic}</p>
                                            </div>
                                            <div class="table-property-cell">
                                                <c:if test="${message.status eq 1}">
                                                    <p>UNREAD</p>
                                                </c:if>
                                                <c:if test="${message.status eq 2}">
                                                    <p>READ</p>
                                                </c:if>
                                            </div>
                                            <div class="table-property-cell">
                                                <a href="/viewMessage/${message.messageId}" class="button js_delete_property_btn">Open</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div style="display: table; width: 100%;">
                                    <div style="display: table-row;">
                                        There is nothing to display now.
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<navigation:footer/>

</body>
</html>
