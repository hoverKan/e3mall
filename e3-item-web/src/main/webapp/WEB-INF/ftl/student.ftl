<html>
<head>
    <title>Student</title>
</head>

<body>
    学生信息：<br>
    学号：${student.id}&nbsp;&nbsp;&nbsp;&nbsp;
    姓名：${student.name}&nbsp;&nbsp;&nbsp;&nbsp;
    年龄：${student.age}&nbsp;&nbsp;&nbsp;&nbsp;
    家庭地址：${student.address}<br>

    学生列表：
    <table border="1">
        <tr>
            <th>序号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>家庭住址</th>
        </tr>
        <#list studentList as student>
        <#if student_index % 2 == 0>
            <tr bgcolor="red">
        <#else>
            <tr bgcolor="gray">
        </#if>
                <td>${student_index}</td>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.age}</td>
                <td>${student.address}</td>
            </tr>
        </#list>

    </table>

    <br>
    日期：${date?date}<br>
    日期：${date?time}<br>
    日期：${date?datetime}<br>
    日期：${date?string('yyyy年MM月dd日 HH:mm:ss')}<br>

    null值的处理：${val!"My Default Value!"}<br>
    <#--null值的处理：${val}<br>-->
    <#if val??>
        when-present val 有内容
        <br>
        ${val}
    <#else>
        when-missing val 为空
        <br>
        ${val!"My Default Value!"}
    </#if>
    <br>
    <#--引用模板：-->
    <#include "hello.ftl">

</body>

</html>