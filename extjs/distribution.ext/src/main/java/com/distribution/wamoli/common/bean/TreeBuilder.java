package com.distribution.wamoli.common.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;







public class TreeBuilder {
	
	public static List<Map<String,Object>> updateTreeIcon(List<Map<String,Object>> list){
		return updateTreeIcon(list,"classic/resources/images/icons/16x16/");
	}
	
	public static List<Map<String,Object>> updateTreeIcon(List<Map<String,Object>> list,String iconpath){
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = list.get(i);
			String icon = (String) map.get("icon");
			if(!StringUtils.isEmpty(icon)){
				map.put("icon",iconpath+icon);
			}
		}
		return list;
	}

    @SuppressWarnings({ "unchecked"})
    public static List<TreeNode> buildListToTree(List<TreeNode> dirs) {
    	if(dirs == null || dirs.size() == 0)return dirs;
        List<TreeNode> roots = findRoots(dirs);
        List<TreeNode> notRoots = (List<TreeNode>) CollectionUtils.subtract(dirs, roots);
        for (TreeNode root : roots) {
            root.setChildren(findChildren(root, notRoots));
        }
        return roots;
    }

    private static List<TreeNode> findRoots(List<TreeNode> allTreeNodes) {
        List<TreeNode> results = new ArrayList<TreeNode>();
        for (TreeNode node : allTreeNodes) {
            boolean isRoot = true;
            for (TreeNode comparedOne : allTreeNodes) {
                if (node.getParentId().equals(comparedOne.getId())) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                node.setLevel(0);
                results.add(node);
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
	private static List<TreeNode> findChildren(TreeNode root, List<TreeNode> allTreeNodes) {
        List<TreeNode> children = new ArrayList<TreeNode>();

        for (TreeNode comparedOne : allTreeNodes) {
            if (comparedOne.getParentId().equals(root.getId())) {
                comparedOne.setLevel(root.getLevel() + 1);
                children.add(comparedOne);
            }
        }
        List<TreeNode> notChildren = (List<TreeNode>) CollectionUtils.subtract(allTreeNodes, children);
        for (TreeNode child : children) {
            List<TreeNode> tmpChildren = findChildren(child, notChildren);
            if (tmpChildren == null || tmpChildren.size() < 1) {
                child.setLeaf(true);
            } else {
                child.setLeaf(false);
            }
            child.setChildren(tmpChildren);
        }
        return children;
    }
    
    /**
     * 增加分组节点
     * @param list 数据
     * @param type param3的类型
     * @param text 分组名称
     * @param icon 分组图标
     */
    public static void addGroupNode(List<TreeNode> list,String type,String text,String icon){
		List<TreeNode> btnslist = new ArrayList<TreeNode>();
		Map<String,TreeNode> map = new HashMap<String,TreeNode>();
		for (int i = 0; i < list.size(); i++) {
			TreeNode tn = list.get(i);
			if(tn.getParam3().equals(type)){ //按钮权限
				String menuid = tn.getParentId();
				TreeNode node = map.get(menuid);
				if(node==null){
					node = new TreeNode();
					node.setId(tn.getId()+"_"+type);
					node.setParentId(tn.getParentId());
					node.setText(text);
					if(icon.startsWith("x-fa")){
						node.setIconCls(icon);
					}else{
						node.setIcon(icon);
					}
					node.setDisabled(true);
					map.put(menuid,node);
					btnslist.add(node);
				}
				tn.setParentId(node.getId());
			}
		}
		list.addAll(btnslist);
    }

    public static void main(String[] args) {
        List<TreeNode> allTreeNodes = new ArrayList<TreeNode>();
        allTreeNodes.add(new TreeNode("1","0", "节点1"));
        allTreeNodes.add(new TreeNode("2", "0", "节点2"));
        allTreeNodes.add(new TreeNode("3", "0", "节点3"));
        allTreeNodes.add(new TreeNode("4", "1", "节点4"));
        allTreeNodes.add(new TreeNode("5", "1", "节点5"));
        allTreeNodes.add(new TreeNode("6", "1", "节点6"));
        allTreeNodes.add(new TreeNode("7", "4", "节点7"));
        allTreeNodes.add(new TreeNode("8", "4", "节点8"));
        allTreeNodes.add(new TreeNode("9", "5", "节点9"));
        allTreeNodes.add(new TreeNode("10", "100", "节点10"));
        allTreeNodes.get(0).setIcon("plugin.gif");
        List<TreeNode> roots = buildListToTree(allTreeNodes);
      //  System.out.println(JSON.toString(roots));
        for (TreeNode n : roots) {
     //       System.out.println(JSON.toString(n));
        }

    }
}