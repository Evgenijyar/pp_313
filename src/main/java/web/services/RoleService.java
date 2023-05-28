package web.services;

import org.springframework.stereotype.Component;
import web.models.Role;

import java.util.List;

@Component
public interface RoleService {
    List<Role> getAllRoles();
}
