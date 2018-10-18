//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.controller;

import com.jr.basic.api.service.SysApiManagerService;
import graphql.ExecutionResult;
import java.util.Map;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(
    name = {"app.graphql"},
    havingValue = "true",
    matchIfMissing = false
)
@RestController
@RequestMapping({"g"})
@Transactional(
    rollbackOn = {RuntimeException.class}
)
public class GraphQLController {
    private static final String MUTATION = "mutation";
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysApiManagerService graphQLService;

    public GraphQLController() {
    }

    @RequestMapping(
        value = {"query"},
        method = {RequestMethod.POST},
        produces = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@RequestBody String query) {
        ExecutionResult result = this.graphQLService.execute(query);
        Map<String, Object> data = (Map)result.getData();
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @RequestMapping(
        value = {"mutation"},
        method = {RequestMethod.POST},
        produces = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<Map<String, Object>> mutation(@RequestBody String query) {
        ExecutionResult result = this.graphQLService.execute("mutation" + query);
        Map<String, Object> data = (Map)result.getData();
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
