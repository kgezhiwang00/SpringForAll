package com.spring.conf.controller.api;

import com.spring.conf.rest.RestResult;
import com.spring.conf.vo.ServerInfoRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 08:53
 * @Description:
 */
public interface IConfInterface {

    @ApiOperation(value = "添加服务器信息",httpMethod="POST")
    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public RestResult addServerInfo(@ModelAttribute @RequestBody ServerInfoRequest addServerInfoRequest);


    @ApiOperation(value = "获取服务器信息", httpMethod="POST")
    @RequestMapping(value = "/getServerInfo", method = RequestMethod.POST)
    public RestResult getServerInfo(@RequestParam String id,@RequestParam String name);
}
