<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="navigation" tagdir="/WEB-INF/tags/navigation" %>

<html>
<head>
    <navigation:navigationBar/>

    <title>${property.title}</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
</head>
<body>

<div tiles:fragment="content" class="body2">
    <div class="main">
        <section id="content">
            <div class="wrapper">
                <div class="common_col">
                    <div class="pad2">
                        <h2>View property:</h2>
                        <div class="table-property-details">
                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="dealDescription">Ad Type</label>
                                </div>
                                <div class="table-property-details-cell" id="dealDescription">
                                    ${property.statusDescription}
                                </div>
                            </div>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="title">Title:</label>
                                </div>
                                <div class="table-property-details-cell" id="title">
                                    ${property.title}
                                </div>
                            </div>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="descriptionStr">Description:</label>
                                </div>
                                <div class="table-property-details-cell">
                                    <div id="descriptionStr">${property.description}</div>
                                </div>
                            </div>

                            <c:if test="${not empty property.propertyTypeName}">
                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="propertyTypeStr">Property Type:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <div id="propertyTypeStr">${property.propertyTypeName}</div>
                                    </div>
                                </div>
                            </c:if>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="priceStr">Price:</label>
                                </div>
                                <div class="table-property-details-cell">
                                    <div id="priceStr">$ ${property.price}</div>
                                </div>
                            </div>


                            <c:if test="${not empty property.city}">
                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="cityStr">City:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <div id="cityStr">${property.city}</div>
                                    </div>
                                </div>
                            </c:if>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="address">Address:</label>
                                </div>
                                <div class="table-property-details-cell">
                                    <br/>
                                    <div id="address">${property.address}</div>
                                </div>
                            </div>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="nearbyLocations">Important places:</label>
                                </div>
                                <div class="table-property-details-cell">
                                    <br/>
                                    <div id="nearbyLocations">${property.nearbyLocations}</div>
                                </div>
                            </div>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="map">Location:</label>
                                </div>
                                <div class="table-property-details-cell">
                                    <div id="map" style="margin-bottom: 20px"></div>
                                </div>
                            </div>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    Photo
                                </div>
                                <div class="table-property-details-cell">
                                    <c:set var="imgUrl" value="/images/noimage.png"/>
                                    <c:if test="${not empty property.pictureCode}">
                                        <c:set var="imgUrl" value="/images/${property.pictureCode}"/>
                                    </c:if>
                                    <figure class="left marg_right1"><img width="500" src="${imgUrl}" alt=""></figure>
                                </div>
                            </div>

                            <sec:authorize access="isAuthenticated()">
                                <c:set var="userId"><sec:authentication property="principal.id"/></c:set>
                                <c:if test="${userId != property.ownerId}">
                                    <div class="table-property-details-row">
                                        <div class="table-property-details-cell">
                                            Message to owner
                                        </div>
                                        <div class="table-property-details-cell">
                                            <div style="padding-top: 10px">
                                                <input type="text" id="messagecontent" value="" style="width:500px;height: 25px;"/>
                                                <a href="#" id="js-send-message-btn" class="button js_approve_property_btn">Send</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </sec:authorize>

                            <input type="hidden" id="latitude" name="latitude" value="${property.latitude}"/>
                            <input type="hidden" id="longitude" name="longitude" value="${property.longitude}"/>
                            <input type="hidden" id="propertyId" value="${property.id}"/>
                            <input type="hidden" id="ownerId" value="${property.ownerId}"/>
                        </div>
                    </div>
                </div>
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
                },
            });
        });
    })
</script>

<script type="application/javascript">
    function initMap() {
        var latVal = $("#latitude").val();
        var longVal = $("#longitude").val();
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 12,
            center: {lat: Number(latVal), lng: Number(longVal)}
        });

        var Latlng = new google.maps.LatLng(Number(latVal), Number(longVal));
        var marker = new google.maps.Marker(
            {
                position: Latlng,
                title: "0"
            }
        );
        marker.setMap(map);
    }
</script>

<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqdG0IEkNfpnAvbTk_CuRd0Dhl5trYb30&callback=initMap">
</script>

</body>
</html>