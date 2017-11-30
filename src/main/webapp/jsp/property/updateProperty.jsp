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
</head>
<body>

<div tiles:fragment="content" class="body2">
    <div class="main">
        <section id="content">
            <div class="wrapper">

                <article class="common_col">
                    <div class="pad2">
                        <h2>Manage your property:</h2>

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
                                        <label for="price">Price:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        <input type="number" id="price" name="price" min="1" step="any"
                                               value="${property.price}"/>
                                    </div>
                                </div>

                                <div class="table-property-details-row">
                                    <div class="table-property-details-cell">
                                        <label for="nearbyLocations">Important places:</label>
                                    </div>
                                    <div class="table-property-details-cell">
                                        (input coma-separated list)
                                        <br/>
                                        <input type="text" id="nearbyLocations" name="nearbyLocations"
                                               value="${property.nearbyLocations}"/>
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
                                <input type="hidden" id="id" name="id" value="${property.id}"/>
                                <input type="hidden" id="address" name="address" value="${property.address}"/>
                                <input type="hidden" id="description" name="description"/>
                                <input type="hidden" id="latitude" name="latitude" value="${property.latitude}"/>
                                <input type="hidden" id="longitude" name="longitude" value="${property.longitude}"/>
                                <input type="hidden" id="ownerId" name="ownerId" value="${property.ownerId}"/>
                                <input type="hidden" id="statusId" name="statusId" value="${property.statusId}"/>
                                <input type="hidden" id="pictureCode" name="pictureCode" value="${property.pictureCode}"/>
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

                            <div class="table-property-details-row">
                                <div class="table-property-details-cell">
                                    &nbsp;
                                </div>
                                <div class="table-property-details-cell">
                                    <form action="uploadPicture" method="post" enctype="multipart/form-data">
                                        Select Photo: <input type="file" name="file" id="file"/>
                                        <input type="button" id="fileUpload" class="button" value="Upload Photo"/>
                                    </form>
                                </div>
                            </div>

                            <div class="table-property-details-row" id="picturesDiv" style="display: none">
                                <div class="table-property-details-cell">
                                    &nbsp;
                                </div>
                                <div id="imageTemplateWrapper" style="display: none">
                                    <img id="imageTemplate" width="100" src=""/>
                                </div>
                                <div id="imageGallery" class="table-property-details-cell">
                                </div>
                            </div>

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
        var address = document.getElementById('location').value;
        geocoder.geocode({'address': address}, function (results, status) {
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
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqdG0IEkNfpnAvbTk_CuRd0Dhl5trYb30&callback=initMap">
</script>

<script>
    $(document).ready(function() {
        var existingImage = $("#propertyForm input[name='pictureCode']").val();
        if (existingImage != undefined && existingImage != "") {
            $('#imageTemplate').attr("src", "/images/" + existingImage);
            $("#imageGallery").html($("#imageTemplateWrapper").html())
            $("#picturesDiv").show();
        }
    })


    $("#js_form_submit").click(function (evt) {
        if ($("#propertyForm input[name='price']").val() == "") {
            $("#propertyForm input[name='price']").val(0);
        }
        $("#propertyForm input[name='address']").val($("#propertyForm input[name='location']").val());
        $("#propertyForm input[name='description']").val($('#trumbowyg-demo').val());
        $("#propertyForm").attr('action', '/addProperty');
        $("#propertyForm").submit();
    })

    $("#fileUpload").click(function (evt) {
        var formData = new FormData();
        formData.append('file', $('#file')[0].files[0]);

        $.ajax({
            url : '/uploadPicture',
            type : 'POST',
            method: 'POST',
            data : formData,
            processData: false,
            contentType: false,
            success : function(data) {
                console.log(data);
                $("#propertyForm input[name='pictureCode']").val(data);
                $('#imageTemplate').attr("src", "/images/" + data);
                $("#imageGallery").html($("#imageTemplateWrapper").html())
                $("#picturesDiv").show();
            }
        });
    })
</script>

</body>
</html>

