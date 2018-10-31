package com.spring.conf.domain;

import lombok.Data;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 09:12
 * @Description:
 */
@Data
public class ConfInfo {

    private String id;

    private String name;

    private String addDev;

    private String addPro;

    private String git;

    private String svn;

    private String description;

    public static ConfInfo getNewConfInfo(ConfInfo oldConfInfo,ConfInfo newConfInfo){

        oldConfInfo.setAddDev(newConfInfo.getAddDev()==null?oldConfInfo.getAddDev():oldConfInfo.getAddDev()+","+newConfInfo.getAddDev());
        oldConfInfo.setAddPro(newConfInfo.getAddPro()==null?oldConfInfo.getAddPro():oldConfInfo.getAddPro()+","+newConfInfo.getAddPro());
        oldConfInfo.setGit(newConfInfo.getGit()==null?oldConfInfo.getGit():oldConfInfo.getGit()+","+newConfInfo.getGit());
        oldConfInfo.setSvn(newConfInfo.getSvn()==null?oldConfInfo.getSvn():oldConfInfo.getSvn()+","+newConfInfo.getSvn());
        oldConfInfo.setDescription(newConfInfo.getDescription()==null?oldConfInfo.getDescription():oldConfInfo.getDescription()+","+newConfInfo.getDescription());
        return oldConfInfo;
    }
}
