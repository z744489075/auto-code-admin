package com.zengtengpeng.auto.utils;

import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import com.zengtengpeng.jdbc.bean.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * 是否生成权限
 */
public class AuthUtils {

   static Logger logger = LoggerFactory.getLogger(AuthUtils.class);
    /**
     * 添加权限
     * @param adminAutoCodeConfig
     */
    public static void addAuth(AdminAutoCodeConfig adminAutoCodeConfig) {
        Bean bean = adminAutoCodeConfig.getBean();


        String authParentId = adminAutoCodeConfig.getAuthParentId();
        if(!MyStringUtils.isEmpty(authParentId)){
            AdminStartCode adminStartCode=new AdminStartCode();
            Connection connection =null;
            try {
                connection = adminStartCode.getConnection(adminAutoCodeConfig);
                connection.setAutoCommit(false);
                int index=0;
                //根权限
                String sql1 = String.format("INSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES" +
                                "  ('%s','%s','%s','%s','%s','%s',NOW());", authParentId, bean.getTableRemarks(), 1, bean.getTableValue() + "/gotoList",
                        "layui-icon layui-icon-file-b", "0");
                PreparedStatement preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                Long key = null;
                while (generatedKeys.next()) {
                    key = generatedKeys.getLong(1);
                }
                //查询
                String sql2 = String.format("INSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES" +
                                "  ('%s','%s','%s','%s','%s','%s',NOW());",key,"查询",++index,bean.getTableValue()+"/selectAllByPaging",
                        "layui-icon layui-icon-search","1");
                preparedStatement = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.executeUpdate();
                //导出
                String sql3 = String.format("\nINSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES" +
                                "  ('%s','%s','%s','%s','%s','%s',NOW());",key,"导出",++index,bean.getTableValue()+"/export",
                        "layui-icon layui-icon-next","1");
                preparedStatement = connection.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.executeUpdate();
                //编辑
                String sql4 = String.format("\nINSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES" +
                                "  ('%s','%s','%s','%s','%s','%s',NOW());",key,"编辑",++index,bean.getTableValue()+"/save",
                        "layui-icon layui-icon-edit","1");
                preparedStatement = connection.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.executeUpdate();
                //删除
                String sql5 = String.format("\nINSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES" +
                                "  ('%s','%s','%s','%s','%s','%s',NOW());",key,"删除",++index,bean.getTableValue()+"/deleteByPrimaryKey",
                        "layui-icon layui-icon-fonts-del","1");
                preparedStatement = connection.prepareStatement(sql5, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.executeUpdate();

                logger.info("生成权限SQL:\n{}\n{}\n{}\n{}\n{}",sql1,sql2,sql3,sql4,sql5);
                connection.commit();
            } catch (Exception e) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RuntimeException(e);
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }else {
            logger.info("authParentId为空,无法自动添加权限到数据库");
        }

    }
}
