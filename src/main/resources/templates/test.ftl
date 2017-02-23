<html>
<head>
    <title></title>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
    ${user}
    <div class="box" name="boxc_b">a</div>
    <div class="nextbox">b</div>
    <div class="box_c" >c</div>
</body>
</html>
<script>
    $(function () {
        $(".box").find(".boxc_b").click(function () {
            var center = $(".boxc_c").text();//.appendTo("nextbox");
            if (center == "") {
                alert("请输入内容喔！");
                return;
            }
            $(".nextbox").prepend("<div class="a">" +
            "<div class="b"></div>" +
            "<span id="time">" + year + "-" +
            month + "-" +
            day + "  " +
            "<span id="hour">" + hour + ":" + min + "</span>" +
            "</span>" +
            "<br>" +
            "<p style="padding:4px">" + center + "</p>" +
            "</div>");
            $(".boxc_c").text("");
        });
        //alert(1);
        $(".boxc_c").keydown(function (event) {
            var len = $(".boxc_c").text().length;
            if (len > 70) {
                alert("够了，你别输入了，哪儿那么多话儿！");
            }
        });

        var dateDom = new Date();
        //获取本地时间，年月日时分
        var year = dateDom.getFullYear();
        var month = dateDom.getMonth() + 1;
        var day = dateDom.getDate();
        var hour = dateDom.getHours();
        var min = dateDom.getMinutes();
    });
</script>