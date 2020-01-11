package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.EmployeeFacade;
import pl.edu.pja.prz.account.facade.GuardianFacade;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/account/")
public class AccountController {
	private final GuardianFacade guardianFacade;
	private final EmployeeFacade employeeFacade;

	@Autowired
	public AccountController(GuardianFacade guardianFacade, EmployeeFacade employeeFacade) {
		this.guardianFacade = guardianFacade;
		this.employeeFacade = employeeFacade;
	}

	@GetMapping("guardian/{id}")
	public ResponseEntity<GuardianDto> findGuardianById(@PathVariable UUID id) {
		return new ResponseEntity<>(guardianFacade.findGuardianById(id), HttpStatus.OK);
	}

	@GetMapping("guardians")
	public ResponseEntity<List<GuardianDto>> findAllGuardians() {
		return new ResponseEntity<>(guardianFacade.findAllGuardians(), HttpStatus.OK);
	}

	@GetMapping("guardians/{id}/children")
	public ResponseEntity<Set<ChildDto>> findAllGuardianChildren(@PathVariable UUID id) {
		return new ResponseEntity<>(guardianFacade.findAllGuardianChildren(id), HttpStatus.OK);
	}

	@PostMapping("guardian")
	public ResponseEntity<GuardianDto> createGuardian(@RequestBody AccountDto accountDto) {
		return new ResponseEntity<>(guardianFacade.createGuardian(accountDto), HttpStatus.OK);
	}



}
