package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.role.kioskClientRep;
import com.ktk.ktkapp.dto.role.kioskCustodian;
import com.ktk.ktkapp.dto.role.ktkEmployee;
import com.ktk.ktkapp.model.role.kioskClientRepModel;
import com.ktk.ktkapp.model.role.kioskCustodianModel;
import com.ktk.ktkapp.model.role.ktkEmployeeModel;
import com.ktk.ktkapp.model.role.roleModel;
import com.ktk.ktkapp.dto.user.userCreate;
import com.ktk.ktkapp.model.user.userModel;
import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import com.ktk.ktkapp.repos.kiosk.kioskClientRepo;
import com.ktk.ktkapp.repos.user.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class roleService {

    private final userRepository userRepo;
    private final roleRepository roleRepo;
    private final kioskClientRepRepository kioskClientRepRepo;
    private final kioskClientRepo kioskClientRepo;
    private final ktkEmployeeRepository ktkEmployeeRepo;
    private final kioskCustodianRepository kioskCustodianRepo;

    public roleService(userRepository userRepo,
                       roleRepository roleRepo, kioskClientRepRepository kioskClientRepRepo, ktkEmployeeRepository ktkEmployeeRepo, kioskCustodianRepository kioskCustodianRepo, kioskClientRepo kioskClientRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.kioskClientRepRepo = kioskClientRepRepo;
        this.kioskClientRepo = kioskClientRepo;
        this.ktkEmployeeRepo = ktkEmployeeRepo;
        this.kioskCustodianRepo = kioskCustodianRepo;
    }

    /**
     * Assigns roles to a user.
     * Assumes rolesString is a comma-separated string of role names.
     */
    public void assignRolesToUser(Long userId, String rolesString) {
        userModel user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Set<roleModel> roles = Arrays.stream(rolesString.split(","))
                .map(String::trim)
                .map(roleName -> roleRepo.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles.stream().collect(Collectors.toList()));
        userRepo.save(user);
    }

    /**
     * Retrieves the roles associated with a user.
     */
    public List<String> getUserRoles(Long userId) {
        userModel user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        return user.getRoles().stream()
                .map(roleModel::getName)
                .collect(Collectors.toList());
    }

    public void createRoleSpecificProfiles(userModel user, userCreate createDTO) {
        Long userId = user.getUserId();cd

        if (createDTO.getRoles().contains("KIOSK_CLIENT") && createDTO.getKioskClientRepProfile() != null) {
            kioskClientRep dto = createDTO.getKioskClientRepProfile();

            kioskClientRepModel clientRep = new kioskClientRepModel();
            clientRep.setUser(user);
            clientRep.setJobTitle(dto.getJobTitle());
            kioskClientRepRepo.save(clientRep);

           
        }

        if (createDTO.getRoles().contains("KTK_USER") && createDTO.getKtkUser() != null) {
            ktkEmployee dto = createDTO.getKtkUser();

            ktkEmployeeModel emp = new ktkEmployeeModel();
            emp.setUser(user);
            emp.setDepartment(dto.getDepartment());

            ktkEmployeeRepo.save(emp);
        }

        if (createDTO.getRoles().contains("KIOSK_CUSTODIAN") && createDTO.getKioskCustodianProfile() != null) {
            kioskCustodian dto = createDTO.getKioskCustodianProfile();

            kioskCustodianModel custodian = new kioskCustodianModel();
            custodian.setUser(user);

            // Set client as kiosk_client (organization)
            kioskClientModel client = kioskClientRepo.findById(dto.getClientId()).orElse(null);
            custodian.setClient(client.getClientRep());

            kioskCustodianRepo.save(custodian);
        }
    }
}
