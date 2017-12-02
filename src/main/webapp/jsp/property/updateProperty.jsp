<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="navigation" tagdir="/WEB-INF/tags/navigation" %>

<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
    <navigation:navigationBar/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>
        ${property.id eq null ? 'Add your Property' : 'Manage your Property'}
    </title>
</head>
<body>

<div tiles:fragment="content" class="body2">
    <div class="main">
        <section id="content">
            <div class="wrapper">

                <article class="common_col">
                    <div class="pad2">
                        <h2>
                            <c:choose>
                                <c:when test="${property.id eq null}">
                                    Add your Property:
                                </c:when>
                                <c:otherwise>
                                    Manage your Property:
                                </c:otherwise>
                            </c:choose>
                        </h2>

                        <form id="propertyForm" name="f" th:action="@{/addProperty}" method="post">
                            <div class="table-property-details">
                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="dealTypeId">Ad Type</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <select name="dealTypeId" id="dealTypeId">
                                            <c:forEach items="${dealTypes}" var="dealType">
                                                <option value="${dealType.id}" ${dealType.id == property.dealTypeId ? 'selected' : ''}>${dealType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="title">Title:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <input type="text" id="title" name="title" value="${property.title}"/>
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="trumbowyg-demo">Description:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <textarea id="trumbowyg-demo">
                                            ${property.description}
                                        </textarea>
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="propertyTypeId">Property Type:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <select name="propertyTypeId" id="propertyTypeId">
                                            <c:forEach items="${propertyTypes}" var="propertyType">
                                                <option value="${propertyType.id}" ${propertyType.id eq property.propertyTypeId ? 'selected' : ''}>${propertyType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="price">Price:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <input type="number" id="price" name="price" min="1" step="any"
                                               value="${property.price}"/>
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="city">City:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <input type="textbox" id="city" name="city"
                                               value="${property.city}"/>
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="location">Location:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <input type="textbox" id="location" name="location"
                                               value="${property.address}"/>
                                        <input id="findLocation" class="button" type="button" value="Show on map">
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="nearbyLocations">Important places nearby:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        (input coma-separated list)
                                        <br/>
                                        <input type="text" id="nearbyLocations" name="nearbyLocations"
                                               value="${property.nearbyLocations}"/>
                                    </div>
                                </div>

                                <input type="hidden" id="id" name="id" value="${property.id}"/>
                                <input type="hidden" id="address" name="address" value="${property.address}"/>
                                <input type="hidden" id="description" name="description"/>
                                <input type="hidden" id="latitude" name="latitude" value="${property.latitude}"/>
                                <input type="hidden" id="longitude" name="longitude" value="${property.longitude}"/>
                                <input type="hidden" id="ownerId" name="ownerId" value="${property.ownerId}"/>
                                <input type="hidden" id="statusId" name="statusId" value="${property.statusId}"/>
                                <input type="hidden" id="pictureCode" name="pictureCode" value="${property.pictureCode}"/>
                                <input type="hidden" id="img1" name="img1" value="${property.img1}"/>
                            </div>
                        </form>

                        <div class="table-property-details">
                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    &nbsp;
                                </div>
                                <div class="table-property-details-cell">
                                    <div id="map"></div>
                                </div>
                            </div>

                            <div class="table-property-details-row" id="picturesDiv" style="${empty property.img1 ? 'display: none' : ''}">
                                <div class="table-property-details-cell">
                                    &nbsp;
                                </div>
                                <div id="imageTemplateWrapper" class="table-property-details-cell">
                                    <div id="imageLoader" style="display: none"></div>
                                    <img id="imageTemplate" src="${pageContext.request.contextPath}/files/file/${property.img1}"/>
                                </div>
                            </div>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <label for="file">Upload photo:</label>
                                </div>
                                <div class="table-property-details-cell">
                                    <form action="${pageContext.request.contextPath}/files/upload"
                                          id="fileUploadForm"
                                          method="POST"
                                          enctype="multipart/form-data"
                                          target="uploadFileIframe">
                                        <label>Max file size 10 Mb</label>
                                        <br/>
                                        <input type="file" id="file" name="file" accept="image/jpeg,image/gif,image/png"/>
                                    </form>
                                </div>
                            </div>

                            <iframe id="uploadFileIframe" name="uploadFileIframe" style="display: none"></iframe>

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    <div class="form-actions">
                                        <button type="submit" id="js_form_submit" class="button">Save</button>
                                    </div>
                                </div>
                                <div class="table-property-details-cell">
                                    &nbsp;
                                </div>
                            </div>
                        </div>
                    </div>
                </article>
            </div>
        </section>
    </div>
</div>

<navigation:footer/>

<script>
    function initMap() {
        var geocoder = new google.maps.Geocoder();

        var latVal = $("#latitude").val();
        var longVal = $("#longitude").val();
        if (longVal != null && latVal != null) {
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
        } else {
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 12,
                center: {lat: -34.397, lng: 150.644}
            });
        }

        document.getElementById('findLocation').addEventListener('click', function () {
            geocodeAddress(geocoder, map);
        });
    }

    function geocodeAddress(geocoder, resultsMap) {
        var city = document.getElementById('city').value;
        var address = document.getElementById('location').value;
        geocoder.geocode({'address': address + ' ' + city}, function (results, status) {
            if (status === 'OK') {
                console.log('Here is the result' + results[0].geometry.location);
                resultsMap.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: resultsMap,
                    position: results[0].geometry.location
                });
                $("#propertyForm input[name='latitude']").val(marker.position.lat());
                $("#propertyForm input[name='longitude']").val(marker.position.lng());
            } else {
                console.log('Geocode was not successful for the following reason: ' + status);
            }
        });
    }
</script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqdG0IEkNfpnAvbTk_CuRd0Dhl5trYb30&callback=initMap"></script>

<script>

    document.getElementById("imageTemplate").onload = function() {
        $("#imageLoader").hide();
        $("#js_form_submit").attr('disabled',false);
        $("#js_form_submit").attr('alt',"");
    };

    document.getElementById("uploadFileIframe").onload = function() {
        var reference = $($(this)[0].contentWindow.document.body.innerHTML).html();
        document.getElementById("imageTemplate").src = "${pageContext.request.contextPath}/files/file/" + reference;
        $("#img1").val(reference);
    };

    document.getElementById("file").onclick = function() {
        this.value = null;
    };

    document.getElementById("file").onchange = function() {
        var fileSize = this.files[0].size;
        if (fileSize > 10485760) {
            alert("File size exceeds 10 Mb.");
        } else {
            $("#js_form_submit").attr('disabled', true);
            $("#js_form_submit").attr('alt', "Please wait for the Images to upload.");
            $("#picturesDiv").show();
            $("#imageLoader").show();
            $("#fileUploadForm").submit();
        }
    };

    $("#js_form_submit").click(function (evt) {
        if ($("#propertyForm input[name='price']").val() == "") {
            $("#propertyForm input[name='price']").val(0);
        }
        $("#propertyForm input[name='address']").val($("#propertyForm input[name='location']").val());
        $("#propertyForm input[name='description']").val($('#trumbowyg-demo').val());
        $("#propertyForm").attr('action', '/addProperty');
        $("#propertyForm").submit();
    })

</script>

</body>
</html>

