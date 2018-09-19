package com.example.demoSpringBoot.hello.controller;

import com.example.demoSpringBoot.hello.dao.GirlDao;
import com.example.demoSpringBoot.hello.model.Girl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api("Girl控制器")
@RestController
public class GirlControll {

    @Autowired
    GirlDao girlDao;

    @ApiModelProperty(value = "保存女孩",notes = "Girl控制器")
    @GetMapping(value = "/girl/save")
    public Girl save(@RequestParam("cupSize") @NotNull String cupSize,@RequestParam("age") @NotNull Integer age){
        Girl girl=new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize );
        girlDao.save(girl);
        return girl;
    }

    @GetMapping(value = "/get-all-girl")
    public List<Girl> getAllGirl(){
        List<Girl>girls=girlDao.findAll();
        return girls;
    }


}
