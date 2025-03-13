package tech.curtiu.brcommerce.projections;

public interface UserDetailsProjection {

    String getUsername();

    String getPassword();

    Long getRoleId();

    String getAuthority();
}
