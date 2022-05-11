package com.vvvbin.demo.utils;

import com.mongodb.WriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
 
import java.util.List;
import java.util.Map;
 
@Component
public class MongoDBUtil {
 
    public static final String MASTER_NAME = "masterTableName";
    public static final String SLAVE_NAME = "slaveTableName";
    public static final String MASTER_FIELD = "fieldForMaster";
    public static final String SLAVE_FIELD = "fieldForSlave";
    public static final String ALIAS_NAME = "resultInfoList";

    @Autowired
    private MongoTemplate mongoTemplate;
 
    /**
     * 保存多条数据
     *
     * @param listObj 要保存的数据列表
     * @param objName 保存到数据库中的名称
     */
    public void saveObjListData(List<Map<String, Object>> listObj, String objName) {
        mongoTemplate.insert(listObj, objName);
    }
 
    /**
     * 保存多条数据
     *
     * @param listObj 要保存的数据列表
     * @param objName 保存到数据库中的名称
     */
    public void saveObjListDataNoType(List<Map> listObj, String objName) {
        for (Map map : listObj) {
            mongoTemplate.save(map, objName);
        }
        //mongoTemplate.insert(listObj, objName);
    }
 
    /**
     * 保存单条数据
     *
     * @param obj     要存的数据
     * @param objName 数据对象名称
     */
    public void saveObj(Map<String, Object> obj, String objName) {
        mongoTemplate.insert(obj, objName);
    }
 
    /**
     * 保存或更新
     * @param obj
     * @param objName
     */
    public void saveOrUpdateObj(Map<String, Object> obj, String objName) {
        mongoTemplate.save(obj, objName);
    }
 
    /**
     * @param obj     要更新的数据 key为字段 value为要更新的数据
     * @param objName 更新的数据对象名称
     * @param conMap  条件 key为字段 value为数据
     * @return
     */
    public UpdateResult updateObj(Map<String, Object> obj, String objName, Map<String, Object> conMap) {
        Update update = new Update();
        Query query = new Query();
        if (conMap != null && conMap.size() != 0) {
            for (String key : conMap.keySet()) {
                query.addCriteria(Criteria.where(key).is(conMap.get(key)));
            }
        } else {
            return null;
        }
        for (String key : obj.keySet()) {
            update.set(key, obj.get(key));
        }
        UpdateResult wr = mongoTemplate.upsert(query, update, objName);
        return wr;
    }
 
 
    /**
     * @param objName 要删除的数据对象名称
     * @param conMap  删除条件 key为字段名称 value为数据
     * @return
     */
    public DeleteResult deleteObj(String objName, Map<String, Object> conMap) {
        Query query = new Query();
        if (conMap != null && conMap.size() != 0) {
            for (String key : conMap.keySet()) {
                query.addCriteria(Criteria.where(key).is(conMap.get(key)));
            }
        } else {
            return null;
        }
        DeleteResult remove = mongoTemplate.remove(query, objName);
        return remove;
    }
 
 
    /**
     * 查询条数
     *
     * @param conMap  要查询的数据条件 key为字段名称，value为值
     * @param sortMap 要排序的字段名称 key为要排序的字段 value为（asc）升序或者（desc）降序
     * @param objName 对象名称
     * @param index   查询开始的条数
     * @param size    取多少条记录
     * @return
     */
    public List<Map> queryData(Map<String, Object> conMap, Map<String, Object> sortMap, String objName, int index, int size) {
        Query query = new Query();
        if (conMap != null) {
            conMap.remove("limit");
            conMap.remove("offset");
            for (String key : conMap.keySet()) {
                if (conMap.get(key) != null && !StringUtils.isEmpty(conMap.get(key).toString())) {
                    // query.addCriteria(Criteria.where(key).is(conMap.get(key)));
                    query.addCriteria(Criteria.where(key).regex(".*?" + conMap.get(key).toString() + ".*"));
                }
            }
        }
//        if (sortMap != null) {
//            for (String sort : sortMap.keySet()) {
//                String value = sortMap.get(sort).toString();
//                if (value.equals("desc")) {
//                    query.with(new Sort(new Sort.Order(Sort.Direction.DESC, sort)));
//                } else {
//                    query.with(new Sort(new Sort.Order(Sort.Direction.ASC, sort)));
//                }
//            }
//        }
        if (!(index == 0 && size == 0)) {
            query.skip(index);// 从那条记录开始
            query.limit(size);// 取多少条记录
        }
 
        List<Map> list = mongoTemplate.find(query, Map.class, objName);
        return list;
 
    }
}