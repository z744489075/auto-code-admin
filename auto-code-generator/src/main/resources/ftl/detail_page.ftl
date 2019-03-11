<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link rel="stylesheet" type="text/css" th:href="@{/static/js/layui/css/formSelects-v4.css}"/>
    <div th:replace="~{common/common-head.html::common-head}"></div>
</head>
<body class="body-common">
<div class="page-container">
    <form class="layui-form layui-form-pane" action="">
        <input type="hidden" name="${primaryKey[0].beanName}" th:value="${r'${'}${dataName}.${primaryKey[0].beanName} ${r'}'}" />

    <#list allColumns as c>
        <#if c.beanName!=primaryKey[0].beanName>
            <#if c.jdbcType_='TIMESTAMP' || c.jdbcType_='DATE'>
                <div class="layui-form-item">
                    <label class="layui-form-label">${c.remarks}</label>
                    <div class="layui-input-block">
                        <input type="text" name="${c.beanName}" id="${c.beanName}" autocomplete="off" class="layui-input" lay-verify="required" th:value="${r'${'}${dataName}.${c.beanName}_${r'}'}" >
                    </div>
                </div>
            <#elseif cons[c.beanName]?? >
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">${cons[c.beanName]['name']!}</label>
                    <div class="layui-input-block">

                            <select name="${c.beanName}" id="${c.beanName}" lay-search>
                                <option value="">请选择${cons[c.beanName]['name']!}</option>
                                    <#list cons[c.beanName]?keys as key>
                                        <#if key!='name' && key!='type'>
		                                <option value="${key}" th:selected="${r'${'}${dataName}.${c.beanName}==${key}${r'}'}">${cons[c.beanName][key]}</option>
                                        </#if>
                                    </#list>
                            </select>
                    </div>
                </div>
            <#elseif c.jdbcType_='LONGVARCHAR'>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">${c.remarks}</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入${c.remarks}" class="layui-textarea" lay-verify="required"  th:text="${r'${'}${dataName}.${c.beanName}${r'}'}" name="${c.beanName}" id="${c.beanName}"></textarea>
                    </div>
                </div>
            <#elseif c.jdbcType_='INTEGER' || c.jdbcType_='DECIMAL' || c.jdbcType_='NUMERIC'|| c.jdbcType_='TINYINT'
            || c.jdbcType_='SMALLINT'|| c.jdbcType_='BIGINT'|| c.jdbcType_='FLOAT'|| c.jdbcType_='DOUBLE'>
            <div class="layui-form-item">
                <label class="layui-form-label">${c.remarks}</label>
                <div class="layui-input-block">
                    <input type="number" name="${c.beanName}" id="${c.beanName}" maxlength="${c.length}" lay-verify="number" autocomplete="off" placeholder="请输入${c.remarks}" class="layui-input" th:value="${r'${'}${dataName}.${c.beanName}${r'}'}" >
                </div>
            </div>
            <#else >
                <div class="layui-form-item">
                    <label class="layui-form-label">${c.remarks}</label>
                    <div class="layui-input-block">
                        <input type="text" name="${c.beanName}" id="${c.beanName}" maxlength="${c.length}" lay-verify="required" autocomplete="off" placeholder="请输入${c.remarks}" class="layui-input" th:value="${r'${'}${dataName}.${c.beanName}${r'}'}" >
                    </div>
                </div>
            </#if>
        </#if>
    </#list>
        <div class="layui-form-item" style="text-align: center">
            <span th:if="${r'${'}session.userAuth==null || #maps.containsKey(session.userAuth,'${tableValue}/save')${r'}'}">
            <button class="layui-btn" lay-submit="" lay-filter="save-data">提交</button>
            </span>
            <input type="button" class="layui-btn"  onclick="myback()" value="返回" />
        </div>
    </form>
</div>
<!--
<script src="//res.layui.com/layui/dist/layui.js" charset="utf-8"></script>-->
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.config({
        base: rootPath+'static/js/layui/extend/'
    }).extend({
        formSelects: 'formSelects-v4'
    });
    layui.use(['form', 'layedit', 'laydate','formSelects'], function () {
        var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;
        form.render();
        var formSelects = layui.formSelects;
        formSelects.render();

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

        //自定义验证规则
        form.verify({});

        //监听提交
        form.on('submit(save-data)', function (data) {
            $.post(rootPath+"${tableValue}/save",data.field,function (data) {
                if(data.code==0){
                    myAlert("保存成功");
                    setTimeout(function () {
                        myback("${tableValue}/gotoList");
                    },alertTime)
                }else{
                    myAlert("保存失败->"+data.message)
                }
            });
            return false
        });

    });
</script>

</body>
</html>