package services;

import java.util.Iterator;
import java.util.List;

import exceptions.NoMoreRecordsException;
import exceptions.NotFoundException;
import models.Role;
import repository.Implementation.RoleRepo;
import util.DisplayUtil;

public class RoleService {

    private final RoleRepo roleRepo = RoleRepo.getInstance();
    private static RoleService instance;

    private RoleService() {}

    public static RoleService getInstance() {
        if (instance == null) {
            instance = new RoleService();
        }
        return instance;
    }

  
    public void addRole(String roleName) {
        if (checkAlreadyExist(roleName)) {
            System.out.println("Role '" + roleName + "' already exists!");
            return;
        }

        Role role = new Role(roleName.toLowerCase());
        roleRepo.add(role);
        System.out.println("Role '" + roleName + "' added successfully!");
    }


    public boolean checkAlreadyExist(String roleName) {
        return roleRepo.checkAlreadyExist(roleName);
    }

    public Role getRoleById(int id) {
        try {
            return roleRepo.getById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Role with ID " + id + " not found!");
        }
    }

    public void updateRole(String roleName, int roleId) {
        Role role = getRoleById(roleId);
        role.setRoleName(roleName.toLowerCase());
        roleRepo.update(role);
        System.out.println("Role updated successfully!");
    }

    public void getAll() {
        List<Role> roles = roleRepo.getAll();

        if (roles.isEmpty()) {
            throw new NoMoreRecordsException(" No Role Records Found!");
        }

        DisplayUtil.showRoles(roles);
    }

    public void getAdminRoles() {
        List<Role> roles = roleRepo.getAll();

        Iterator<Role> itr = roles.iterator();
        while (itr.hasNext()) {
            Role role = itr.next();

            if (role.getRoleName().equalsIgnoreCase("customer")) {
                itr.remove();
            }
        }

        DisplayUtil.showRoles(roles);
    }

    public void syncChanges() {
        roleRepo.syncChanges();
    }

    public Role getRoleByName(String roleName) {
        for (Role role : roleRepo.getAll()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return role;
            }
        }

        throw new NotFoundException("The role '" + roleName + "' does not exist.");
    }
}
