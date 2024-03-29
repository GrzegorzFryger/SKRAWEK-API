package pl.edu.pja.prz.receivables.model;


import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Transaction extends BaseEntityLong implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    @NotNull
    private LocalDate transactionDate;
    @NotNull
    private LocalDate bookingDate;
    @NotNull
    private String contractorDetails;
    @NotNull
    private String title;
    @NotNull
    private String accountNumber;
    @NotNull
    private String bankName;
    @NotNull
    private String details;
    @NotNull
    private String transactionNumber;
    @NotNull
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal transactionAmount;
    @NotNull
    private String transactionCurrency;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID childId;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID guardianId;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getContractorDetails() {
        return contractorDetails;
    }

    public void setContractorDetails(String contractorDetails) {
        this.contractorDetails = contractorDetails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public UUID getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(UUID guardianId) {
        this.guardianId = guardianId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionDate.equals(that.transactionDate) &&
                Objects.equals(bookingDate, that.bookingDate) &&
                contractorDetails.equals(that.contractorDetails) &&
                title.equals(that.title) &&
                accountNumber.equals(that.accountNumber) &&
                bankName.equals(that.bankName) &&
                details.equals(that.details) &&
                transactionNumber.equals(that.transactionNumber) &&
                transactionAmount.equals(that.transactionAmount) &&
                transactionCurrency.equals(that.transactionCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, bookingDate, contractorDetails, title, accountNumber, bankName, details,
                transactionNumber, transactionAmount, transactionCurrency);
    }

    @Override
    public String toString() {
        return transactionDate + " " + title + " [" + transactionAmount + " " + transactionCurrency + "]";
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved transaction: {}", this);
    }
}
