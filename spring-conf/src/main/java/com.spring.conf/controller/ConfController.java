package com.spring.conf.controller;

import com.spring.conf.controller.api.IConfInterface;
import com.spring.conf.domain.ConfInfo;
import com.spring.conf.rest.ErrorCode;
import com.spring.conf.rest.RestResult;
import com.spring.conf.rest.RestResultGenerator;
import com.spring.conf.service.IConfService;
import com.spring.conf.vo.ServerInfoRequest;
import com.spring.conf.vo.ServerInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 09:08
 * @Description:
 */
@RestController
@RequestMapping("/conf")
@Slf4j
public class ConfController implements IConfInterface {

    @Autowired
    private IConfService confService;

    @Override
    public RestResult addServerInfo(ServerInfoRequest addServerInfoRequest) {

        log.info("start addServerInfo,request:{}",addServerInfoRequest.toString());
        try {
            ConfInfo confInfo = new ConfInfo();
            BeanUtils.copyProperties(addServerInfoRequest,confInfo);
            confService.addServerInfo(confInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            return RestResultGenerator.genErrorResult(ErrorCode.SERVER_ERROR);
        }
        return RestResultGenerator.genSuccessResult("");
    }

    @Override
    public RestResult getServerInfo(String id,String name) {
        log.info("start getServerInfo,request:{}",name);
        ServerInfoResponse serverInfoResponse = new ServerInfoResponse();
        try {
            ConfInfo confInfo = confService.getServerInfo(id,name);
            serverInfoResponse = ServerInfoResponse.getServerInfoResponseByServerInfo(confInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            return RestResultGenerator.genErrorResult(ErrorCode.SERVER_ERROR);
        }
        return RestResultGenerator.genSuccessResult(serverInfoResponse);
    }
}
