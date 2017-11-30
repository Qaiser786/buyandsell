<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/css/reset.css" var="resetCss" />
<spring:url value="/css/layout.css" var="layoutCss" />
<spring:url value="/css/style.css" var="styleCss" />
<spring:url value="/css/main.css" var="mainCss" />
<spring:url value="/css/trumbowyg.min.css" var="trumbowygCss" />

<spring:url value="/js/jquery-3.2.1.min.js" var="jqueryJs" />
<spring:url value="/js/script.js" var="scriptJS" />
<spring:url value="/js/jquery.nivo.slider.pack.js" var="jquerySlider" />
<spring:url value="/js/cufon-replace.js" var="cufonReplace" />
<spring:url value="/js/cufon-yui.js" var="cufonYui" />
<spring:url value="/js/atooltip.jquery.js" var="autoflipQuery" />
<spring:url value="/js/jquery.jqtransform.js" var="jqueryTransform" />
<spring:url value="/js/jquery.easing.1.3.js" var="jqueryEasing" />
<spring:url value="/js/html5.js" var="html5" />
<spring:url value="/js/Didact_Gothic_400.font.js" var="directFont" />

<head>
    <link href="${resetCss}" rel="stylesheet" />
    <link href="${layoutCss}" rel="stylesheet" />
    <link href="${styleCss}" rel="stylesheet" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${trumbowygCss}" rel="stylesheet" />

    <script src="${jqueryJs}"></script>
    <style type="text/css">
        cufon{text-indent:0!important;}@media screen,projection{cufon{display:inline!important;display:inline-block!important;position:relative!important;vertical-align:middle!important;font-size:1px!important;line-height:1px!important;}cufon cufontext{display:-moz-inline-box!important;display:inline-block!important;width:0!important;height:0!important;overflow:hidden!important;text-indent:-10000in!important;}cufon canvas{position:relative!important;}}@media print{cufon{padding:0!important;}cufon canvas{display:none!important;}}
    </style>
</head>

<div class="body1">
    <div class="main">
        <header>
            <h1><a href="<c:url value="/"/>" id="logo"></a></h1>
            <div class="wrapper">
                <ul id="icons">
                    <sec:authorize access="isAnonymous()">
                    <li id="menu_active">
                        <a href="<c:url value="/login"/>" style="float: right; color:white!important; font-size: 18px;">
                            <input class="button" type="button" value="Login">
                        </a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                    <li id="welcome_greeting">
                        <div>
                            Welcome, <sec:authentication property="principal.username" />
                        </div>
                    </li>
                    </sec:authorize>
                </ul>
            </div>
            <sec:authorize access="isAuthenticated()">
            <nav>
                <ul id="menu">
                    <li><a href="<c:url value="/"/>">Home</a></li>

                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')">
                        <li><a href="<c:url value="/addProperty"/>">Add property</a></li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole('ROLE_OWNER', 'ROLE_ADMIN', 'ROLE_BUYER')">
                        <li><a href="<c:url value="/showMessages"/>">My messages</a></li>
                    </sec:authorize>

                    <%--<sec:authorize access="hasAnyRole('ROLE_OWNER', 'ROLE_ADMIN', 'ROLE_BUYER')">--%>
                        <%--<li><a href="<c:url value="/search"/>">Search</a></li>--%>
                    <%--</sec:authorize>--%>

                    <%--<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER', 'ROLE_BUYER','ROLE_ANONYMOUS')">--%>
                        <%--<form action="/search" path="/search" method="GET">--%>
                            <%--<div class="search-form-div">--%>
                                <%--<input type="text" id="searchQuery" name="searchQuery"/>--%>
                                <%--<select name="searchCondition" id="searchCondition">--%>
                                    <%--<option value="city">City</option>--%>
                                    <%--<option value="price">Price</option>--%>
                                <%--</select>--%>
                                <%--<input id="searchButton" type="submit" class="btn" value="Search">--%>
                            <%--</div>--%>
                        <%--</form>--%>
                    <%--</sec:authorize>--%>

                    <sec:authorize access="isAuthenticated()">
                        <li>
                            <a href="<c:url value="/logout"/>" class="right_corner_item">
                                Logout
                            </a>
                        </li>
                    </sec:authorize>
                </ul>
            </nav>
            </sec:authorize>
        </header>
    </div>
</div>

<%--<script>Cufon.now();</script>--%>

<script>
    $("#js_form_submit").click(function () {
        $("#propertyForm input[name='address']").val($("#propertyForm input[name='location']").val());
        $("#propertyForm input[name='description']").val($('#trumbowyg-demo').html());
        $("#propertyForm").attr('action', '/addProperty');
        $("#propertyForm").submit();
    })

//    $(window).load(function () {
//        $('#slider').nivoSlider({
//            effect: 'sliceUpDown', //Specify sets like: 'fold,fade,sliceDown, sliceDownLeft, sliceUp, sliceUpLeft, sliceUpDown, sliceUpDownLeft'
//            slices: 17,
//            animSpeed: 500,
//            pauseTime: 6000,
//            startSlide: 0, //Set starting Slide (0 index)
//            directionNav: false, //Next & Prev
//            directionNavHide: false, //Only show on hover
//            controlNav: true, //1,2,3...
//            controlNavThumbs: false, //Use thumbnails for Control Nav
//            controlNavThumbsFromRel: false, //Use image rel for thumbs
//            controlNavThumbsSearch: '.jpg', //Replace this with...
//            controlNavThumbsReplace: '_thumb.jpg', //...this in thumb Image src
//            keyboardNav: true, //Use left & right arrows
//            pauseOnHover: true, //Stop animation while hovering
//            manualAdvance: false, //Force manual transitions
//            captionOpacity: 1, //Universal caption opacity
//            beforeChange: function () {
//                $('.nivo-caption').animate({
//                    bottom: '-110'
//                }, 400, 'easeInBack')
//            },
//            afterChange: function () {
//                Cufon.refresh();
//                $('.nivo-caption').animate({
//                    bottom: '-20'
//                }, 400, 'easeOutBack')
//            },
//            slideshowEnd: function () {} //Triggers after all slides have been shown
//        });
//        Cufon.refresh();
//    });
</script>
