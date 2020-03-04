package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.facade.dto.AccountActivateDto;
import pl.edu.pja.prz.account.facade.dto.AccountCredentialDto;

import java.util.Optional;

public interface AccountCredentialFacade {
	Optional<AccountCredentialDto> findByEmail(String email);

	boolean activateAccount(AccountActivateDto accountActivateDto);
}
