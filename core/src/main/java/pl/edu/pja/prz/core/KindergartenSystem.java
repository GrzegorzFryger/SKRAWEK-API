package pl.edu.pja.prz.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.edu.pja.prz.account.AccountConfiguration;
import pl.edu.pja.prz.calendar.CalendarConfiguration;
import pl.edu.pja.prz.commons.CommonsConfiguration;
import pl.edu.pja.prz.finances.FinancesConfiguration;
import pl.edu.pja.prz.groups.GroupsConfiguration;
import pl.edu.pja.prz.mail.MailConfiguration;
import pl.edu.pja.prz.meal.MealConfiguration;
import pl.edu.pja.prz.payments.PaymentsConfiguration;
import pl.edu.pja.prz.receivables.ReceivablesConfiguration;
import pl.edu.pja.prz.scheduler.SchedulerConfiguration;

@SpringBootApplication
@Import({AccountConfiguration.class, CalendarConfiguration.class, GroupsConfiguration.class,
		ReceivablesConfiguration.class, PaymentsConfiguration.class,
		FinancesConfiguration.class, CommonsConfiguration.class,
		MealConfiguration.class, MailConfiguration.class, SchedulerConfiguration.class})

public class KindergartenSystem {
	public static void main(String[] args) {
		SpringApplication.run(KindergartenSystem.class, args);
	}
}
