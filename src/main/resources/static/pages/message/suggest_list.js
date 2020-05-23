layui.extend({
    admin: '{/}../../static/js/admin'
});

layui.use(['table', 'jquery', 'form', 'admin'], function () {
    var table = layui.table,
        $ = layui.jquery,
        form = layui.form,
        admin = layui.admin;

    table.render({
        elem: '#articleList',
        cellMinWidth: 80,
        event: true,
        page: true,
        url: "http://localhost:10010/suggest/list",
        cols: [[
            {type: 'checkbox'},
            {field: 'id', title: 'ID', sort: true},
            {field: 'content', title: '内容', templet: '#usernameTpl'},
            {field: 'type', title: '分类',},
            {
                field: 'createDate',
                title: '发布时间',
                sort: true,
                templet: "<div>{{layui.util.toDateString(d.createDate, 'yyyy年MM月dd日 ')}}</div>"
            },
            {field: 'status', title: '状态', sort: true},
            {field: 'operate', title: '操作', toolbar: '#operateTpl', unresize: true}
        ]]

    });

    //查询
    form.on('submit(sreach)', function (data) {
        table.render({
            elem: '#articleList',
            cellMinWidth: 80,
            page: true,
            url: "http://localhost:10010/suggest/search?type=" + data.field.type,
            cols: [[
                {type: 'checkbox'},
                {field: 'id', title: 'ID', sort: true},
                {field: 'content', title: '内容', templet: '#usernameTpl'},
                {field: 'type', title: '分类',},
                {
                    field: 'createDate',
                    title: '发布时间',
                    sort: true,
                    templet: "<div>{{layui.util.toDateString(d.createDate, 'yyyy年MM月dd日 ')}}</div>"
                },
                {field: 'status', title: '状态', sort: true},
                {field: 'operate', title: '操作', toolbar: '#operateTpl', unresize: true}
            ]]
        });
        return false;
    });
    //查看文章
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        if (obj.event === 'setnext') {
            $.ajax({
                url: 'http://localhost:10010/suggest/setnext?id=' + data.id,
                type: 'GET',
                data: {},
                contentType: 'application/json',
                //请求成功时执行该函数
                success: function (result) {
                    if (result.code == '0') {
                        location.replace(location.href);
                    } else {
                        alert(result.msg);
                    }
                },
                //请求失败时执行该函数
                error: function (errorMsg) {
                    alert("数据异常!" + errorMsg.msg);
                }
            });
        } else if (obj.event === 'reply'&& data.status==="未查看") {
            layer.open({
                type: 2,
                area: [($(window).width() * 0.4) + 'px', ($(window).height() - 250) + 'px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: "查看",
                content: "./suggest_reply.html#/id=" + data.id,
            });
        }
        if (obj.event === 'del') {
            $.ajax({
                url: 'http://localhost:10010/suggest/deleteById?id=' + data.id,
                type: 'GET',
                data: {},
                contentType: 'application/json',
                //请求成功时执行该函数
                success: function (result) {
                    // alert(JSON.stringify(result));
                    if (result.code == '0') {
                        alert("删除成功！");
                        location.replace(location.href);
                    } else {
                        alert(result.msg);
                    }
                },
                //请求失败时执行该函数
                error: function (errorMsg) {
                    alert("数据异常!" + errorMsg.msg);
                }
            });
        }
    });
    /*
     *数据表格中form表单元素是动态插入,所以需要更新渲染下
     * http://www.layui.com/doc/modules/form.html#render
     * */
    $(function () {
        form.render();
    });
    //删除选中数据
    var active = {
        getCheckData: function () { //获取选中数据
            var checkStatus = table.checkStatus('articleList'),
                data = checkStatus.data;
            layer.msg(data.length);
            if (data.length > 0) {
                layer.confirm('确认要删除吗？', function (index) {
                    //找到所有被选中的，发异步进行删除
                    $.ajax({
                        url: 'http://localhost:10010/suggest/deleteList',
                        type: 'POST',
                        data: JSON.stringify(data),
                        contentType: 'application/json',
                        //请求成功时执行该函数
                        success: function (result) {
                            if (result.code == '0') {
                                layer.msg(result.msg, {time: 1 * 1000}, function () {
                                    $(".layui-table-body .layui-form-checked").parents('tr').remove();
                                });
                            } else {
                                alert(result.msg);
                            }
                        },
                        //请求失败时执行该函数
                        error: function (errorMsg) {
                            alert("数据异常!" + errorMsg.msg);
                        }
                    });
                });
            } else {
                layer.msg("请先选择需要删除的文章！");
            }
        }
    };


});
