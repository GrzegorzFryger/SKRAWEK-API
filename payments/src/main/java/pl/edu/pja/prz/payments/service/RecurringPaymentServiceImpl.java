package pl.edu.pja.prz.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.PaymentFactory;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.StatusPayment;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;
import pl.edu.pja.prz.payments.repository.DiscountRepository;
import pl.edu.pja.prz.payments.repository.RecurringPaymentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RecurringPaymentServiceImpl implements RecurringPaymentService {
    private final RecurringPaymentRepository recurringPaymentRepository;
    private final DiscountRepository discountRepository;

    @Autowired
    public RecurringPaymentServiceImpl(RecurringPaymentRepository recurringPaymentRepository,
                                       DiscountRepository discountRepository) {
        this.recurringPaymentRepository = recurringPaymentRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public RecurringPayment createOtherPayment(RecurringPayment recurringPayment) {
        return this.createOtherPayment(recurringPayment.getChildId(), recurringPayment.getGuardianId(), recurringPayment,
                recurringPayment.getPeriodValidity());
    }

    @Override
    public RecurringPayment createOtherPayment(UUID childId, UUID guardianId, Payment payment,
                                               PeriodValidity periodValidity) {
        return recurringPaymentRepository
                .save(PaymentFactory.createOtherRecurringPayment(childId, guardianId, payment, periodValidity));
    }

    @Override
    public RecurringPayment createTuition(RecurringPayment recurringPayment) {
        return this.createTuition(recurringPayment.getChildId(), recurringPayment.getGuardianId(), recurringPayment,
                recurringPayment.getPeriodValidity());
    }

    @Override
    public RecurringPayment createTuition(UUID childId, UUID guardianId, Payment payment,
                                          PeriodValidity periodValidity) {
        return recurringPaymentRepository.save(
                PaymentFactory.createTuitionPayment(childId, guardianId, payment, periodValidity)
        );
    }

    @Override
    public RecurringPayment getPaymentById(Long id) {
        return recurringPaymentRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Not found payment with id " + id));
    }

    @Override
    public List<RecurringPayment> getAllPayments() {
        return recurringPaymentRepository.findAll();
    }


    @Override
    public RecurringPayment updatePayment(RecurringPayment recurringPayment) {
        return recurringPaymentRepository.findById(recurringPayment.getId()).map(payment -> {
            if (recurringPayment.getBaseAmount() != null) {
                payment.setBaseAmount(recurringPayment.getBaseAmount());
            }
            if (recurringPayment.getDescription() != null) {
                payment.setDescription(recurringPayment.getDescription());
            }
            if (recurringPayment.getPeriodValidity() != null) {
                payment.setPeriodValidity(recurringPayment.getPeriodValidity());
            }
            if (recurringPayment.getStatusPayment() != null) {
                payment.setStatusPayment(recurringPayment.getStatusPayment());
            }
            if (recurringPayment.getDiscount() != null) {
                discountRepository.findById(recurringPayment.getDiscount().getId())
                        .ifPresentOrElse(
                                payment::setDiscount,
                                () -> {
                                    throw new ElementNotFoundException("Discount", "Not found with id" +
                                            recurringPayment.getDiscount().getId());
                                });

            }
            return recurringPaymentRepository.save(payment);
        }).orElseThrow(() -> new ElementNotFoundException("Not found payment with id " + recurringPayment.getId()));
    }

    @Override
    public RecurringPayment markAsCancelPayment(Long paymentId) {
        return recurringPaymentRepository.findById(paymentId).map(payment -> {
            payment.setStatusPayment(StatusPayment.CANCELED);
            return recurringPaymentRepository.save(payment);
        }).orElseThrow(() -> {
            throw new ElementNotFoundException("Not found payment with id " + paymentId);
        });
    }

    @Override
    public void deletePayment(RecurringPayment recurringPayment) {
        recurringPaymentRepository.delete(recurringPayment);
    }

    @Override
    public void deletePayment(Long id) {
        recurringPaymentRepository.deleteById(id);
    }

    @Override
    public Discount addDiscountToPayment(UUID childId, Long discountId) {
        return menageRecurringPaymentDiscounts(childId, discountId, false);
    }

    @Override
    public Discount removeDiscountsFromPayment(UUID childId, Long discountId) {
        return menageRecurringPaymentDiscounts(childId, discountId, true);
    }

    @Override
    public List<RecurringPayment> findAllByChildren(UUID childId) {
        return this.recurringPaymentRepository.findAllByChildId(childId);
    }

    private Discount menageRecurringPaymentDiscounts(UUID childId, Long discountId, boolean remove) {
        return discountRepository.findById(discountId).map(discount ->
                recurringPaymentRepository.findActiveByChildId(childId).map(
                        payment -> {
                            if (remove) {
                                payment.setDiscount(null);
                            } else {
                                payment.setDiscount(discount);
                            }
                            recurringPaymentRepository.save(payment);
                            return payment.getDiscount();
                        }
                ).orElseThrow(() -> new ElementNotFoundException("Not found payment with child id " + childId))
        ).orElseThrow(() -> new ElementNotFoundException("Not found discount with id " + discountId));
    }
}
