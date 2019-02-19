package com.zengtengpeng.utils;

import com.zengtengpeng.sys.bean.SysAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限递归循环
 */
public class AuthTreeUtils {
    /**
     * 递归
     * @param sysAuths
     * @return
     */
    public static List<SysAuth> recurve(List<SysAuth> sysAuths){
        ArrayList<SysAuth> root=new ArrayList<>();
        ArrayList<SysAuth> child=new ArrayList<>();
        sysAuths.forEach(t->{
            if(t.getShows().equals(0)) {
                if (t.getParentAuthId() == null || t.getParentAuthId() == 0) {
                    root.add(t);
                } else {
                    child.add(t);
                }
            }
        });
        start(root,child);
        return root;
    }

    public static List<SysAuth> start(List<SysAuth> root,List<SysAuth> child){
        root.forEach(t->{
            List<SysAuth> children=new ArrayList<>();
            List<SysAuth> other=new ArrayList<>();
            child.forEach(tt->{
                if(tt.getParentAuthId().equals(t.getId())){
                    children.add(tt);
                }else {
                    other.add(tt);
                }
            });
            //只有子节点有值才递归便利
            if(children.size()>0){
                t.setChildren(children);
                start(children,other);
            }
        });
        return root;
    }
}
