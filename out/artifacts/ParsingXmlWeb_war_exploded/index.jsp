
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <link rel="stylesheet" href="css/styles.css" type="text/css" />
  </head>
  <body>
  <div class="wrap">
    <div class="sub-wrap"></div><p>Введите вид парсера&nbsp;</p>
    <form name="ParserManager" action="ParserManager" method="get">
    <input required class="inp" placeholder=" Вид  парсера" maxlength="4" name="input" size="10" type="text" pattern = "(sax)|(stax)|(dom)" />
    <button class="btn-ok" name="okButton" type="submit">OK</button>
    </form>
    </div>
  </div> </body>
</html>
