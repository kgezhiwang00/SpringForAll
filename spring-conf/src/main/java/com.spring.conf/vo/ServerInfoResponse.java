package com.spring.conf.vo;

import com.spring.conf.domain.ConfInfo;
import lombok.Data;
import org.apache.catalina.util.ServerInfo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 09:03
 * @Description:
 */
@Data
public class ServerInfoResponse {

    private String name;

    private List<String> addDev;

    private List<String> addPro;

    private List<String> git;

    private List<String> svn;

    private String description;

    public static ServerInfoResponse getServerInfoResponseByServerInfo(ConfInfo confInfo){
        ServerInfoResponse serverInfoResponse = new ServerInfoResponse();

        if(confInfo !=null){
            serverInfoResponse.setName(confInfo.getName());
        }
        if(!StringUtils.isEmpty(confInfo.getAddDev())){
            List<String> addDev = new ArrayList<>();
            addDev = Arrays.asList(confInfo.getAddDev().split(","));
            serverInfoResponse.setAddDev(addDev);
        }
        if(!StringUtils.isEmpty(confInfo.getAddPro())){
            List<String> addPro = new ArrayList<>();
            addPro = Arrays.asList(confInfo.getAddPro().split(","));
            serverInfoResponse.setAddPro(addPro);
        }
        if(!StringUtils.isEmpty(confInfo.getSvn())){
            List<String> svn = new ArrayList<>();
            svn = Arrays.asList(confInfo.getSvn().split(","));
           serverInfoResponse.setSvn(svn);
        }
        if(!StringUtils.isEmpty(confInfo.getGit())){
            List<String> git = new ArrayList<>();
            git = Arrays.asList(confInfo.getGit().split(","));
            serverInfoResponse.setGit(git);
        }
        return serverInfoResponse;

    }
}
