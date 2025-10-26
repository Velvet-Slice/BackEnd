package com.velvetslice.pi_velvetslice.Repository;

import com.velvetslice.pi_velvetslice.models.UserFuncionario;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFuncionarioRepository extends JpaRepository<UserFuncionario,Long> {
}
