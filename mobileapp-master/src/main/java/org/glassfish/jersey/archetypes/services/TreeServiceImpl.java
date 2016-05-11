package org.glassfish.jersey.archetypes.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import org.glassfish.jersey.archetypes.DatabaseConnectionProvider;
import org.glassfish.jersey.archetypes.model.Tree;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Created by akhilakishore on 29/04/16.
 */
public class TreeServiceImpl implements TreeService {
    private final DatabaseConnectionProvider db;

    public TreeServiceImpl(DatabaseConnectionProvider db){this.db =db;}


    public Tree getTreeByTreeId(int treeId, String sessionToken) throws Exception {
        if (!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)) {
            return null;
        }

        ResultSet resultSet = db.select("select * from TREE where treeID="+treeId+";");
        Tree tree = new Tree();

        if (resultSet.next()){
            tree.setTreeId(resultSet.getInt(1));
            tree.setTreeName(resultSet.getString(2));
            tree.setTreeInstallationDate(resultSet.getDate(3));
            tree.setTreeMaintenanceDate(resultSet.getDate(4));
            tree.setTreeCount(resultSet.getInt(5));
            tree.setTreeLocation(resultSet.getString(6));
            tree.setAdminId(resultSet.getString(7));
        }
            return tree;
    }

    @Override
    public int addTree(Tree tree, String sessionToken) throws Exception {
        if (!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)) {
            return -1;
        }

        PreparedStatement preparedStatement = db.getConnection().prepareStatement("Insert into TREE values(?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, tree.getTreeId());
        preparedStatement.setString(2, tree.getTreeName());
        preparedStatement.setDate(3, new Date(tree.getTreeInstallationDate().getTime()));
        preparedStatement.setDate(4, new Date(tree.getTreeMaintenanceDate().getTime()));
        preparedStatement.setInt(5, tree.getTreeCount());
        preparedStatement.setString(6, tree.getTreeLocation());
        preparedStatement.setString(7, tree.getAdminId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int deleteTree(int treeId, String sessionToken) throws Exception {
        if (!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)) {
            return -1;
        }
        return db.insertUpdateDelete("Delete from TREE where treeID="+treeId);
    }

    @Override
    public int updateTree(int treeId, String maintenanceDate, String sessionToken) throws Exception {
        if (!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)) {
            return -1;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(maintenanceDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        PreparedStatement preparedStatement = db.getConnection().prepareStatement("Update TREE Set treeMaintenanceDate=? where treeID=?");
        preparedStatement.setDate(1, sqlDate);
        preparedStatement.setInt(2, treeId);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public JSONArray getAllTreeInformation(String sessionToken) throws Exception {
        if (!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)) {
            return null;
        }
        ResultSet resultSet = db.select("select * from TREE");
        JSONArray array = new JSONArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        while(resultSet.next()){
            Tree tree = new Tree();
            tree.setTreeId(resultSet.getInt(1));
            tree.setTreeName(resultSet.getString(2));
            tree.setTreeInstallationDate(resultSet.getDate(3));
            tree.setTreeMaintenanceDate(resultSet.getDate(4));
            tree.setTreeCount(resultSet.getInt(5));
            tree.setTreeLocation(resultSet.getString(6));
            tree.setAdminId(resultSet.getString(7));
            array.add(gson.toJson(tree));
        }
        return array;
    }

    public static void main(String args[]) throws Exception {
        new TreeServiceImpl(DatabaseConnectionProvider.getDatabaseProvider()).updateTree(101, "2099-02-02","akhila@gmail.com");
    }
}
