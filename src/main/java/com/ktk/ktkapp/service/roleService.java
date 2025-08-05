package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.role.clientRep.request.kioskClientRepRequest;
import com.ktk.ktkapp.dto.role.custodian.request.kioskCustodianRequest;
import com.ktk.ktkapp.dto.role.ktkEmployee.request.ktkUserRequest;
import com.ktk.ktkapp.dto.user.request.userRegistrationRequest;
import com.ktk.ktkapp.model.role.kioskClientRepModel;
import com.ktk.ktkapp.model.role.kioskCustodianModel;
import com.ktk.ktkapp.model.role.ktkUserModel;
import com.ktk.ktkapp.model.role.roleModel;
import com.ktk.ktkapp.model.user.userModel;
import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import com.ktk.ktkapp.repos.kiosk.kioskClientRepo;
import com.ktk.ktkapp.repos.user.*;
import com.ktk.ktkapp.dto.role.roleMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class roleService {

    private final userRepository userRepo;
    private final roleRepository roleRepo;
    private final kioskClientRepRepository kioskClientRepRepo;
    private final kioskClientRepo kioskClientRepo;
    private final ktkUserRepository ktkEmployeeRepo;
    private final kioskCustodianRepository kioskCustodianRepo;
    private final roleMapper roleMaps;

    public roleService(userRepository userRepo,
                       roleRepository roleRepo, kioskClientRepRepository kioskClientRepRepo, ktkUserRepository ktkEmployeeRepo, kioskCustodianRepository kioskCustodianRepo, kioskClientRepo kioskClientRepo, roleMapper roleMaps) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.kioskClientRepRepo = kioskClientRepRepo;
        this.kioskClientRepo = kioskClientRepo;
        this.ktkEmployeeRepo = ktkEmployeeRepo;
        this.kioskCustodianRepo = kioskCustodianRepo;
        this.roleMaps = roleMaps;
    }

    /**
     * Assigns roles to a user.
     * This method is now much cleaner and more focused on its single responsibility.
     * @param userId The ID of the user to assign roles to.
     * @param roleNames A list of role names to assign.
     */
    public void assignRolesToUser(Long userId, List<String> roleNames) {
        userModel user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Set<roleModel> roles = roleNames.stream()
                .map(String::trim)
                .map(roleName -> roleRepo.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles.stream().toList());
        userRepo.save(user);
    }

    /**
     * Retrieves the roles associated with a user.
     * @param userId The ID of the user.
     * @return A list of role names for the specified user.
     */
    public List<String> getUserRoles(Long userId) {
        userModel user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        return user.getRoles().stream()
                .map(roleModel::getName)
                .collect(Collectors.toList());
    }


    @Transactional
    public void createRoleSpecificProfiles(userModel user, userRegistrationRequest createDTO) {
        if (createDTO.getRoles().contains("KIOSK_CLIENT_REP") && createDTO.getKioskClientRepProfile() != null) {
            createKioskClientRepProfile(user, createDTO.getKioskClientRepProfile());
        }

        if (createDTO.getRoles().contains("KTK_USER") && createDTO.getKtkUserProfile() != null) {
            createKtkUserProfile(user, createDTO.getKtkUserProfile());
        }

        if (createDTO.getRoles().contains("KIOSK_CUSTODIAN") && createDTO.getKioskCustodianProfile() != null) {
            createKioskCustodianProfile(user, createDTO.getKioskCustodianProfile());
        }
    }

    /**
     * Creates a Kiosk Client Representative profile.
     * @param user The user to link the profile to.
     * @param dto The DTO with the profile details.
     */
    private void createKioskClientRepProfile(userModel user, kioskClientRepRequest dto) {
        kioskClientRepModel clientRep = roleMaps.toKioskClientRepModel(dto);
        clientRep.setUser(user);

        kioskClientModel clientOrg = kioskClientRepo.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Kiosk Client Organization not found with ID: " + dto.getClientId()));
        clientRep.setClient(clientOrg);

        kioskClientRepRepo.save(clientRep);
    }

    /**
     * Creates a Kinektek User profile.
     * @param user The user to link the profile to.
     * @param dto The DTO with the profile details.
     */
    private void createKtkUserProfile(userModel user, ktkUserRequest dto) {
        ktkUserModel ktkUser = roleMaps.toKtkUserModel(dto);
        ktkUser.setUser(user);
        ktkEmployeeRepo.save(ktkUser);
    }

    /**
     * Creates a Kiosk Custodian profile.
     * @param user The user to link the profile to.
     * @param dto The DTO with the profile details.
     */
    private void createKioskCustodianProfile(userModel user, kioskCustodianRequest dto) {
        kioskCustodianModel custodian = roleMaps.toKioskCustodianModel(dto);
        custodian.setUser(user);

        kioskClientModel clientOrg = kioskClientRepo.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Kiosk Client Organization not found with ID: " + dto.getClientId()));
        custodian.setClient(clientOrg);

        kioskCustodianRepo.save(custodian);
    }


}
