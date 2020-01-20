package pl.edu.pja.prz.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BasicAccountRepository<T extends Account> extends JpaRepository<T,UUID> {
	Optional<T> findByEmailAndFullName(String email, FullName fullName);
	Optional<T> findByEmail(String email);

}
