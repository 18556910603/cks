package sz.zp.cks.service.impl;

import java.util.*;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import sz.zp.cks.common.Json;
import sz.zp.cks.dao.MenuMapper;
import sz.zp.cks.entity.Menu;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.MainJspValue;
import sz.zp.cks.model.MonthReCounts;
import sz.zp.cks.model.UserEpTypeCounts;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.MenuService;
import sz.zp.cks.tree.BuildTree;
import sz.zp.cks.tree.Tree;
import sz.zp.cks.dao.EquipmentRepairMapper;
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private EquipmentRepairService equipmentRepairService;
	@Autowired
	EquipmentRepairMapper equipmentRepairMapper;
	
	
	@Override
	public int insert(Menu entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Menu entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Menu entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Menu select(Menu entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findList(Menu entity) {
		// TODO Auto-generated method stub
		return menuMapper.findList(entity);
	}

	@Override
	public JSONObject findListMsg(User user) {
	        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
	        List<Menu> MenuList = new ArrayList<Menu>();
	        MenuList = menuMapper.findListByUser(user);
	        for (Menu test : MenuList) {
	            Tree<Menu> tree = new Tree<Menu>();
	            tree.setId(test.getMenuId().toString());
	            tree.setParentId(test.getParentId().toString());
	            tree.setText(test.getName());
	            tree.setUrl(test.getUrl());
	            trees.add(tree);
	        }
	        List<Tree<Menu>> children=BuildTree.build(trees);
	        JSONArray json2 = JSONArray.fromObject( children );//将java对象转换为json对象
	        JSONArray noteObj=(JSONArray)((json2.getJSONObject(0)).get("children")) ;
	        JSONObject conObj = new JSONObject();
	        conObj.put("children", noteObj);
	        JSONObject conObj2 = new JSONObject();
	        conObj2.put("extend",conObj);
	        return conObj2;
	}

	

public static void main(String[] args) {
	Json json=new Json();
    List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
    List<Menu> MenuList = new ArrayList<Menu>();
//    MenuList.add(new Menu(1,"主菜单",1));
//    MenuList.add(new Menu(2,"权限系统",1));
//    MenuList.add(new Menu(3,"内容管理",1));
//    MenuList.add(new Menu(4,"用户管理",2));
//    MenuList.add(new Menu(5,"角色管理",2));
//    MenuList.add(new Menu(6,"权限管理",2));
//    MenuList.add(new Menu(7,"权限增加",6));
//    MenuList.add(new Menu(8,"权限删除",6));
//    MenuList.add(new Menu(9,"轮播图管理",3));
//    MenuList.add(new Menu(10,"商品管理",3));

    for (Menu test : MenuList) {
        Tree<Menu> tree = new Tree<Menu>();
        tree.setId(test.getMenuId().toString());
        tree.setParentId(test.getParentId().toString());
        tree.setText(test.getName());
        trees.add(tree);
    }
    List<Tree<Menu>> children=BuildTree.build(trees);


    
    
    
    JSONArray json2 = JSONArray.fromObject( children );//将java对象转换为json对象
    JSONArray noteObj=(JSONArray)((json2.getJSONObject(0)).get("children")) ;
    
    
    JSONObject conObj = new JSONObject();
    conObj.put("children", noteObj);
    
    JSONObject conObj2 = new JSONObject();
    conObj2.put("extend",conObj);
    
    
    
    json.setObj(conObj2);
}

@Override
public List<MonthReCounts> qryCountsByMonth() {
	List<MonthReCounts> list=new ArrayList<MonthReCounts>();
	list=equipmentRepairService.qryCountsByMonth();
	return list;
}

@Override
public Model viewMain(Model model) {
	//查询每个月巡检和业主报修的台数(首页柱状图)
	List<MonthReCounts> monthList = qryCountsByMonth();
	JSONArray json2 = JSONArray.fromObject( monthList );//将java对象转换为json对象
	model.addAttribute("monthList", json2);
	//首页第一行MainJspValue 
	MainJspValue tMainJspValue = new MainJspValue();
	tMainJspValue=equipmentRepairMapper.selectForMainJspValue();
	model.addAttribute("tMainJspValue", tMainJspValue);
	//首页第二个柱状图 各巡检人员设备分布
	List<UserEpTypeCounts> tUserEpTypeCountsList =new ArrayList<UserEpTypeCounts>();
	tUserEpTypeCountsList= equipmentRepairMapper.qryUserEpTypeCount();
	JSONArray tUserEpTypeCountsListJson = JSONArray.fromObject( tUserEpTypeCountsList );//将java对象转换为json对象
	model.addAttribute("tUserEpTypeCountsList", tUserEpTypeCountsListJson);
	
	List userNameList=new ArrayList<>();
	List todayCksList=new ArrayList<>();
	for(int i=0;i<tUserEpTypeCountsList.size();i++){
		userNameList.add(tUserEpTypeCountsList.get(i).getUserName());
		todayCksList.add(tUserEpTypeCountsList.get(i).getTodayCks());
	}
	JSONArray userNameListJson = JSONArray.fromObject( userNameList );//将java对象转换为json对象
	JSONArray todayCksListJson = JSONArray.fromObject( todayCksList );//将java对象转换为json对象
	model.addAttribute("userNameList", userNameListJson);
	model.addAttribute("todayCksList", todayCksListJson);
	
	return model;
}


}
