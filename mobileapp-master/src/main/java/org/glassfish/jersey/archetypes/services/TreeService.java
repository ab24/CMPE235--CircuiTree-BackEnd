package org.glassfish.jersey.archetypes.services;

import net.sf.json.JSONArray;
import org.glassfish.jersey.archetypes.model.Tree;

import java.sql.SQLException;

/**
 * Created by akhilakishore on 29/04/16.
 */
public interface TreeService {
    public JSONArray getAllTreeInformation(String sessionToken) throws Exception;
    public Tree getTreeByTreeId(int treeId, String sessionToken) throws Exception;
    public int addTree(Tree tree, String sessionToken) throws Exception;
    public int deleteTree(int treeId, String sessionToken) throws Exception;
    public int updateTree(int treeId, String maintenanceDate, String sessionToken) throws Exception;
}
