<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
         pageEncoding="ISO-8859-1"%> 
<%@page import="in.gadgethub.utility.AppInfo"%>
<!DOCTYPE html> 
<html> 
    <head> 
        <title>Add Product | <%=AppInfo.appName%></title> 
        <meta charset="utf-8"> 
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> 

        <link rel="stylesheet" href="mycss.css"> 
    </head> 
    <body style="background-color: #E6F9E6;"> 
        <%
            String userName = (String) session.getAttribute("userName");
            String password = (String) session.getAttribute("password");
            String userType = (String) session.getAttribute("userType");
            if (userType == null || !userType.equalsIgnoreCase("admin")) {
                response.sendRedirect("login.jsp?message=Access denied ! Please login as admin");
            } else if (userName == null || password == null) {
                response.sendRedirect("login.jsp?message=Session expired ! Please login again");
            }
        %> 

        <jsp:include page="header.jsp" /> 

        <%
            String message = (String) request.getAttribute("message");

        %> 
        <div class="container"> 
            <div class="row justify-content-center"> 
                <form action="./AddProductServlet" method="post" enctype="multipart/form-data" 
                      class="col-md-6 col-md-offset-3 myform"> 
                    <div class="text-center"> 
                        <h2 class="text-primary">Product Addition Form</h2> 
                        <% if (message != null) {
                        %> 
                        <p style="color: blue;"> 
                            <%=message%> 
                        </p> 
                        <%
                            }
                        %> 
                    </div> 
                    <div></div> 
                    <div class="row mt-3"> 
                        <div class="col-md-6 form-group"> 
                            <label for="product_name">Product Name</label><input type="text" placeholder="Enter Product Name" name="name" class="form-control" id="product_name" required> 
                        </div> 
                        <div class="col-md-6 form-group"> 
                            <label for="producttype">Product Type</label>
                            <select name="type" id="producttype" class="form-control" required> 
                                <option value="mobile">MOBILE</option> 
                                <option value="tv">TV</option> 
                                <option value="camera">CAMERA</option> 
                                <option value="laptop">LAPTOP</option> 
                                <option value="tablet">TABLET</option> 
                                <option value="speaker">SPEAKER</option> 
                                <option value="book">BOOK</option> 
                                <option value="pen">PEN</option> 
                                <option value="pandrive">PAN DRIVE</option> 
                                <option value="copy">COPY</option> 
                                <option value="trollybag">TROLLY BAG</option> 
                                <option value="bag">BAG</option> 
                                <option value="other">Some Other Appliances</option> 
                            </select> 
                        </div> 
                    </div> 
                    <div class="form-group mt-3"> 
                        <label for="product_desc">Product Description</label> 
                        <textarea name="info" class="form-control" id="product_desc" required></textarea> 
                    </div> 
                    <div class="row mt-3"> 
                        <div class="col-md-6 form-group"> 
                            <label for="unit_price">Unit Price</label>

                            <input type="number"
                                   placeholder="Enter Unit Price"
                                   name="price"
                                   class="form-control"
                                   id="unit_price"
                                   required>
                        </div> 
                        <div class="col-md-6 form-group"> 
                            <label for="stock_qty">Stock Quantity</label><input type="number" 
                                                                                placeholder="Enter Stock Quantity" name="quantity" 
                                                                                class="form-control" id="stock_qty" required> 
                        </div> 
                    </div> 
                    <div class="mt-3"> 
                        <div class="col-md-12 form-group"> 
                            <label for="product_img">Product Image</label><input type="file" 
                                                                                 placeholder="Select Image" name="image" class="form-control" 
                                                                                 id="product_img" required> 
                        </div> 
                    </div> 
                    <div class="row mt-3"> 
                        <div class="col-md-6 text-center" style="margin-bottom: 2px;"> 
                            <button type="reset" class="btn btn-danger">Reset</button> 
                        </div> 
                        <div class="col-md-6 text-center"> 
                            <button type="submit" class="btn btn-primary">Add Product</button> 
                        </div> 
                    </div> 
                </form> 
            </div> 
        </div> 

        <%@ include file="footer.jsp"%> 
    </body> 
</html>