<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <div th:replace="~{common/common-head.html::common-head}"></div>
</head>
<body class="body-common">
<div class="page-container">
    <blockquote class="layui-elem-quote">
        <form id="dataForm" class="layui-form">
            <div class="layui-form-item">
                <#assign textflag = 1>
                <#assign jsonflag = 1>
                <#assign dateflag = 1>
        <#list allColumns as c>
            <#if c.jdbcType_='TIMESTAMP' || c.jdbcType_='DATE'>
                <div class="layui-inline"  <#if dateflag==2>style="display: none"</#if>>
                    <input type="text" name="${c.beanName}" id="${c.beanName}" placeholder="${c.remarks?json_string}" class="layui-input">
                </div>
                <#assign dateflag = 2>
            <#elseif cons[c.beanName]?? >
                <div class="layui-inline" <#if jsonflag==2>style="display: none"</#if>>
                    <label class="layui-form-label">${cons[c.beanName]['name']}</label>
                    <div class="layui-input-inline">
                        <select name="${c.beanName}" id="${c.beanName}">
                            <option value="">请选择${cons[c.beanName]['name']}</option>
                            <#list cons[c.beanName]?keys as key>
                                <#if key!='name'>
                                <option value="${key}">${cons[c.beanName][key]}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <#assign jsonflag = 2>
            <#else >
                <div class="layui-inline" <#if textflag==2>style="display: none"</#if>>
                    <input type="text" name="${c.beanName}" id="${c.beanName}" placeholder="${c.remarks?json_string}" class="layui-input">
                </div>
                <#assign textflag = 2>
            </#if>
        </#list>
                <div class="layui-inline">
                    <input type="text" name="startDate" id="startDate" placeholder="开始时间" class="layui-input">
                </div>
                <div class="layui-inline">
                    <input type="text" name="endDate" id="endDate" placeholder="结束时间" class="layui-input">
                </div>
                <div class="layui-inline">
                    <input type="button" name="" class="layui-btn" onclick="loadMyData()" data-type="reload"
                           th:value="搜索"/>
                </div>
            </div>
        </form>
    </blockquote>
    <div class="mt-20">
        <table id="table-data" lay-filter="table-data"></table>
    </div>
</div>
<script type="text/html" id="barDemo">

    <span th:if="${r'${'}session.userAuth==null || #maps.containsKey(session.userAuth,'${tableValue}/save')${r'}'}">
        <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    </span>

    <span th:if="${r'${'}session.userAuth==null || #maps.containsKey(session.userAuth,'${tableValue}/deleteByPrimaryKey')${r'}'}">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
    </span>
</script>
<script type="text/html" id="toolbar-data">
    <!-- http://www.h-ui.net/Hui-3.7-Hui-iconfont.shtml 图标库-->
    <span th:if="${r'${'}session.userAuth==null || #maps.containsKey(session.userAuth,'${tableValue}/save')${r'}'}">
    <a class="layui-btn layui-btn-normal layui-btn-sm" th:href="${r'@{/'}${tableValue}/gotoDetail ${r'}'}"><i class="layui-icon layui-icon-add-1"></i> 新增</a>
    </span>

    <span th:if="${r'${'}session.userAuth==null || #maps.containsKey(session.userAuth,'${tableValue}/export')${r'}'}">
    <a href="javascript:void(0)" onclick="exports()" class="layui-btn layui-btn-warm layui-btn-sm">
            <i  class="layui-icon layui-icon-download-circle"></i>  导出</a>
    </span>
</script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" th:inline="javascript">
    var table;
    layui.use(['form', 'table', 'laydate'], function () {
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
        form.render();
        table = layui.table;
        //第一个实例
        table.render({
            elem: '#table-data'
            , toolbar: '#toolbar-data'
            , url: getDataUrl()  //数据接口
            , method: "post"
            , page: true //开启分页
            , cols: [[ //表头
                {field: '${primaryKey[0].beanName}', title: '${primaryKey[0].beanName}', type: "checkbox"}
            <#list allColumns as c>
                <#if c.jdbcType_='TIMESTAMP' || c.jdbcType_='DATE'>
                ,{field: '${c.beanName}_', title: '${c.remarks?json_string}'}
                <#elseif cons[c.beanName]?? >
                ,{field: '${c.beanName}_', title: '${cons[c.beanName]['name']!}'}
                <#else >
                ,{field: '${c.beanName}', title: '${c.remarks?json_string}'}
                </#if>
            </#list>
                //addCodeCol
                , { title: '操作', toolbar: '#barDemo',width:250}
            ]],
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.rows //解析数据列表
                };
            },
            "request": {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            "response": {
                statusName: 'code' //规定数据状态的字段名称，默认：code
                , statusCode: 0 //规定成功的状态码，默认：0
            }
        });
        //监听行工具事件
        table.on('tool(table-data)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'del') {
                myConfirm("确定要删除吗?", function (index) {
                    obj.del();
                    $.post(rootPath + "${tableValue}/deleteByPrimaryKey", {"${primaryKey[0].beanName}": data.${primaryKey[0].beanName}}, function (data) {
                        myAlert("删除成功");
                        setTimeout(function () {
                            loadMyData()
                        }, 2000)
                    });
                    layer.close(index);
                })
            } else if (obj.event === 'edit') {
                window.location.href = rootPath + "${tableValue}/gotoDetail?${primaryKey[0].beanName}=" + data.${primaryKey[0].beanName};
            }
        });

        /*初始化日期选择框 begin*/
        var laydate = layui.laydate;
        laydate.render({
            elem: '#startDate'
            , type: 'datetime'
        });
        laydate.render({
            elem: '#endDate'
            , type: 'datetime'
        });

<#list allColumns as c>
    <#if c.jdbcType_='TIMESTAMP' >
       laydate.render({
           elem: '#${c.beanName}'
           , type: 'datetime'
       });
    <#elseif  c.jdbcType_='DATE'>
        laydate.render({
            elem: '#${c.beanName}'
            , type: 'date'
        });
    </#if>
</#list>
        /*初始化日期选择框 end*/
    });

    //组装url,如果页面有值传过来,则组装
    function getDataUrl() {
        var fkey=[[${r'${'}param.fkey${r'}'}]];
        var fvalue=[[${r'${'}param.fvalue${r'}'}]];
        var furl=[[${r'${'}param.furl${r'}'}]];
        var param="";
        if(fkey&&fvalue){
            param="?"+fkey[0]+"="+fvalue[0];
        }

        if(!furl){
            furl="${tableValue}/selectAllByPaging";
        }else{
            furl=furl[0];
        }
     return rootPath +furl +param;
    }

    function loadMyData() {
        table.reload("table-data", {
            where: $("#dataForm").serializeObject()
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    }

    //导出
    function exports() {
        window.location.href = rootPath + "${tableValue}/export?" + $("#dataForm").serialize()
    }

</script>
</body>
</html>