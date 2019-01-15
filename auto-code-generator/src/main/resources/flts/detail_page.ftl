<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <div th:replace="~{common/common-head.html::common-head}"></div>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
    <span class="c-gray en">&gt;</span>
    ${tableRemarks}
    <span class="c-gray en">&gt;</span>
    编辑
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
       href="javascript:loads();" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
    <form class="layui-form layui-form-pane" action="">
        <input type="hidden" name="${primaryKey.javaProperty}" th:value="${r'${'}${dataName}.${primaryKey.javaProperty} ${r'}'}" />

    <#list allColumns as c>
        <#if c.javaProperty!=primaryKey.javaProperty>
            <#if c.jdbcTypeName='TIMESTAMP' || c.jdbcTypeName='DATE'>
                <div class="layui-form-item">
                    <label class="layui-form-label">${c.remarks}</label>
                    <div class="layui-input-block">
                        <input type="text" name="${c.javaProperty}" id="${c.javaProperty}" autocomplete="off" class="layui-input" lay-verify="required"
                               th:value="${r'${'}${dataName}.${c.javaProperty}_${r'}'}">
                    </div>
                </div>
            <#elseif cons[c.javaProperty]?? >
                <div class="layui-form-item">
                    <label class="layui-form-label">${cons[c.javaProperty]['name']!}</label>
                    <div class="layui-input-block">

                            <select name="${c.javaProperty}" id="${c.javaProperty}">
                                <option value="">请选择${cons[c.javaProperty]['name']!}</option>
                                    <#list cons[c.javaProperty]?keys as key>
                                        <#if key!='name'>
		                                <option value="${key}" th:selected="${r'${'}${dataName}.${c.javaProperty}==${key}${r'}'}">${cons[c.javaProperty][key]}</option>
                                        </#if>
                                    </#list>
                            </select>
                        </select>
                    </div>
                </div>
            <#elseif c.jdbcTypeName='LONGVARCHAR'>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">${c.remarks}</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入${c.remarks}" class="layui-textarea" lay-verify="required"
                                  th:text="${r'${'}${dataName}.${c.javaProperty}${r'}'}" name="${c.javaProperty}" id="${c.javaProperty}"></textarea>
                    </div>
                </div>
            <#else >
                <div class="layui-form-item">
                    <label class="layui-form-label">${c.remarks}</label>
                    <div class="layui-input-block">
                        <input type="text" name="${c.javaProperty}" id="${c.javaProperty}" maxlength="${c.length}" lay-verify="required" autocomplete="off"
                               placeholder="请输入${c.remarks}" class="layui-input" th:value="${r'${'}${dataName}.${c.javaProperty}${r'}'}" >
                    </div>
                </div>
            </#if>
        </#if>
    </#list>
        <div class="layui-form-item" style="text-align: center">
            <button class="layui-btn" lay-submit="" lay-filter="save-data">提交</button>
            <input type="button" class="layui-btn"  onclick="myback()" value="返回" />
        </div>
    </form>
</div>
<!--
<script src="//res.layui.com/layui/dist/layui.js" charset="utf-8"></script>-->
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;
        form.render();


        <#list allColumns as c>
            <#if c.jdbcTypeName='TIMESTAMP' >
           laydate.render({
               elem: '#${c.javaProperty}'
               , type: 'datetime'
           });
            <#elseif  c.jdbcTypeName='DATE'>
            laydate.render({
                elem: '#${c.javaProperty}'
                , type: 'date'
            });
            </#if>
        </#list>

        //自定义验证规则
        form.verify({});

        //监听提交
        form.on('submit(save-data)', function (data) {
            $.post(rootPath+"${tableValue}/save",data.field,function (data) {
                if(data.code==0){
                    myAlert("保存成功");
                    setTimeout(function () {
                        myback();
                    },2000)
                }else{
                    myAlert("保存失败->"+data.msg)
                }
            });
            return false
        });

    });
</script>

</body>
</html>