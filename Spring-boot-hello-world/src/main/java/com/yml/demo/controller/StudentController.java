package com.yml.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yml.demo.framework.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/yml")
public class StudentController {
    /**
     * 获取学员列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result list(Integer year) {
        if (year == null) {
            return Result.error("参数错误，year不能为空！");
        }
        List<JSONObject> list = this.getStudents();
        return Result.success(list);
    }

    @GetMapping("/proList")
    public Result proList() {
        List<JSONObject> proList = this.getProvinces();
        return Result.success(proList);
    }

    /**
     * 根据学员ID获取学员信息
     *
     * @param params
     * @return
     */
    @GetMapping("/getById")
    public Result getById(@RequestBody JSONObject params) {
        // 获取参数并进行参数校验
        Integer studentId = params.getInteger("id");
        if (studentId == null) {
            return Result.error("参数错误，id不能为空！");
        }
        //
        List<JSONObject> list = this.getStudents();
        // Map<key:studentId, value: student>
        Map<Integer, JSONObject> studentMap = this.getStudentMap(list);
        JSONObject student = studentMap.get(studentId);
        if (student == null) {
            return Result.error("参数错误，未获查询学员数据!");
        }
        return Result.success(student);
    }

    @PostMapping("/save")
    public Result save(@RequestBody JSONObject params) {
        Integer age = params.getInteger("age");
        if (age == null) {
            return Result.error("参数错误，age不能为空！");
        }
        String name = params.getString("name");
        if (StringUtils.isEmpty(name)) {
            return Result.error("参数错误，name不能为空！");
        }
        return Result.success(params);
    }


    @PutMapping("/update")
    public Result update(@RequestBody JSONObject params) {
        Integer id = params.getInteger("id");
        if (id == null) {
            return Result.error("参数错误，id不能为空！");
        }
        String age = params.getString("age");
        String name = params.getString("name");
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(age)) {
            return Result.error("参数错误，name和age不能同时为空！");
        }

        List<JSONObject> list = this.getStudents();
        Map<Integer, JSONObject> studentMap = this.getStudentMap(list);
        JSONObject student = studentMap.get(id);
        if (student == null) {
            return Result.error("参数错误，未获查询学员数据!");
        }
        return Result.success(params);
    }


    // map<key:学员id, value:学员对象>
    private Map<Integer, JSONObject> getStudentMap(List<JSONObject> list) {
        return list.stream().collect(Collectors.toMap(item -> item.getInteger("id"), Function.identity()));
    }


    // 模拟从数据库获取到的数据
    private List<JSONObject> getStudents() {
        // 创建课程对象
        JSONObject courseen = new JSONObject();
        courseen.put("id", 101);
        courseen.put("name", "英语");
        courseen.put("version", 201);
        JSONObject coursech = new JSONObject();
        coursech.put("id", 102);
        coursech.put("name", "语文");
        coursech.put("version", 202);
        JSONObject coursewl = new JSONObject();
        coursewl.put("id", 103);
        coursewl.put("name", "物理");
        coursewl.put("version", 203);
        JSONObject coursehx = new JSONObject();
        coursehx.put("id", 104);
        coursehx.put("name", "化学");
        coursehx.put("version", 204);

        // 课程集合【英语(id,  name, version)、语文(id,  name, version)】
        List<JSONObject> courseList = new ArrayList() {{
            // 添加课程对象
            add(courseen);
            add(coursech);
        }};
        List<JSONObject> courseList1 = new ArrayList() {{
            // 添加课程对象
            add(coursewl);
            add(coursehx);
        }};

        // 学员对象
        JSONObject student = new JSONObject();
        student.put("id", 10010);
        student.put("name", "叶茂林");
        student.put("sex", "男");
        student.put("address", "北京市海淀区");
        student.put("courses", courseList);
        //
        JSONObject student1 = new JSONObject();
        student1.put("id", 10011);
        student1.put("name", "张三丰");
        student1.put("sex", "男");
        student1.put("address", "上海浦东开发区");
        student1.put("courses", courseList1);

        //
        List<JSONObject> list = new ArrayList() {{
            add(student);
            add(student1);
        }};
        return list;
    }

    //省
    private List<JSONObject> getProvinces() {
        // 合肥市下的区
        // 瑶海区
        JSONObject areayh = new JSONObject();
        areayh.put("id", 10111);
        areayh.put("name", "瑶海区");
        // 蜀山区
        JSONObject areash = new JSONObject();
        areash.put("id", 10112);
        areash.put("name","蜀山区");
        // 合肥市下的区集合
        List<JSONObject> areahfList = new ArrayList(){{
            add(areayh);
            add(areash);
        }};
        // 芜湖市下的区
        // 鸠江区
        JSONObject areajj = new JSONObject();
        areajj.put("id", 10121);
        areajj.put("name", "鸠江区");
        // 弋江区
        JSONObject areayj = new JSONObject();
        areayj.put("id", 10122);
        areayj.put("name", "弋江区");
        // 芜湖市下的区集合
        List<JSONObject> areawhList = new ArrayList(){{
            add(areajj);
            add(areayj);
        }};
        // 安徽省下的市
        // 合肥市
        JSONObject cityhf = new JSONObject();
        cityhf.put("id", 1011);
        cityhf.put("name", "合肥市");
        cityhf.put("areas", areahfList);
        // 芜湖市
        JSONObject citywh = new JSONObject();
        citywh.put("id", 1012);
        citywh.put("name", "芜湖市");
        citywh.put("areas", areawhList);
        // 安徽省下市的集合
        List<JSONObject> proahList = new ArrayList() {{
            add(cityhf);
            add(citywh);
        }};

        // 南京下的区
        // 六合区
        JSONObject arealh = new JSONObject();
        arealh.put("id", 20111);
        arealh.put("name", "六合区");
        // 白下区
        JSONObject areabx = new JSONObject();
        areabx.put("id", 20112);
        areabx.put("name", "白下区");
        //南京下的区下的集合
        List<JSONObject> areanjList = new ArrayList(){{
            add(arealh);
            add(areabx);
        }};
        // 苏州下的区
        // 平江区
        JSONObject areapj = new JSONObject();
        areapj.put("id", 20121);
        areapj.put("name", "平江区");
        // 昆山区
        JSONObject areaks = new JSONObject();
        areaks.put("id", 20122);
        areaks.put("name", "昆山区");
        //苏州市下的区下的集合
        List<JSONObject> areaszList = new ArrayList(){{
            add(areapj);
            add(areaks);

        }};
        // 江苏省下的市
        // 南京市
        JSONObject citynj = new JSONObject();
        citynj.put("id", 2011);
        citynj.put("name", "南京市");
        citynj.put("areas", areanjList);

        // 苏州市
        JSONObject citysz = new JSONObject();
        citysz.put("id", 2012);
        citysz.put("name", "苏州市");
        citysz.put("areas", areaszList);
        // 江苏省下的市集合
        List<JSONObject> projsList = new ArrayList(){{
            add(citynj);
            add(citysz);
        }};

        // 1.创建省对象
        JSONObject provinceah = new JSONObject();//安徽
        provinceah.put("id", 101);
        provinceah.put("name", "安徽省");
        provinceah.put("cities", proahList);
        JSONObject provincejs = new JSONObject();//江苏
        provincejs.put("id", 201);
        provincejs.put("name", "江苏省");
        provincejs.put("cities", projsList);
        List<JSONObject> proList = new ArrayList() {{
            add(provinceah);
            add(provincejs);
        }};
        return proList;
    }
}
