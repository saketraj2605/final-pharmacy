<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="editMedicine" method="get" action="update">

name
<input type="text" size=25 name="bookId" value="${medicine.name}">
type
<input type="text" size=25 name="bookTitle" value="${medicine.type}">
unit value
<input type="text" size=25 name="bookAuthor" value="${medicine.unitValue}">
Price
<input type="text" size=25 name="bookPrice" value="${medicine.unitPrice}">

 <input type="radio" name="unit" value="mg" ${medicine.unit=='mg'?'checked':'' }> mg<br>
 <input type="radio" name="unit" value="ml" ${medicine.unit == 'ml'?'checked':'' }>ml<br>
 <input type="radio" name="unit" value="gm" ${medicine.unit == 'gm'?'checked':'' }> gm<br>
 <input type="radio" name="unit" value="l" ${medicine.unit == 'l'?'checked':'' }> l<br>
    

Expiry date
<input type="date" size=25 name="bookPrice" value="${medicine.expDate}">

<input type="checkbox" name="inStock" value="inStock" checked>inStock<br>


<input type="submit" onclick="return check(this.form)" value="update">
</form>
</body>
</html>