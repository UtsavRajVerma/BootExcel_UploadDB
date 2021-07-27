<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Excel Assignment</title>
</head>
<body>
<h1>Upload Excel File</h1>
<form action="success" method="post" enctype = "multipart/form-data">
    <table style="with: 50%">
        <tr>
            <td>File</td>
            <td><input type="file" class="form-control-file" id="sub" name="file" title="Upload file.">
            </td>
        </tr></table>
        <br>
    	<input type="submit" value="Submit"/>
</form>
</body>
</html>