<%@ page import="com.qssoft.security.UserAccessHelper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="navigation" tagdir="/WEB-INF/tags/navigation"%>

<html xmlns:tiles="http://www.thymeleaf.org">
  <head>
    <title>Sale&Purchase Property Online</title>
    <link rel="icon" type="image/jpg" href="images/titleImage.jpg"/>
  </head>
  <body>

  <navigation:navigationBar/>

  <div class="body2">
    <div class="main">
        <div class="wrapper">
          <article class="col1">
            <div id="slider"> <img src="images/img1.jpg" alt="" title=""></div>
          </article>


          <article class="col2">
              <div class="pad_left1">
                <h3>Find Your Property</h3>

                <form action="/search" method="GET">
                    <div class="search-form-div">
                        Input your search criteria
                        <input type="text" id="cityInput" name="city" placeholder="City *" value="${param.city}" style="border-top: 1px solid black; width: 188px;"/>
                        <input type="text" id="addressInput" name="address" placeholder="Location" value="${param.address}" style="border-top: 1px solid black; width: 188px;"/>
                        <input type="number" id="minPriceInput" name="minPrice" placeholder="Min Price (Rs)" value="${param.minPrice}" style="border-top: 1px solid black; width: 188px;"/>
                        <input type="number" id="maxPriceInput" name="maxPrice" placeholder="Max Price (Rs)" value="${param.maxPrice}" style="border-top: 1px solid black; width: 188px;"/>
                        <select name="propertyTypeId" id="propertyTypeIdInput">
                            <option value="-1">All Property Types</option>
                            <c:forEach items="${propertyTypes}" var="propertyType">
                            <option value="${propertyType.id}" ${propertyType.id eq param.propertyTypeId ? 'selected' : ''}>${propertyType.name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Search"/>
                    </div>
                    <c:if test="${not empty error}">
                        <div style="background: #ff0000; color: #ffffff;">${error}</div>
                    </c:if>
                    <br/>
                </form>

                <div>
                    <h3>Explore in famous cities</h3>
                    <br/>
                    <c:forEach items="${popularCities}" var="popularCity">
                        <a href="${pageContext.request.contextPath}/search?city=${popularCity.city}">${popularCity.city}</a>&nbsp;(${popularCity.count})&nbsp;
                    </c:forEach>
              </div>
              </div>
          </article>
        </div>
    </div>
  </div>

  <div tiles:fragment="content" class="body3">
    <div class="main">
      <section id="content">
        <div class="wrapper">

          <article class="common_col">
            <div class="pad2">
              <h2>Popular properties</h2>

              <c:choose>
                <c:when test="${not empty propertiesList}">
                  <c:forEach items="${propertiesList}" var="property">
                    <div class="wrapper pad_bot3">
                      <input type="hidden" class="js_property_id" value="${property.id}" />
                      <c:set var="imgUrl" value="/images/noimage.png"/>
                      <c:if test="${not empty property.img1}">
                          <c:set var="imgUrl" value="/files/file/${property.img1}"/>
                      </c:if>
                      <figure class="left marg_right1"><img width="100" src="${imgUrl}" alt=""></figure>
                      <p class="pad_bot1"><strong class="color2">${property.title}<br>
                        Price: <span class="color1">Rs&nbsp;${property.price}</span></strong></p>
                      <div id="address">
                          ${empty property.propertyTypeName ? '' : property.propertyTypeName.concat(' in ')}
                          ${property.address}
                          ${empty property.city ? '' : ' '.concat(property.city)}
                      </div>

                    <%--<p class="pad_bot2">${property.description}</p>--%>
                      <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasAnyRole('ROLE_OWNER')">
                        <c:set var="userId"><sec:authentication property="principal.id"/></c:set>
                        <c:choose>
                          <c:when test="${userId eq property.ownerId}">
                            <a href="/updateProperty/${property.id}" class="button js_update_property_btn">Edit</a>
                            <c:choose>
                              <c:when test="${property.statusId ne 3}">
                                <a href="#" class="button js_delete_property_btn">Delete</a>
                              </c:when>
                              <c:otherwise>
                                Deleted
                              </c:otherwise>
                            </c:choose>
                          </c:when>
                        </c:choose>
                        </sec:authorize>
                      </sec:authorize>

                      <sec:authorize access="isAuthenticated()">
                        <a href="/viewProperty/${property.id}" class="button">Read more</a>
                      </sec:authorize>

                      <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
                        <b><font size="3" color="red">Please Login to see Property Details!</font></b>

                      </sec:authorize>

                      <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <c:choose>
                          <c:when test="${userId eq property.ownerId}">
                            <a href="/updateProperty/${property.id}" class="button js_update_property_btn">Edit</a>
                          </c:when>
                          <c:otherwise>
                            <a href="/viewProperty/${property.id}" class="button">Read more</a>
                          </c:otherwise>
                        </c:choose>
                        &nbsp;
                        <c:choose>
                          <c:when test="${property.statusId eq 1}">
                            <a href="#" class="button js_approve_property_btn">Approve</a>
                          </c:when>
                          <c:when test="${property.statusId eq 2}">
                            Approved
                          </c:when>
                        </c:choose>
                      </sec:authorize>
                      <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        &nbsp;
                        <c:choose>
                          <c:when test="${property.statusId ne 3}">
                            <a href="#" class="button js_delete_property_btn">Delete</a>
                          </c:when>
                          <c:otherwise>
                            Deleted
                          </c:otherwise>
                        </c:choose>
                      </sec:authorize>
                    </div>
                  </c:forEach>
                </c:when>

                <c:otherwise>
                  <div style="display: table; width: 100%;">
                    <div style="display: table-row;">
                      <font color="blue">There is no approved properties at the moment.</font>
                    </div>
                  </div>
                </c:otherwise>
              </c:choose>
            </div>
          </article>
        </div>
      </section>
    </div>
  </div>

  <navigation:footer/>

    <script type="application/javascript">
      $(document).ready(function() {
        $(".js_delete_property_btn").on('click', function(event) {
          var index = $(event.target).closest('.wrapper.pad_bot3').find('.js_property_id').val();
          $.ajax({
            type: 'POST',
            url: '/deleteProperty/' + index,
            contentType: "application/json",
            cache: false,
            crossDomain: false,
            success: function(result){
              location.reload();
            },
            error: function (result) {
              location.reload();
            },
          });
        });

        $(".js_approve_property_btn").on('click', function(event) {
          var index = $(event.target).closest('.wrapper.pad_bot3').find('.js_property_id').val();
          $.ajax({
            type: 'POST',
            url: '/approveProperty/' + index,
            contentType: "application/json",
            cache: false,
            crossDomain: false,
            success: function(result){
              location.reload();
            },
            error: function (result) {
              location.reload();
            },
          });
        });
      });

      $(".js_view_property_btn").on('click', function(event) {
        var index = $(event.target).closest('.wrapper.pad_bot3').find('.js_property_id').val();
        $.ajax({
          type: 'POST',
          url: '/viewProperty/' + index,
          contentType: "application/json",
          cache: false,
          crossDomain: false,
          success: function(result){
            location.reload();
          },
          error: function (result) {
            location.reload();
          },
        });
      });
    </script>

  </body>
</html>
