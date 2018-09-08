package org.saxing.mybatis_code_helper.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 权限
 * 
 * @author 刘罕  2018/8/2 10:59
 */
public class PermissionEntity extends BasicEntity {
    private static final long serialVersionUID = 3672030445905791373L;



    private String type;
    private String resource;
    private String boundStatus = BoundStatus.UNKNOWN.getValue();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!StringUtils.equals(type, PermissionType.GATEWAY.getValue())
                && !StringUtils.equals(type, PermissionType.API.getValue())
                && !StringUtils.equals(type, PermissionType.UI.getValue())){
            throw new IllegalArgumentException("Mismatched type with value=" + type);
        }
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBoundStatus() {
        return boundStatus;
    }

    public void setBoundStatus(String boundStatus) {
        if (!StringUtils.equals(boundStatus, BoundStatus.YES.getValue())
                && !StringUtils.equals(boundStatus, BoundStatus.NO.getValue())
                && !StringUtils.equals(boundStatus, BoundStatus.UNKNOWN.getValue())){
            throw new IllegalArgumentException("Mismatched boundstatus with value=" + type);
        }

        this.boundStatus = boundStatus;
    }

    // 校验权限名，只能是字母，数字，空格，下划线，中划线这5种组合
    public void validateName(){
        String name = getName();
        if (StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Permission name can't be null or empty");
        }
        String regEx = "^[\\s0-9a-zA-Z_.-]+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(name);

        boolean matched = matcher.matches();
        if (!matched){
            throw new IllegalArgumentException("Permission name is only allowed to hold one of the followings : [A-Z, a-z, 0-9, blank, _, -, .]");
        }

    }
}
