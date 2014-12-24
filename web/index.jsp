<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
<div>
  <p>FORM</p>
  <%--
      request.getParameter(...) will work for normal url parameters with matching parameter names i.e. getParameter(firstname) will return terry if ?firstname=terry
      but it can also pick up form element values if you use the name attribute.
      So use the name attribite if you want its values to be picked up by response.getParameter(...)
      --%>
  <form action="helloworld" id="helloworld">
    <p><input name="name" type="text" /></p>
    <p>
      <select name="response">
        <option name="html" value="html" selected="selected" >html</option>
        <option name="xml" value="xml" >xml</option>
        <option name="json" value="json" >json</option>
      </select>
    </p>
    <p>
      <select name="method" onchange="document.getElementById('helloworld').setAttribute('method',this.value)">
        <option name="get" value="get" selected="selected">GET</option>
        <option name="post" value="post">POST</option>
      </select>
    </p>
    <p><input type="submit" /></p>
  </form>
</div>
</body>
</html>